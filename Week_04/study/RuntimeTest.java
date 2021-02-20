package study;

import java.util.Arrays;
import java.util.List;

/**
 * @author deguang
 * @date 2021/01/31
 */

public class RuntimeTest {
    public static void main(String[] args) {
        String s="085912,289743,290808,105500,183829,257726,109544,246568,242744,233544,204491,282967,208172";
        List<String> strings = Arrays.asList(s.split(","));
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, "'" + strings.get(i) + "'");
        }
        System.out.println(String.join(",", strings));
        // System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
