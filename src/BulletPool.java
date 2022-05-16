import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BulletPool {
    public static final int BULLET_SIZE = 3;

    private List<Bullet> bullets = new ArrayList<Bullet>();
    private List<Bullet> usedBullet = new ArrayList<Bullet>();

    public static final int RELOAD_TIME = 200;
    private Thread reloadingThread;
    private boolean isReloading = false;

    public BulletPool() {
        for(int i = 0; i < BULLET_SIZE; i ++) {
            bullets.add(new Bullet());
        }
    }

    public Bullet requestBullet() {
        if (bullets.isEmpty()) {
            reload();
        }
        Bullet bullet = bullets.remove(0);
        return bullet;
    }

    public void returnBullet(Bullet bullet) {
        if (bullet.getIsBorderHit()) {
            usedBullet.add(bullet);
        }
    }

    private void reload() {
        if (!isReloading) {
            isReloading = true;
            reloadingThread = new Thread() {
                public void run() {
                    while (isReloading) {
                        try {
                            sleep(RELOAD_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Bullet bullet = usedBullet.remove(0);
                        bullets.add(bullet);
                        if (usedBullet.isEmpty()) {
                            isReloading = false;
                        }
                    }
                }
            };
            this.reloadingThread.start();
        }
    }

    public boolean getIsReloading() {
        return isReloading;
    }
}
