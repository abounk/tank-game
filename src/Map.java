import java.util.Arrays;
import java.util.List;

public class Map {
    private List<List<Integer>> listBrick = Arrays.asList();
    private List<List<Integer>> listSteel = Arrays.asList();
    private List<List<Integer>> listBush = Arrays.asList();

    public Map() {
        initObstacle();
    }

    private void initObstacle() {
        addBrickToMap();
        addSteelToMap();
        addBushToMap();
    }

    public void addBrickToMap() {
        listBrick = Arrays.asList(
                Arrays.asList(3, 9),
                Arrays.asList(3, 10),
                Arrays.asList(3, 11),
                Arrays.asList(4, 9),
                Arrays.asList(4, 10),
                Arrays.asList(4, 11),
                Arrays.asList(27, 9),
                Arrays.asList(27, 10),
                Arrays.asList(27, 11),
                Arrays.asList(28, 9),
                Arrays.asList(28, 10),
                Arrays.asList(28, 11),
                Arrays.asList(8, 4),
                Arrays.asList(8, 5),
                Arrays.asList(8, 6),
                Arrays.asList(8, 7),
                Arrays.asList(8, 8),
                Arrays.asList(8, 9),
                Arrays.asList(8, 10),
                Arrays.asList(8, 11),
                Arrays.asList(8, 12),
                Arrays.asList(8, 13),
                Arrays.asList(8, 14),
                Arrays.asList(8, 15),
                Arrays.asList(8, 16),
                Arrays.asList(23, 4),
                Arrays.asList(23, 5),
                Arrays.asList(23, 6),
                Arrays.asList(23, 7),
                Arrays.asList(23, 8),
                Arrays.asList(23, 9),
                Arrays.asList(23, 10),
                Arrays.asList(23, 11),
                Arrays.asList(23, 12),
                Arrays.asList(23, 13),
                Arrays.asList(23, 14),
                Arrays.asList(23, 15),
                Arrays.asList(23, 16),
                Arrays.asList(10, 7),
                Arrays.asList(10, 8),
                Arrays.asList(10, 9),
                Arrays.asList(10, 10),
                Arrays.asList(10, 11),
                Arrays.asList(10, 12),
                Arrays.asList(10, 13),
                Arrays.asList(21, 7),
                Arrays.asList(21, 8),
                Arrays.asList(21, 9),
                Arrays.asList(21, 10),
                Arrays.asList(21, 11),
                Arrays.asList(21, 12),
                Arrays.asList(21, 13),
                Arrays.asList(20, 7),
                Arrays.asList(19, 6),
                Arrays.asList(18, 5),
                Arrays.asList(20, 13),
                Arrays.asList(19, 14),
                Arrays.asList(18, 15),
                Arrays.asList(11, 7),
                Arrays.asList(12, 6),
                Arrays.asList(13, 5),
                Arrays.asList(11, 13),
                Arrays.asList(12, 14),
                Arrays.asList(13, 15),
                Arrays.asList(14, 7),
                Arrays.asList(15, 7),
                Arrays.asList(16, 7),
                Arrays.asList(17, 7),
                Arrays.asList(14, 13),
                Arrays.asList(15, 13),
                Arrays.asList(16, 13),
                Arrays.asList(17, 13),
                Arrays.asList(13, 8),
                Arrays.asList(18, 8),
                Arrays.asList(13, 12),
                Arrays.asList(18, 12));
    }

    public void addSteelToMap() {
        listSteel = Arrays.asList(
                Arrays.asList(1, 7),
                Arrays.asList(1, 8),
                Arrays.asList(2, 7),
                Arrays.asList(2, 8),
                Arrays.asList(3, 7),
                Arrays.asList(3, 8),
                Arrays.asList(4, 7),
                Arrays.asList(4, 8),
                Arrays.asList(1, 12),
                Arrays.asList(1, 13),
                Arrays.asList(2, 12),
                Arrays.asList(2, 13),
                Arrays.asList(3, 12),
                Arrays.asList(3, 13),
                Arrays.asList(4, 12),
                Arrays.asList(4, 13),
                Arrays.asList(30, 7),
                Arrays.asList(30, 8),
                Arrays.asList(29, 7),
                Arrays.asList(29, 8),
                Arrays.asList(28, 7),
                Arrays.asList(28, 8),
                Arrays.asList(27, 7),
                Arrays.asList(27, 8),
                Arrays.asList(30, 12),
                Arrays.asList(30, 13),
                Arrays.asList(29, 12),
                Arrays.asList(29, 13),
                Arrays.asList(28, 12),
                Arrays.asList(28, 13),
                Arrays.asList(27, 12),
                Arrays.asList(27, 13),
                Arrays.asList(14, 5),
                Arrays.asList(15, 5),
                Arrays.asList(16, 5),
                Arrays.asList(17, 5),
                Arrays.asList(14, 15),
                Arrays.asList(15, 15),
                Arrays.asList(16, 15),
                Arrays.asList(17, 15));
    }

    public void addBushToMap() {
        listBush = Arrays.asList(
                Arrays.asList(5, 5),
                Arrays.asList(6, 5),
                Arrays.asList(7, 5),
                Arrays.asList(5, 6),
                Arrays.asList(6, 6),
                Arrays.asList(7, 6),
                Arrays.asList(5, 7),
                Arrays.asList(6, 7),
                Arrays.asList(7, 7),
                Arrays.asList(5, 8),
                Arrays.asList(6, 8),
                Arrays.asList(7, 8),
                Arrays.asList(5, 9),
                Arrays.asList(6, 9),
                Arrays.asList(7, 9),
                Arrays.asList(5, 10),
                Arrays.asList(6, 10),
                Arrays.asList(7, 10),
                Arrays.asList(5, 11),
                Arrays.asList(6, 11),
                Arrays.asList(7, 11),
                Arrays.asList(5, 12),
                Arrays.asList(6, 12),
                Arrays.asList(7, 12),
                Arrays.asList(5, 13),
                Arrays.asList(6, 13),
                Arrays.asList(7, 13),
                Arrays.asList(5, 14),
                Arrays.asList(6, 14),
                Arrays.asList(7, 14),
                Arrays.asList(5, 15),
                Arrays.asList(6, 15),
                Arrays.asList(7, 15),
                Arrays.asList(24, 5),
                Arrays.asList(25, 5),
                Arrays.asList(26, 5),
                Arrays.asList(24, 6),
                Arrays.asList(25, 6),
                Arrays.asList(26, 6),
                Arrays.asList(24, 7),
                Arrays.asList(25, 7),
                Arrays.asList(26, 7),
                Arrays.asList(24, 8),
                Arrays.asList(25, 8),
                Arrays.asList(26, 8),
                Arrays.asList(24, 9),
                Arrays.asList(25, 9),
                Arrays.asList(26, 9),
                Arrays.asList(24, 10),
                Arrays.asList(25, 10),
                Arrays.asList(26, 10),
                Arrays.asList(24, 11),
                Arrays.asList(25, 11),
                Arrays.asList(26, 11),
                Arrays.asList(24, 12),
                Arrays.asList(25, 12),
                Arrays.asList(26, 12),
                Arrays.asList(24, 13),
                Arrays.asList(25, 13),
                Arrays.asList(26, 13),
                Arrays.asList(24, 14),
                Arrays.asList(25, 14),
                Arrays.asList(26, 14),
                Arrays.asList(24, 15),
                Arrays.asList(25, 15),
                Arrays.asList(26, 15));
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
