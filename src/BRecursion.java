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
            Okay so I'm almost there I just need to abs() the resulting number before comparing
         */
        ArrayList<Integer> newBlocks = new ArrayList<>(blocks);
        int criticalBlock = newBlocks.get(blockIndex);
        newBlocks.remove(blockIndex);
        if (newBlocks.size() == 1) {
            return ((criticalBlock >= Math.abs(newBlocks.getFirst())) ? 2 : 0);
        } else {
            ArrayList<Integer> pos1 = new ArrayList<>(newBlocks);
            ArrayList<Integer> pos2 = new ArrayList<>(newBlocks);
            pos1.set(0, newBlocks.getLast() + newBlocks.getFirst());
            pos2.set(0, newBlocks.getLast() - newBlocks.getFirst());
            pos1.removeLast();
            pos2.removeLast();
            pos1.addFirst(criticalBlock);
            pos2.addFirst(criticalBlock);
            return countCriticalVotes(pos1, 0) + countCriticalVotes(pos2, 0);
        }
    }

    public static boolean coinWrapper (int coins, boolean s1, String p1, String p2) {
        /*
            There is a reason for the s1 -- when recursing and evaluating the previous case,
            it matters that it's now the other player's turn
            So basically, when evaluating p1's turn, it's just an OR, but when evaluating p2's turn,
            it's a NAND because only if there's a way for p2 to lose with all options will p1 win

            Never mind found a simpler way to do this by talking to Mr. Bakker
         */
        if (coins == 1 || coins == 2 || coins == 4) {
            return s1;
        } else if (coins == 3) {
            return !s1;
        } else {
            return !s1 ^ (coinWrapper(coins - 1, !s1, p1, p2) || coinWrapper(coins - 2, !s1, p1, p2) || coinWrapper(coins - 4, !s1, p1, p2));
        }
    }

    public static int pickcoin (int coins, String p1, String p2) {
        if (coins == 1 || coins == 2) {
            return 1;
        } else if (coins == 4) {
            return 3;
        } else if (coins == 3) {
            return -2;
        } else {
            int win = -1 * (Math.min(0, pickcoin(coins - 1, p1, p2)) + Math.min(0, pickcoin(coins - 2, p1, p2)) + Math.min(0, pickcoin(coins - 4, p1, p2)));
            int lose = -1 * (Math.max(0, pickcoin(coins - 1, p1, p2)) + Math.max(0, pickcoin(coins - 2, p1, p2)) + Math.max(0, pickcoin(coins - 4, p1, p2)));
            return (win > 0) ? win : lose;
        }
    }
}
