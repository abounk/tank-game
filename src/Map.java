import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Map {
    public List<List<Integer>> listBrick = Arrays.asList();
    public List<List<Integer>> listSteel = Arrays.asList();
    public List<List<Integer>> listBush = Arrays.asList();
    

    public Map(int size) {
        initObstracle();
    }

    private void initObstracle() {
        addBrickToMap();
        addSteelToMap();
        addBushToMap();
    }

    public void addBrickToMap() {
        listBrick = Arrays.asList(
            Arrays.asList(1,1),
            Arrays.asList(2,2)
            );
    }

    public void addSteelToMap() {
        listSteel = Arrays.asList(
            Arrays.asList(3,3),
            Arrays.asList(4,4)
            );
    }

    public void addBushToMap() {
        listBush = Arrays.asList(
            Arrays.asList(5,5),
            Arrays.asList(6,6)
            );
    }

    public List<List<Integer>> getBrickList() {
        return listBrick;
    }

    public List<List<Integer>> getSteelList() {
        return listSteel;
    }

    public List<List<Integer>> getBushList() {
        return listBush;
    }
}
