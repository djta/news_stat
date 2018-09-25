package qunat2;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String args[]) {
        List<String> strs = new ArrayList<String>();
        strs.add("a");
        strs.add("b");
        strs.add("c");
        strs.add("d");
        List<String> subStrs = strs.subList(0, 3);
        for (String str : subStrs) {
            System.out.println(str);
        }
    }
}
