public class World {
    private int width;
    private int height;
    private Tank tank;
    private Bullet bullet;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void tick() {
    }

    public Tank getTank() {
        return this.tank;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
