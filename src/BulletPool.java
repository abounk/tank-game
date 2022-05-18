import java.util.ArrayList;
import java.util.List;

public class BulletPool {
    public static final int BULLET_SIZE = 1;

    private List<Bullet> bullets = new ArrayList<Bullet>();

    private boolean isReloading = false;

    public BulletPool() {
        for (int i = 0; i < BULLET_SIZE; i++) {
            bullets.add(new Bullet());
        }
    }

    public Bullet requestBullet() {
        if (bullets.isEmpty()) {
            throw new Error("Out of ammo!");
        }
        Bullet bullet = bullets.remove(0);
        return bullet;
    }

    public void returnBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public boolean getIsReloading() {
        return isReloading;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}
