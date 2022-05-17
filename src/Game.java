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

    private PreGameUI preGameUI;
    public boolean selectMode = false;
    public boolean onePlayermode = false;
    public boolean twoPlayermode = false;


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
                while (!world.getisOver()) {
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
        preGameUI = new PreGameUI();
        add(preGameUI);
        pack();
    }

    public void deleteinitPreGame() {
        remove(preGameUI);
        pack();
    }



    class WorldPanel extends JPanel {
        public static final int PIXEL_SIZE = 40;

        private List<Brick> brickList;
        private List<Steel> steelList;
        private List<Bush> bushList;

        private Image imageBrick;
        private Image imageSteel;
        private Image imageBush;

        private Image imgTank1;
        private Image imgTank2;
        private Image imgBullet;

        WorldPanel() {
            setPreferredSize(new Dimension(
                    world.getWidth() * PIXEL_SIZE,
                    world.getHeight() * PIXEL_SIZE));

            imageBrick = new ImageIcon("imgs/brick.png").getImage();
            imageSteel = new ImageIcon("imgs/steel.png").getImage();
            imageBush = new ImageIcon("imgs/bush2.png").getImage();

            addKeyListener(new KeyHandler(
                    world.getTank(0),
                    KeyEvent.VK_W,
                    KeyEvent.VK_S,
                    KeyEvent.VK_A,
                    KeyEvent.VK_D,
                    KeyEvent.VK_F));

            addKeyListener(new KeyHandler(
                    world.getTank(1),
                    KeyEvent.VK_I,
                    KeyEvent.VK_K,
                    KeyEvent.VK_J,
                    KeyEvent.VK_L,
                    KeyEvent.VK_SEMICOLON
            ));
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, world.getWidth() * PIXEL_SIZE, world.getHeight() * PIXEL_SIZE);
            g.setColor(Color.white);
            // g.drawString("Player1 Score : " + "", 30, 30);



            paintBrick(g);
            paintSteel(g);

            paintBulletTank1(g);
            turnTank1();
            paintTank1(g);
            if (twoPlayermode) {
                // g.drawString("Player2 Score : " + "", 1030, 30);
                paintBulletTank2(g);
                turnTank2();
                paintTank2(g);
            }

            paintBush(g);

            if(world.getisOver()) {
                JOptionPane.showMessageDialog(this,"Game ended!", "Message",
                JOptionPane.WARNING_MESSAGE);
                Game game = new Game();
                game.start();
            }
        }

        public void paintBrick(Graphics g) {
            List<Brick> brickList = world.getBrickList();
            for (Brick a : brickList) {
                int x = a.getX() * PIXEL_SIZE;
                int y = a.getY() * PIXEL_SIZE;
                g.drawImage(imageBrick, x, y, PIXEL_SIZE, PIXEL_SIZE, null, null);
            }
        }

        public void paintSteel(Graphics g) {
            List<Steel> steelList = world.getSteelList();
            for (Steel a : steelList) {
                int x = a.getX() * PIXEL_SIZE;
                int y = a.getY() * PIXEL_SIZE;
                g.drawImage(imageSteel, x, y, PIXEL_SIZE, PIXEL_SIZE, null, null);
            }
        }

        public void paintBush(Graphics g) {
            List<Bush> bushList = world.getBushList();
            for (Bush a : bushList) {
                int x = a.getX() * PIXEL_SIZE;
                int y = a.getY() * PIXEL_SIZE;
                g.drawImage(imageBush, x, y, PIXEL_SIZE, PIXEL_SIZE, null, null);
            }
        }

        public void turnTank1() {
            switch (world.getTank(0).getDirection()) {
                case UP -> imgTank1 = new ImageIcon("img/greenTank_up.png").getImage();
                case DOWN -> imgTank1 = new ImageIcon("img/greenTank_down.png").getImage();
                case LEFT -> imgTank1 = new ImageIcon("img/greenTank_left.png").getImage();
                case RIGHT -> imgTank1 = new ImageIcon("img/greenTank_right.png").getImage();
            }
        }

        public void turnTank2() {
            switch (world.getTank(1).getDirection()) {
                case UP -> imgTank2 = new ImageIcon("img/redTank_up.png").getImage();
                case DOWN -> imgTank2 = new ImageIcon("img/redTank_down.png").getImage();
                case LEFT -> imgTank2 = new ImageIcon("img/redTank_left.png").getImage();
                case RIGHT -> imgTank2 = new ImageIcon("img/redTank_right.png").getImage();
            }
        }

        public void turnBullet(Bullet bullet) {
            switch (bullet.getDirection()) {
                case UP -> imgBullet = new ImageIcon("img/bullet_up.png").getImage();
                case DOWN -> imgBullet = new ImageIcon("img/bullet_down.png").getImage();
                case LEFT -> imgBullet = new ImageIcon("img/bullet_left.png").getImage();
                case RIGHT -> imgBullet = new ImageIcon("img/bullet_right.png").getImage();
            }
        }

        public void paintTank1(Graphics g) {
            Tank tank = world.getTank(0);
            int nx = tank.getX();
            int ny = tank.getY();
//             System.out.println("x: " + nx + " y: " + ny);
            g.drawImage(imgTank1, nx, ny, tank.getWidth(), tank.getHeight(), null, null);
        }

        public void paintTank2(Graphics g) {
            Tank tank = world.getTank(1);
            int nx = tank.getX();
            int ny = tank.getY();
//             System.out.println("x: " + nx + " y: " + ny);
            g.drawImage(imgTank2, nx, ny, tank.getWidth(), tank.getHeight(), null, null);
        }

        public void paintBulletTank1(Graphics g) {
            for (Bullet bullet : world.getTank(0).getBullets()) {
                turnBullet(bullet);
                int bx = bullet.getX();
                int by = bullet.getY();
                g.drawImage(imgBullet, bx, by, bullet.getWidth(), bullet.getHeight(), null, null);
            }
        }

        public void paintBulletTank2(Graphics g) {
            for (Bullet bullet : world.getTank(1).getBullets()) {
                turnBullet(bullet);
                int bx = bullet.getX();
                int by = bullet.getY();
                g.drawImage(imgBullet, bx, by, bullet.getWidth(), bullet.getHeight(), null, null);
            }
        }
    }

    class PreGameUI extends JPanel {
        private JButton onoPlayer;
        private JButton twoPlayer;

        public PreGameUI() {
            setLayout(null);
            setPreferredSize(new Dimension(
                world.getWidth() * 40,
                world.getHeight() * 40));
            JLabel preGameLabel = new JLabel("Mode");
            preGameLabel.setFont(new Font("Arial", Font.PLAIN, 50));
            preGameLabel.setBounds(200,300,400,100);
            add(preGameLabel);
            pack();
            setButton();
        }

        private void setButton() {
            onePlayerButton();
            twoPlayerButton();
        }

        private void onePlayerButton() {
            onoPlayer = new JButton("Single-Player");
            onoPlayer.setBounds(400,200,400,100);
            onoPlayer.setFont(new Font("Arial", Font.PLAIN, 50));
            onoPlayer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectMode = true;
                    twoPlayer.setEnabled(false);
                    onePlayermode = true;
                    deleteinitPreGame();
                    pack();
                    startGame();
                    
                }
            });
            add(onoPlayer);
        }

        private void twoPlayerButton() {
            twoPlayer = new JButton("Multi-Player");
            twoPlayer.setBounds(400,400,400,100);
            twoPlayer.setFont(new Font("Arial", Font.PLAIN, 50));
            twoPlayer.setPreferredSize(new Dimension(400,100));
            twoPlayer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectMode = true;
                    onoPlayer.setEnabled(false);
                    twoPlayermode = true;
                    deleteinitPreGame();
                    pack();
                    startGame();
                }
            });
            add(twoPlayer);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
