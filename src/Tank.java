import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tank extends MovingObj {
    private BulletPool bulletPool = new BulletPool();
    private List<Bullet> bullets = new ArrayList<Bullet>();

    private String name;
    private boolean isAlive = true;

    public Tank(int x, int y, String name) {
        super(x, y);
        this.setSize(1, 1);
        this.name = name;
    }

    @Override
    public void animate() {
        super.animate();
        for (Bullet bullet : bullets) {
            bullet.animate();
        }
        cleanUpBullets();
    }

    public void shoot() {
        Bullet bullet;
        try {
            bullet = bulletPool.requestBullet();
            bullet.setOwner(this);
        } catch (Error e) {
            return;
        }

        int x, y;
        switch (this.getDirection()) {
            case UP:
                x = this.getX();
                y = this.getY() + 1;
                break;
            case DOWN:
                x = this.getX();
                y = this.getY() - 1;
                break;
            case LEFT:
                x = this.getX() - 1;
                y = this.getY();
                break;
            case RIGHT:
                x = this.getX() + 1;
                y = this.getY();
                break;
            default:
                x = this.getX();
                y = this.getY();
                break;
        }
        bullet.setPosition(x, y);
        bullet.setDirection(this.getDirection());
        bullet.move();
        bullets.add(bullet);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void isHit(Bullet bullet) {
        Rectangle bulletArea = new Rectangle(
                bullet.getX(),
                bullet.getY(),
                bullet.getWidth(),
                bullet.getHeight());
        Rectangle thisArea = new Rectangle(
                getX(),
                getY(),
                getWidth(),
                getHeight());
        if (thisArea.intersects(bulletArea)) {
            if (bullet.getOwner() != this) {
                isAlive = false;
            }
        }
    }

    public void checkHit(List<WorldObj> blockList) {
        for (WorldObj obj : blockList) {
            if (obj instanceof Bush) {
                continue;
            }
            Rectangle otherArea = new Rectangle(
                    obj.getX() * Game.WorldPanel.PIXEL_SIZE,
                    obj.getY() * Game.WorldPanel.PIXEL_SIZE,
                    obj.getWidth(),
                    obj.getHeight());
            Rectangle thisArea = new Rectangle(
                    getX(),
                    getY(),
                    getWidth(),
                    getHeight());
            if (thisArea.intersects(otherArea)) {
                // System.out.println("hit");
                setPosition(
                        getX() - getDirection().getX() * getSpeed(),
                        getY() - getDirection().getY() * getSpeed());
                stop();
            }
        }
    }

    public void cleanUpBullets() {
        List<Bullet> toRemove = new ArrayList<Bullet>();
        for (Bullet bullet : bullets) {
            if (bullet.getIsBorderHit() || bullet.getIsBlockHit()) {
                toRemove.add(bullet);
                bullet.cleanBullet();
            }
        }
        for (Bullet bullet : toRemove) {
            bulletPool.returnBullet(bullet);
            bullets.remove(bullet);
        }
    }

    public String getName() {
        return this.name;
    }

    public BulletPool getBulletPool() {
        return bulletPool;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
