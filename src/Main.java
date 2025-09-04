import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (String arg : Arrays.copyOfRange(args, 1, args.length)) {
            list.add(Integer.parseInt(arg));
        }
        System.out.println(Recursion.subsetSum(list, Integer.parseInt(args[0])));
    }
}