import java.util.concurrent.ThreadLocalRandom;

public class TankAI extends Tank{
    private boolean changeDirection = true;

    public TankAI(int x, int y) {
        super(x, y, "AI");
        Thread AIThread = new Thread() {
            @Override
            public void run () {
                int timer = 0;
                while (true) {
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timer += 1;
                    if (timer == 20) {
                        changeDirection = true;
                        timer = 0;
                    }
                }
            }
        };
        AIThread.start();
    }

    @Override
    public void animate() {
        super.animate();
        for (Bullet bullet : getBullets()) {
            bullet.animate();
        }
        cleanUpBullets();
        if (changeDirection) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
            switch (randomNum) {
                case 1:
                    setDirection(Direction.UP);
                    break;
                case 2:
                    setDirection(Direction.DOWN);
                    break;
                case 3:
                    setDirection(Direction.LEFT);
                    break;
                case 4:
                    setDirection(Direction.RIGHT);
                    break;
            }
            changeDirection = false;
            shoot();
            move();
        }
    }
}
