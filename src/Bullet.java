import java.awt.*;
import java.util.List;

public class Bullet extends MovingObj {
    public static int SHELL_SPEED = 3;

    private boolean isBlockHit = false;

    private Tank owner;

    public Bullet() {
        super(-1, -1, SHELL_SPEED);
        this.setSize(1, 1);
    }

    public Bullet(int x, int y) {
        super(x, y, SHELL_SPEED);
    }

    public WorldObj checkHit(List<WorldObj> blocklist) {
        isBlockHit = false;
        for (WorldObj obj : blocklist) {
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
                isBlockHit = true;
                if (obj instanceof Steel) {
                    return null;
                }
                return obj;
            }
        }
        return null;
    }

    public boolean getIsBlockHit() {
        return isBlockHit;
    }

    public void cleanBullet() {
        isBlockHit = false;
    }

    public void setOwner(Tank tank) {
        this.owner = tank;
    }

    public Tank getOwner() {
        return this.owner;
    }
}
