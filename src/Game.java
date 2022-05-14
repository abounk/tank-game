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
    }

    public void start() {
        setVisible(true);
        gameThread = new Thread() {
            public void run() {
                while (true) {
                    world.tick();
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
    }

    class WorldPanel extends JPanel {
        public static final int PIXEL_SIZE = 20;
        WorldPanel() {
            setPreferredSize(new Dimension(
                    world.getWidth() * PIXEL_SIZE,
                    world.getHeight() * PIXEL_SIZE
            ));
        }
        @Override
        public void paint(Graphics g) {
            super.paint(g);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
