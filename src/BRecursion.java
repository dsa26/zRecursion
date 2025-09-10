import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class BRecursion {
    /*
        Row i has i + 1 people
        So if a person is index 0 or i, then they only have one person above them
        If it's one person, add 100, if not, add 200
        Simplest way is using different logic for 0, i, and every other index
    */

    public static double memoizedWeightOnBackOf (int row, int col, Map<String, Double> table) {
        String key = Integer.toString(row) + ", " + Integer.toString(col);
        if (col > row) {
            throw new IllegalArgumentException(String.format("row %d cannot have more than %d people", row, row + 1));
        }
        if (row == 0) {
            return 0;
        }
        if (table.containsKey(key)) {
            return table.get(key);
        }
        if (col == 0) {
            double val = 100 + 0.5 * memoizedWeightOnBackOf(row - 1, 0, table);
            table.put(key, val);
            return val;
        } else if (col == row) {
            double val = 100 + 0.5 * memoizedWeightOnBackOf(row - 1, col - 1, table);
            table.put(key, val);
            return val;
        } else {
            double val = 200 + 0.5 * (memoizedWeightOnBackOf(row - 1, col - 1, table) + memoizedWeightOnBackOf(row - 1, col, table));
            table.put(key, val);
            return val;
        }
    }

    public static double weightOnBackOf (int row, int col) {
        HashMap<String, Double> table = new HashMap<>();
        table.put("0", 0.0);
        return memoizedWeightOnBackOf(row, col, table);
    }

    public static boolean solvable (int start, ArrayList<Integer> squares) {
        /*
            This is just a subset sum question but including negative integers too
            But because of indices, the sum that is happening is different from the values that are being summed

            I'm thinking of just recursing each move, but making the current number -1 so that it becomes false if it returns to it
         */
        if (squares.getLast() != 0) {
            throw new IllegalArgumentException("last square must be 0");
        }

        if (squares.get(start) == -1) {
            return false;
        } else if (squares.get(start) == 0) {
            return true;
        } else {
            if ((start + squares.get(start)) >= squares.size() && (start - squares.get(start)) < 0) {
                return false;
            }
            ArrayList<Integer> newSquares = new ArrayList<>(squares);
            newSquares.set(start, -1);
            return ((start + squares.get(start)) < squares.size() && solvable(start + squares.get(start), newSquares)) || ((start - squares.get(start)) >= 0 && solvable(start - squares.get(start), newSquares));
        }
    }

    public static int countCriticalVotes (ArrayList<Integer> blocks, int blockIndex) {
        /*
            Initially, I thought it would make sense to compare 1v1 and then recurse by adding or subtracting blocks from the critical block
            This didn't work and I was confused
            Then I realized that by doing this, I was trying to see when the critical block would win, rather than when the critical block would influence outcome
         */
        ArrayList<Integer> newBlocks = new ArrayList<>(blocks);
        int criticalBlock = newBlocks.get(blockIndex);
        newBlocks.remove(blockIndex);
        if (newBlocks.size() == 1) {
            return (criticalBlock >= newBlocks.getFirst() ? 1 : 0);
        } else {
            ArrayList<Integer> pos1 = new ArrayList<>(newBlocks);
            ArrayList<Integer> pos2 = new ArrayList<>(newBlocks);
            pos1.add(criticalBlock + newBlocks.getFirst());
            pos2.add(criticalBlock - newBlocks.getFirst());
            pos1.removeFirst();
            pos2.removeFirst();
            System.out.print(pos1);
            System.out.println(pos2);
            return countCriticalVotes(pos1, pos1.size() - 1) + countCriticalVotes(pos2, pos2.size() - 1);
        }
    }

//    public static Object[] coinWrapper (int coins, String p1, String p2) {
//        if (coins == 1) {
//            params = new Object[2];
//            params[0] = 1;
//            params[1] = p1;
//            return params;
//        }
//
//    }

//    public static String pickcoin (int coins, String p1, String p2) {
//        if (coins < 1) {
//            throw new IllegalArgumentException("there must be at least 1 coin");
//        }
//        return "true";
//    }
}
