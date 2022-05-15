import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    private Tank tank;

    private int up;
    private int down;
    private int left;
    private int right;
    private int shoot;

    private int currentKey;

    public KeyHandler (Tank tank, int up, int down, int left, int right) {
        this.tank = tank;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == up) {
            tank.setDirection(Direction.UP);
            tank.move();
        } else if (e.getKeyCode() == down) {
            tank.setDirection(Direction.DOWN);
            tank.move();
        } else if (e.getKeyCode() == left) {
            tank.setDirection(Direction.LEFT);
            tank.move();
        } else if (e.getKeyCode() == right) {
            tank.setDirection(Direction.RIGHT);
            tank.move();
        }
        currentKey = e.getKeyCode();
        }

    @Override
    public void keyReleased(KeyEvent e) {
        if ((e.getKeyCode() == up && currentKey == e.getKeyCode())
                || (e.getKeyCode() == down && currentKey == e.getKeyCode())
                || (e.getKeyCode() == left && currentKey == e.getKeyCode())
                || (e.getKeyCode() == right && currentKey == e.getKeyCode())) {
            tank.stop();
        }
    }
}
