import java.util.ArrayList;
import java.util.List;

public class World {
    private int width;
    private int height;
    private Tank tank;
    private Bullet bullet;

    private Map map;
    private List<Brick> bricksList;
    private List<Steel> steelList;
    private List<Bush> bushList;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.bricksList = new ArrayList<Brick>();
        this.steelList = new ArrayList<Steel>();
        this.bushList = new ArrayList<Bush>();
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
            bricklist.add(new Brick(a.get(0)-1, a.get(1)-1));
        }
    }

    private void addSteelList() {
        List<List<Integer>> listSteel = this.map.getSteelList();
        for (List<Integer> a: listSteel) {
            steellist.add(new Steel(a.get(0)-1, a.get(1)-1));
        }
    }

    private void addBushList() {
        List<List<Integer>> listBush = this.map.getBushList();
        for (List<Integer> a: listBush) {
            bushlist.add(new Bush(a.get(0)-1, a.get(1)-1));
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
