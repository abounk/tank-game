import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JFrame {
    private Thread gameThread;
    private WorldPanel worldPanel;
    private World world;

    public Game() {
        world = new World(60, 40);
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
        public static final int PIXEL_SIZE = 20;

        private Image imgTank;

        WorldPanel() {
            setPreferredSize(new Dimension(
                    world.getWidth() * PIXEL_SIZE,
                    world.getHeight() * PIXEL_SIZE
            ));
            addKeyListener(new KeyHandler(
                    world.getTank(),
                    KeyEvent.VK_W,
                    KeyEvent.VK_S,
                    KeyEvent.VK_A,
                    KeyEvent.VK_D
            ));

            imgTank = new ImageIcon("img/greenTank_right.png").getImage();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, world.getWidth() * PIXEL_SIZE, world.getHeight() * PIXEL_SIZE);

            paintTank(g);
        }

        public void paintTank(Graphics g) {
            Tank tank = world.getTank();
            int nx = tank.getX();
            int ny = tank.getY();
            System.out.println("x: " + nx + " y: " + ny);
            g.drawImage(imgTank, nx, ny, PIXEL_SIZE * 3, PIXEL_SIZE * 3, null, null);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
