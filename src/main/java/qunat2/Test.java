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
        List<String> subStrs = strs.subList(0, 2);
        System.out.println(subStrs);
        System.out.println(strs.subList(2, strs.size()));
        for (String str : subStrs) {
            System.out.println(str);
//            for (int i = 1; i < 3; i++) {
//                continue;
//
//            }
        }
    }
}
