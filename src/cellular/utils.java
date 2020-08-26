package cellular;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class utils {
    public final static Map<String, Integer> itemNumber = new HashMap<String, Integer>();
    public final static Map<String, Integer> actionNumber = new HashMap<String, Integer>();

    public final static Map<String, Integer> directionNumber = new HashMap<String, Integer>();
    public final static HashMap<Integer, int[]> directionMap = new HashMap<Integer, int[]>();

    static {
        itemNumber.put("space", 0);
        itemNumber.put("food", 1);
        itemNumber.put("wall", 2);
        itemNumber.put("cell", 3);
        itemNumber.put("dead cell", 4);

        actionNumber.put("stay", 0);
        actionNumber.put("eat", 1);
        actionNumber.put("move", 2);
        actionNumber.put("cw", 3);
        actionNumber.put("ccw", 4);
        actionNumber.put("attack", 5);

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
            strRet.append(Integer.toString(i));
        }
        return strRet.toString();
    }

}
