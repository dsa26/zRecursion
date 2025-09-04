import java.util.ArrayList;

public class Recursion {
    public static int factorial (int x) {
        if (x < 0) {
            throw new IllegalArgumentException("x must be nonnegative");
        }

        if (x == 0) {
            return 1;
        } else {
            return x * factorial(x - 1);
        }
    }

    public static int fibonacci (int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static int sumOfDigits (int x) {
        if (x < 0) {
            throw new IllegalArgumentException("x must be nonnegative");
        }

        if (x < 10) {
            return x;
        } else {
            return x % 10 + sumOfDigits(x / 10);
        }
    }

    public static String moveTOH (int n, int start, int end, int helper) {
        if (n == 1) {
            return String.format("%d to %d", start, end);
        } else if (n == 2) {
            return String.format("%d to %d, %d to %d, %d to %d", start, helper, start, end, helper, end);
        } else {
            return moveTOH(n - 1, start, helper, end) + String.format(", %d to %d, ", start, end) + moveTOH(n - 1, helper, end, start);
        }
    }

    public static String towerOfHanoi (int n) {
        return moveTOH(n, 1, 3, 2);
    }

    public static int sum (ArrayList<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }

    public static int subsetSum (ArrayList<Integer> list, int target) {
        if (target < 0) {
            return 0;
        }
        if (target == 0) {
            return 1;
        }
        int perms = 0;
        if (list.isEmpty()) {
            return perms;
        } else if (list.size() == 1){
            if (list.getFirst() == target) {
                perms++;
            }
            return perms;
        } else if (list.size() == 2){
            if (list.getFirst() == target) {
                perms++;
            }
            if (list.get(1) == target) {
                perms++;
            }
            if (list.getFirst() + list.get(1) == target) {
                perms++;
            }
            return perms;
        } else {
            ArrayList<Integer> smallerList = new ArrayList<Integer>(list.subList(1, list.size()));
            perms += subsetSum(smallerList, target - list.getFirst());
            perms += subsetSum(smallerList, target);
            return perms;
        }
    }
}