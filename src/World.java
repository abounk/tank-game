import java.util.ArrayList;
import java.util.List;

public class World {
    private int width;
    private int height;
    private List<Tank> tanks = new ArrayList<Tank>();

    private BulletPool bulletPool;
    private List<Bullet> bullets;

    private Map map;
    private List<Brick> bricksList;
    private List<Steel> steelList;
    private List<Bush> bushList;
    private List<WorldObj> worldObjList;

    public World(int width, int height) {
        bulletPool = new BulletPool();
        bullets = new ArrayList<Bullet>();

        this.width = width;
        this.height = height;

        this.map = new Map();
        this.bricksList = new ArrayList<Brick>();
        this.steelList = new ArrayList<Steel>();
        this.bushList = new ArrayList<Bush>();

        this.worldObjList = new ArrayList<WorldObj>();

//        x = 0, 1160
//        y = 360
        Tank tank1 = new Tank(0, 50);
        Tank tank2 = new Tank(1160, 50);
        tanks.add(tank1);
        tanks.add(tank2);


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
            Brick brick = new Brick(a.get(0)-1, a.get(1)-1);
            bricksList.add(brick);
            worldObjList.add(brick);
        }
    }

    private void addSteelList() {
        List<List<Integer>> listSteel = this.map.getSteelList();
        for (List<Integer> a: listSteel) {
            Steel steel = new Steel(a.get(0)-1, a.get(1)-1);
            steelList.add(steel);
            worldObjList.add(steel);
        }
    }

    private void addBushList() {
        List<List<Integer>> listBush = this.map.getBushList();
        for (List<Integer> a: listBush) {
            Bush bush = new Bush(a.get(0)-1, a.get(1)-1);
            bushList.add(bush);
            worldObjList.add(bush);
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

    public List<WorldObj> getWorldObjList() {
        return this.worldObjList;
    }

    public void tick() {
//        tank.animate();
        for (Tank tank : tanks) {
            tank.animate();
            tank.checkMove(worldObjList);
            }
    }

    public Tank getTank(int numTank) {
        return this.tanks.get(numTank);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
