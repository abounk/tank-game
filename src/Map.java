import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Map {
    private List<List<Integer>> listBrick = Arrays.asList();
    private List<List<Integer>> listSteel = Arrays.asList();
    private List<List<Integer>> listBush = Arrays.asList();
    

    // public Map(int size) {
    public Map() {
        initObstracle();
    }

    private void initObstracle() {
        addBrickToMap();
        addSteelToMap();
        addBushToMap();
    }

    public void addBrickToMap() {
        listBrick = Arrays.asList(
            Arrays.asList(2,1),
            Arrays.asList(2,2)
            );
    }

    public void addSteelToMap() {

        listSteel = Arrays.asList();
        for (int i=1;i<41;i++) {
            
        }

        listSteel = Arrays.asList(
            Arrays.asList(1,1),
            Arrays.asList(1,2),
            Arrays.asList(1,3),
            Arrays.asList(1,4),
            Arrays.asList(1,5),
            Arrays.asList(1,6),
            Arrays.asList(1,7),
            Arrays.asList(1,8),
            Arrays.asList(1,9),
            Arrays.asList(1,10),
            Arrays.asList(1,11),
            Arrays.asList(1,12),
            Arrays.asList(1,13),
            Arrays.asList(1,14),
            Arrays.asList(1,15),
            Arrays.asList(1,16),
            Arrays.asList(1,17),
            Arrays.asList(1,18),
            Arrays.asList(1,19),
            Arrays.asList(1,20),
            Arrays.asList(1,21),
            Arrays.asList(1,22),
            Arrays.asList(1,23),
            Arrays.asList(1,24),
            Arrays.asList(1,25),
            Arrays.asList(1,26),
            Arrays.asList(1,27),
            Arrays.asList(1,28),
            Arrays.asList(1,29),
            Arrays.asList(1,30),
            Arrays.asList(1,31),
            Arrays.asList(1,32),
            Arrays.asList(1,33),
            Arrays.asList(1,34),
            Arrays.asList(1,35),
            Arrays.asList(1,36),
            Arrays.asList(1,37),
            Arrays.asList(1,38),
            Arrays.asList(1,39),
            Arrays.asList(1,40)
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
