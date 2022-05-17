import java.util.ArrayList;
import java.util.List;

public class World {
    private int width;
    private int height;
    private List<Tank> tanks = new ArrayList<Tank>();
//    private Tank tank;

    private BulletPool bulletPool;
    private List<Bullet> bullets;

    private Map map;
    private List<Brick> bricksList;
    private List<Steel> steelList;
    private List<Bush> bushList;

    private boolean isOver;

    private List<Brick> brickNeedToRemove;

    public World(int width, int height) {
        bulletPool = new BulletPool();
        bullets = new ArrayList<Bullet>();

        this.width = width;
        this.height = height;
        this.map = new Map();
        this.bricksList = new ArrayList<Brick>();
        this.steelList = new ArrayList<Steel>();
        this.bushList = new ArrayList<Bush>();
        this.isOver = false;
        // tank = new Tank(569, 622);

        this.brickNeedToRemove = new ArrayList<Brick>();

        tanks.add(new Tank(0, 360));
        tanks.add(new Tank(1160, 360));

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
    }

    public void tick() {
        for (Tank tank : tanks) {
            tank.animate();
        }
        // checkBreak();
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

    public boolean hitBrick(int x, int y) {
        return bricksList.stream().anyMatch(brick -> brick.getX() == x &&
            brick.getY() == y);
    }

    public boolean hitSteel(int x, int y) {
        return steelList.stream().anyMatch(steel -> steel.getX() == x && steel.getY() == y);
    }

    public boolean getisOver() {
        return isOver;
    }

}
