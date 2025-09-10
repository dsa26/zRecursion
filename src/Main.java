import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (String arg : args) {
            list.add(Integer.parseInt(arg));
        }
        System.out.println(Boolean.toString(BRecursion.solvable(list.getFirst(), new ArrayList<Integer> (list.subList(1, list.size())))));
    }
}