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

        private Image imgTank;
        private Image imgBullet;

        WorldPanel() {
            setPreferredSize(new Dimension(
                    world.getWidth() * PIXEL_SIZE,
                    world.getHeight() * PIXEL_SIZE));

            imageBrick = new ImageIcon("imgs/brick.png").getImage();
            imageSteel = new ImageIcon("imgs/steel.png").getImage();
            imageBush = new ImageIcon("imgs/bush2.png").getImage();

            addKeyListener(new KeyHandler(
                    world.getTank(),
                    KeyEvent.VK_W,
                    KeyEvent.VK_S,
                    KeyEvent.VK_A,
                    KeyEvent.VK_D,
                    KeyEvent.VK_SPACE));
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, world.getWidth() * PIXEL_SIZE, world.getHeight() * PIXEL_SIZE);

            List<Brick> brickList = world.getBrickList();
            List<Steel> steelList = world.getSteelList();
            List<Bush> bushList = world.getBushList();

            paintBrick(g, brickList);
            paintSteel(g, steelList);

            paintBullet(g);
            turnTank();
            paintTank(g);
            paintBush(g, bushList);
        }

        public void paintBrick(Graphics g, List<Brick> brickList) {
            // List<Brick> brickList = world.getBrickList();
            // this.brickList = world.getBrickList();
            for (Brick a : brickList) {
                int x = a.getX() * PIXEL_SIZE;
                int y = a.getY() * PIXEL_SIZE;
                g.drawImage(imageBrick, x, y, PIXEL_SIZE, PIXEL_SIZE, null, null);
            // checkBrick(brickList);
            }
        }

        public void paintSteel(Graphics g, List<Steel> steelList) {
            // List<Steel> steelList = world.getSteelList();
            // this.steelList = world.getSteelList();
            for (Steel a : steelList) {
                int x = a.getX() * PIXEL_SIZE;
                int y = a.getY() * PIXEL_SIZE;
                g.drawImage(imageSteel, x, y, PIXEL_SIZE, PIXEL_SIZE, null, null);
            }
        }

        public void paintBush(Graphics g, List<Bush> bushList) {
            // List<Bush> bushList = world.getBushList();
            for (Bush a : bushList) {
                int x = a.getX() * PIXEL_SIZE;
                int y = a.getY() * PIXEL_SIZE;
                g.drawImage(imageBush, x, y, PIXEL_SIZE, PIXEL_SIZE, null, null);
            }
        }

        public void turnTank() {
            // for (Brick brick : brickList) {

            // }
            switch (world.getTank().getDirection()) {
                case UP -> imgTank = new ImageIcon("img/greenTank_up.png").getImage();
                case DOWN -> imgTank = new ImageIcon("img/greenTank_down.png").getImage();
                case LEFT -> imgTank = new ImageIcon("img/greenTank_left.png").getImage();
                case RIGHT -> imgTank = new ImageIcon("img/greenTank_right.png").getImage();
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

        public void paintTank(Graphics g) {
            Tank tank = world.getTank();
            int nx = tank.getX();
            int ny = tank.getY();
            // System.out.println("x: " + nx + " y: " + ny);
            g.drawImage(imgTank, nx, ny, tank.getWidth(), tank.getHeight(), null, null);
        }

        public void paintBullet(Graphics g) {
            for (Bullet bullet : world.getTank().getBullets()) {
                turnBullet(bullet);
                int bx = bullet.getX();
                int by = bullet.getY();
                g.drawImage(imgBullet, bx, by, bullet.getWidth(), bullet.getHeight(), null, null);
            }
        }

        // public void checkBrick(List<Brick> bricks) {
        //     for (WorldObj brick: bricks) {
        //         // System.out.println(brick.getisBreakable());
        //         // if (!brick.getisBreakable()) {
        //             // System.out.println(!block.getisBreakable());
        //             // continue;
        //         // }

        //     }
        // }

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
