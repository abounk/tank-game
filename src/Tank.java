import java.util.ArrayList;
import java.util.List;

public class Tank extends MovingObj{
    private BulletPool bulletPool = new BulletPool();
    private List<Bullet> bullets = new ArrayList<Bullet>();

    public Tank(int x, int y) {
        super(x, y);
        this.setSize(2, 2);
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
        } catch (Error e) {
            return;
        }

        int x = 0;
        int y = 0;

        switch (this.getDirection()) {
            case UP:
                x = this.getX() + (this.getWidth() / 3);
                y = this.getY() + 1;
                break;
            case DOWN:
                x = this.getX() + (this.getWidth() / 3);
                y = this.getY() - 1;
                break;
            case LEFT:
                x = this.getX() - 1;
                y = this.getY() + (this.getHeight() / 3);
                break;
            case RIGHT:
                x = this.getX() + 1;
                y = this.getY() + (this.getHeight() / 3);
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


    public BulletPool getBulletPool() {
        return this.bulletPool;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void cleanUpBullets() {
        List<Bullet> toRemove = new ArrayList<Bullet>();
        for (Bullet bullet : bullets) {
            if (bullet.getIsBorderHit()) {
                toRemove.add(bullet);
            }
        }
        for (Bullet bullet : toRemove) {
            bullets.remove(bullet);
            bulletPool.returnBullet(bullet);
        }
    }
}
