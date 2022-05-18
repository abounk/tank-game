import java.util.ArrayList;
import java.util.List;

public class World {
    private int width;
    private int height;
    private List<Tank> tanks = new ArrayList<Tank>();

    private BulletPool bulletPool;
    private List<Bullet> bullets;

    private Map map;
    private List<WorldObj> worldObjList;
    private List<WorldObj> toRemove;

    private Tank tank1;
    private Tank tank2;

    private boolean isOver;
    private boolean twoPlayermode = false;

    public World(int width, int height) {
        bulletPool = new BulletPool();
        bullets = new ArrayList<Bullet>();

        this.width = width;
        this.height = height;

        this.map = new Map();
        this.worldObjList = new ArrayList<WorldObj>();
        this.toRemove = new ArrayList<WorldObj>();
        this.isOver = false;

        tank1 = new Tank(0, 360);
        tanks.add(tank1);

        tank2 = new Tank(1160, 360);
        tanks.add(tank2);
        

        addListObject();
    }

    private void addListObject() {
        addBrick();
        addSteel();
        addBush();
    }

    private void addBrick() {
        List<List<Integer>> listBrick = this.map.getBrickList();
        for (List<Integer> a : listBrick) {
            Brick brick = new Brick(a.get(0) - 1, a.get(1) - 1);
            worldObjList.add(brick);
        }
    }

    private void addSteel() {
        List<List<Integer>> listSteel = this.map.getSteelList();
        for (List<Integer> a : listSteel) {
            Steel steel = new Steel(a.get(0) - 1, a.get(1) - 1);
            worldObjList.add(steel);
        }
    }

    private void addBush() {
        List<List<Integer>> listBush = this.map.getBushList();
        for (List<Integer> a : listBush) {
            Bush bush = new Bush(a.get(0) - 1, a.get(1) - 1);
            worldObjList.add(bush);
        }
    }

    public List<WorldObj> getWorldObjList() {
        return this.worldObjList;
    }

    public void tick() {
        for (Tank tank : tanks) {
            tank.animate();
            tank.checkHit(worldObjList);
            for (Bullet bullet : tank.getBullets()) {
                WorldObj hitBlock = bullet.checkHit(worldObjList);
                if (hitBlock != null) {
                    toRemove.add(hitBlock);
                }
            }
            for (WorldObj block : toRemove) {
                worldObjList.remove(block);
            }
            checkTankShot();
        }
    }

    public void checkTankShot() {
        if (this.twoPlayermode) {
            for (Tank tank : tanks) {
                for (Bullet bullet : tank.getBullets()) {
                    if (tank1.isHit(bullet) || tank2.isHit(bullet)) {
                        this.isOver = true;
                    }
                }
            }
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

    public boolean getisOver() {
        return this.isOver;
    }

    public void setGameMode2Player() {
        twoPlayermode = true;
    }

}
