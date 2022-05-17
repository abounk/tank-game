import java.awt.*;
import java.util.List;

public class MovingObj extends WorldObj {
    private Direction direction;
    private int speed;

    private boolean isMoving = false;
    private boolean isBorderHit = false;

    private boolean isCollision = false;

    public MovingObj(int x, int y) {
        super(x, y);
        this.speed = 1;
        this.direction = Direction.UP;
    }

    public MovingObj(int x, int y, int speed) {
        super(x, y);
        this.speed = speed;
        this.direction = Direction.UP;
    }

    public void move() {
        this.isMoving = true;
        this.isBorderHit = false;
    }

    public void stop() {
        this.isMoving = false;
    }

    @Override
    public void animate() {
        if (!isMoving || isCollision) {
            return;
        }
        int x = this.getX() + (direction.getX() * speed);
        int y = this.getY() + (direction.getY() * speed);

        if (x < 0 ||
                y < 0 ||
                x + this.getWidth() > 30 * Game.WorldPanel.PIXEL_SIZE ||
                y + this.getHeight() > 20 * Game.WorldPanel.PIXEL_SIZE) {
            this.isBorderHit = true;
            this.stop();
            return;
        }
        this.setPosition(x, y);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean getIsBorderHit() {
        return isBorderHit;
    }

    public int getSpeed() {
        return speed;
    }
}
