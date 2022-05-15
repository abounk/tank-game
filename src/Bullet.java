public class Bullet extends MovingObj {
    public static int SHELL_SPEED = 5;

    public Bullet () {
        super(-1, -1, SHELL_SPEED);
        this.setSize(1, 1);
    }

    public Bullet (int x, int y) {
        super(x, y, SHELL_SPEED);
    }
}
