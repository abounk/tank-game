import java.util.ArrayList;
import java.util.List;

public class World {
    private int width;
    private int height;
//    private List<Tank> tanks = new ArrayList<Tank>();
    private Tank tank;
    private Bullet bullet;

    private Map map;
    private List<Brick> bricksList;
    private List<Steel> steelList;
    private List<Bush> bushList;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Map();
        this.bricksList = new ArrayList<Brick>();
        this.steelList = new ArrayList<Steel>();
        this.bushList = new ArrayList<Bush>();
        tank = new Tank(10, 10);
        addListObject();
    }

    private void addListObject() {
        addBrickList();
        addSteelList();
        addBushList();
    }

    private void addBrickList() {
        List<List<Integer>> listBrick = this.map.getBrickList();
        for (List<Integer> a: listBrick) {
            bricksList.add(new Brick(a.get(0)-1, a.get(1)-1));
        }
    }

    private void addSteelList() {
        List<List<Integer>> listSteel = this.map.getSteelList();
        for (List<Integer> a: listSteel) {
            steelList.add(new Steel(a.get(0)-1, a.get(1)-1));
        }
    }

    private void addBushList() {
        List<List<Integer>> listBush = this.map.getBushList();
        for (List<Integer> a: listBush) {
            bushList.add(new Bush(a.get(0)-1, a.get(1)-1));
        }
    }

    public List<Brick> getBrickList() {
        return this.bricksList;
    }

    public List<Steel> getSteelList() {
        return this.steelList;
    }

    public List<Bush> getBushList() {
        return this.bushList;
        // tank = new Tank(30, 30);
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
