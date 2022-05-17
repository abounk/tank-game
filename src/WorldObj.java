import javax.swing.text.html.parser.Entity;
import java.awt.*;

public class WorldObj {
    private int x;
    private int y;

    private int width = Game.WorldPanel.PIXEL_SIZE;
    private int height = Game.WorldPanel.PIXEL_SIZE;

    public WorldObj(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isHit(WorldObj other) {
        return (
                getX() < other.getX() + other.getWidth() &&
                getX() + getWidth() > other.getX() &&
                getY() < other.getY() + other.getWidth() &&
                getY() + getHeight() > other.getY()
                );
    }

    public void animate() {
    }

    public void setSize(int width, int height) {
        this.width = Game.WorldPanel.PIXEL_SIZE * width;
        this.height = Game.WorldPanel.PIXEL_SIZE * height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean canHit() {
        return true;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


}
