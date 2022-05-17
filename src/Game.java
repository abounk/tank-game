import javax.swing.*;

import java.awt.*;
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

            paintBrick(g);
            paintSteel(g);

            paintBulletTank1(g);
            paintBulletTank2(g);

            turnTank1();
            turnTank2();
            paintTank1(g);
            paintTank2(g);

            paintBush(g);

            paintRectangle(g);
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

        public void paintRectangle(Graphics g) {
            g.setColor(Color.white);
            for (WorldObj block : world.getWorldObjList()) {
//                System.out.println("x: " + block.getX() + " y: " + block.getY());
                g.drawRect(block.getX() * PIXEL_SIZE, block.getY() * PIXEL_SIZE, block.getWidth(), block.getHeight());
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
