public class MovingObj extends WorldObj{
    private Direction direction;

    private boolean isMoving = false;

    public MovingObj (int x, int y) {
        super(x, y);
        this.direction = Direction.UP;
    }

    public void move() {
        this.isMoving = true;
    }

    public  void stop() {
        this.isMoving = false;
    }

    @Override
    public void animate() {
        if (!isMoving) {
            return;
        }
        int x = this.getX() + direction.getX();
        int y = this.getY() + direction.getY();

        this.setPosition(x, y);
//        System.out.println("x: " + getX() + " y: " + getY());
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

}

