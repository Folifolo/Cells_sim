package cellular;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class utils {
    public enum Item {
        space, food, wall, cell, deadCell
    }
    public enum Action {
        stay, eat, move, cw, ccw, attack
    }

    public final static Map<String, Integer> directionNumber = new HashMap<String, Integer>();
    public final static HashMap<Integer, int[]> directionMap = new HashMap<Integer, int[]>();

    static {

        directionNumber.put("up", 0);
        directionNumber.put("right", 1);
        directionNumber.put("down", 2);
        directionNumber.put("left", 3);

        directionMap.put(0, new int[]{0, -1});
        directionMap.put(1, new int[]{1, 0});
        directionMap.put(2, new int[]{0, 1});
        directionMap.put(3, new int[]{-1, 0});
    }

    public static String IntArrayToString(@NotNull int[] array) {
        StringBuilder strRet= new StringBuilder();
        for(int i : array) {
            strRet.append(i);
        }
        return strRet.toString();
    }

}
