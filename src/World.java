import java.util.ArrayList;
import java.util.List;

public class World {
    private int width;
    private int height;
//    private List<Tank> tanks = new ArrayList<Tank>();
    private Tank tank;
    private Bullet bullet;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        tank = new Tank(30, 30);
    }

    public void tick() {
        tank.animate();
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
