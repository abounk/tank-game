public class Tank extends WorldObj{
    private Direction direction;

    private Tank(int x, int y) {
        super(x, y);
        direction = Direction.UP;
    }

    public void move() {

    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }


}
