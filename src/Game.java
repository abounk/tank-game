import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Game extends JFrame {
    private Thread gameThread;
    private WorldPanel worldPanel;
    private World world;

    public Game() {
        world = new World(30, 20);
        worldPanel = new WorldPanel();
        add(worldPanel);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        worldPanel.requestFocus();
        setTitle("Tank game");
        setAlwaysOnTop(true);
        setResizable(false);
    }

    public void start() {
        setVisible(true);
        gameThread = new Thread() {
            public void run() {
                while (true) {
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

    class WorldPanel extends JPanel {
        public static final int PIXEL_SIZE = 40;

        private List<Brick> brickList;
        private List<Steel> steelList;
        private List<Bush> bushList;

        private Image imageBrick;
        private Image imageSteel;
        private Image imageBush;

        // public WorldPanel() {
        //     setPreferredSize(new Dimension(10 * PIXEL_SIZE, 10 * PIXEL_SIZE));
        //     imageBrick = new ImageIcon("imgs/brick.png").getImage();
        //     imageSteel = new ImageIcon("imgs/steel.png").getImage();
        //     imageBush = new ImageIcon("imgs/bush.png").getImage();
        // }
        private Image imgTank;

        WorldPanel() {
            setPreferredSize(new Dimension(
                    world.getWidth() * PIXEL_SIZE,
                    world.getHeight() * PIXEL_SIZE
            ));

            imageBrick = new ImageIcon("imgs/brick.png").getImage();
            imageSteel = new ImageIcon("imgs/steel.png").getImage();
            imageBush = new ImageIcon("imgs/bush2.png").getImage();

            // imgTank = new ImageIcon()

            addKeyListener(new KeyHandler(
                    world.getTank(),
                    KeyEvent.VK_W,
                    KeyEvent.VK_S,
                    KeyEvent.VK_A,
                    KeyEvent.VK_D
            ));

        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, world.getWidth() * PIXEL_SIZE, world.getHeight() * PIXEL_SIZE);

            paintBrick(g);
            paintSteel(g);
            // paintBush(g);
            // g.setColor(Color.BLACK);
            // g.fillRect(0, 0, world.getWidth() * PIXEL_SIZE, world.getHeight() * PIXEL_SIZE);

            turnTank();
            paintTank(g);
            paintBush(g);
        }

        public void paintBrick(Graphics g) {
            List<Brick> brickList = world.getBrickList();
            for (Brick a: brickList) {
                int x = a.getX() * PIXEL_SIZE;
                int y = a.getY() * PIXEL_SIZE;
                g.drawImage(imageBrick, x, y, PIXEL_SIZE, PIXEL_SIZE, null, null);
            }
        }

        public void paintSteel(Graphics g) {
            List<Steel> steelList = world.getSteelList();
            for (Steel a: steelList) {
                int x = a.getX() * PIXEL_SIZE;
                int y = a.getY() * PIXEL_SIZE;
                g.drawImage(imageSteel, x, y, PIXEL_SIZE, PIXEL_SIZE, null, null);
            }
        }

        public void paintBush(Graphics g) {
            List<Bush> bushList = world.getBushList();
            for (Bush a: bushList) {
                int x = a.getX() * PIXEL_SIZE;
                int y = a.getY() * PIXEL_SIZE;
                g.drawImage(imageBush, x, y, PIXEL_SIZE, PIXEL_SIZE, null, null);
            }
            // g.setColor(Color.BLACK);
            // g.fillRect(0, 0, world.getWidth() * PIXEL_SIZE, world.getHeight() * PIXEL_SIZE);

            // turnTank();
            // paintTank(g);
        }

        public void turnTank() {
            switch (world.getTank().getDirection()) {
                case UP -> imgTank = new ImageIcon("img/greenTank_up.png").getImage();
                case DOWN -> imgTank = new ImageIcon("img/greenTank_down.png").getImage();
                case LEFT -> imgTank = new ImageIcon("img/greenTank_left.png").getImage();
                case RIGHT -> imgTank = new ImageIcon("img/greenTank_right.png").getImage();
            }
        }

        public void paintTank(Graphics g) {
            Tank tank = world.getTank();
            int nx = tank.getX();
            int ny = tank.getY();
        //    System.out.println("x: " + nx + " y: " + ny);
            g.drawImage(imgTank, nx, ny, tank.getWidth(), tank.getHeight(), null, null);
        }
    }



    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
