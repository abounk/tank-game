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

    public KeyHandler(Tank tank, int up, int down, int left, int right, int shoot) {
        this.tank = tank;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.shoot = shoot;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == up) {
            tank.setDirection(Direction.UP);
            currentKey = e.getKeyCode();
            tank.move();
        } else if (e.getKeyCode() == down) {
            tank.setDirection(Direction.DOWN);
            currentKey = e.getKeyCode();
            tank.move();
        } else if (e.getKeyCode() == left) {
            tank.setDirection(Direction.LEFT);
            currentKey = e.getKeyCode();
            tank.move();
        } else if (e.getKeyCode() == right) {
            tank.setDirection(Direction.RIGHT);
            currentKey = e.getKeyCode();
            tank.move();
        } else if (e.getKeyCode() == shoot) {
            tank.shoot();
        }
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
