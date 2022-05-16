import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BulletPool {
    public static final int BULLET_SIZE = 3;
    public static final int RELOAD_TIME = 2;

    private List<Bullet> bullets = new ArrayList<Bullet>();
    private List<Bullet> usedBullet = new ArrayList<Bullet>();

    private Thread reloadingThread;
    private boolean isReloading = false;

    public BulletPool() {
        for(int i = 0; i < BULLET_SIZE; i ++) {
            bullets.add(new Bullet());
        }
    }

    public Bullet requestBullet() {
        if (bullets.isEmpty()) {
            throw new Error("No bullet left.");
        }
        Bullet bullet = bullets.remove(0);
        return bullet;
    }

    public void returnBullet(Bullet bullet) {
        bullet.setPosition(-999, -999);
        bullet.stop();
        this.usedBullet.add(bullet);
        if (!isReloading) {
            isReloading = true;
            this.reloadingThread = new Thread() {
                public void run() {
                    while (isReloading) {
                        try {
                            sleep(RELOAD_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (usedBullet.isEmpty()) {
                            isReloading = false;
                        }
                        Bullet bullet = usedBullet.remove(0);
                        bullets.add(bullet);
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
