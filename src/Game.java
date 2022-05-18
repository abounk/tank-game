import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Game extends JFrame {
    private Thread gameThread;
    private WorldPanel worldPanel;
    private World world;

    private MenuUI menuUI;

    public Game() {
        world = new World(30, 20);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Tank game");
        setLayout(new BorderLayout());
        setResizable(false);
    }

    public void startGame() {
        worldPanel = new WorldPanel();
        add(worldPanel);
        pack();
        worldPanel.requestFocus();
        gameThread = new Thread() {
            public void run() {
                while (!world.getIsOver()) {
                    world.tick();
                    try {
                        sleep(1000 / 300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        };
        gameThread.start();
    }

    public void start() {
        setVisible(true);
        initPreGame();
    }

    public void initPreGame() {
        menuUI = new MenuUI();
        add(menuUI);
        pack();
    }

    public void deleteInitPreGame() {
        remove(menuUI);
        pack();
    }

    class WorldPanel extends JPanel {
        public static final int PIXEL_SIZE = 40;

        private final Image imageBrick = new ImageIcon(getClass().getResource("res/brick.png")).getImage();
        private final Image imageSteel = new ImageIcon(getClass().getResource("res/steel.png")).getImage();
        private final Image imageBush = new ImageIcon(getClass().getResource("res/bush.png")).getImage();

        private Image imgTank;
        private Image imgBullet;

        WorldPanel() {

            setPreferredSize(new Dimension(
                    world.getWidth() * PIXEL_SIZE,
                    world.getHeight() * PIXEL_SIZE));

            addKeyListener(new KeyHandler(
                    world.getTanks().get(0),
                    KeyEvent.VK_W,
                    KeyEvent.VK_S,
                    KeyEvent.VK_A,
                    KeyEvent.VK_D,
                    KeyEvent.VK_F));

            if (world.getIsMultiplayer()) {
                addKeyListener(new KeyHandler(
                        world.getTanks().get(1),
                        KeyEvent.VK_I,
                        KeyEvent.VK_K,
                        KeyEvent.VK_J,
                        KeyEvent.VK_L,
                        KeyEvent.VK_SEMICOLON));
            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, world.getWidth() * PIXEL_SIZE, world.getHeight() * PIXEL_SIZE);
            g.setColor(Color.white);

            paintBullet(g);
            paintTank(g);
            paintBlock(g);

            if (world.getIsOver()) {
                JOptionPane.showMessageDialog(this, "Game ended!", "Message",
                        JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        }

        public void paintBlock(Graphics g) {
            List<WorldObj> blockList = world.getWorldObjList();
            for (WorldObj block : blockList) {
                int x = block.getX() * PIXEL_SIZE;
                int y = block.getY() * PIXEL_SIZE;
                if (block instanceof Brick) {
                    g.drawImage(imageBrick, x, y, PIXEL_SIZE, PIXEL_SIZE, null, null);
                } else if (block instanceof Steel) {
                    g.drawImage(imageSteel, x, y, PIXEL_SIZE, PIXEL_SIZE, null, null);
                } else {
                    g.drawImage(imageBush, x, y, PIXEL_SIZE, PIXEL_SIZE, null, null);
                }
            }
        }

        public void paintTank(Graphics g) {
            for (Tank tank : world.getTanks()) {
                if (tank.getName() == "Player 1") {
                    switch (tank.getDirection()) {
                        case UP -> imgTank = new ImageIcon(getClass().getResource("res/greenTank_up.png")).getImage();
                        case DOWN -> imgTank = new ImageIcon(getClass().getResource("res/greenTank_down.png")).getImage();
                        case LEFT -> imgTank = new ImageIcon(getClass().getResource("res/greenTank_left.png")).getImage();
                        case RIGHT -> imgTank = new ImageIcon(getClass().getResource("res/greenTank_right.png")).getImage();
                    }
                } else {
                    switch (tank.getDirection()) {
                        case UP -> imgTank = new ImageIcon(getClass().getResource("res/redTank_up.png")).getImage();
                        case DOWN -> imgTank = new ImageIcon(getClass().getResource("res/redTank_down.png")).getImage();
                        case LEFT -> imgTank = new ImageIcon(getClass().getResource("res/redTank_left.png")).getImage();
                        case RIGHT -> imgTank = new ImageIcon(getClass().getResource("res/redTank_right.png")).getImage();
                    }
                }
                int nx = tank.getX();
                int ny = tank.getY();
                g.drawImage(imgTank, nx, ny, tank.getWidth(), tank.getHeight(), null, null);
            }
        }

        public void turnBullet(Bullet bullet) {
            switch (bullet.getDirection()) {
                case UP -> imgBullet = new ImageIcon(getClass().getResource("res/bullet_up.png")).getImage();
                case DOWN -> imgBullet = new ImageIcon(getClass().getResource("res/bullet_down.png")).getImage();
                case LEFT -> imgBullet = new ImageIcon(getClass().getResource("res/bullet_left.png")).getImage();
                case RIGHT -> imgBullet = new ImageIcon(getClass().getResource("res/bullet_right.png")).getImage();
            }
        }

        public void paintBullet(Graphics g) {
            for (Tank tank: world.getTanks()) {
                for (Bullet bullet : tank.getBullets()) {
                    turnBullet(bullet);
                    int bx = bullet.getX();
                    int by = bullet.getY();
                    g.drawImage(imgBullet, bx, by, bullet.getWidth(), bullet.getHeight(), null, null);
                }
            }
        }
    }

    class MenuUI extends JPanel {

        public MenuUI() {
            setLayout(null);
            setPreferredSize(new Dimension(
                    world.getWidth() * 40,
                    world.getHeight() * 40));
            JLabel preGameLabel = new JLabel("Tank Game");
            preGameLabel.setFont(new Font("Arial", Font.PLAIN, 50));
            preGameLabel.setBounds(475, 50, 400, 100);
            JLabel preGameLabel2 = new JLabel("Select Game Mode");
            preGameLabel2.setFont(new Font("Arial", Font.PLAIN, 50));
            preGameLabel2.setBounds(400, 150, 450, 100);
            add(preGameLabel);
            add(preGameLabel2);
            pack();
            setButton();
        }

        private void setButton() {
            onePlayerButton();
            twoPlayerButton();
        }

        private void onePlayerButton() {
            JButton singlePlayerBtn = new JButton("Single-Player");
            singlePlayerBtn.setBounds(400, 300, 400, 100);
            singlePlayerBtn.setFont(new Font("Arial", Font.PLAIN, 50));
            singlePlayerBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    world.setSinglePlayer();
                    deleteInitPreGame();
                    pack();
                    startGame();

                }
            });
            add(singlePlayerBtn);
        }

        private void twoPlayerButton() {
            JButton multiPlayerBtn = new JButton("Multi-Player");
            multiPlayerBtn.setBounds(400, 500, 400, 100);
            multiPlayerBtn.setFont(new Font("Arial", Font.PLAIN, 50));
            multiPlayerBtn.setPreferredSize(new Dimension(400, 100));
            multiPlayerBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    SinglePlayerBtn.setEnabled(false);
                    world.setMultiplayer();
                    deleteInitPreGame();
                    pack();
                    startGame();
                }
            });
            add(multiPlayerBtn);
        }
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
