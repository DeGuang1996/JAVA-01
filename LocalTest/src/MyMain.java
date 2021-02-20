package src;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * @author deguang
 * @date 2021/02/07
 */

@Slf4j
public class MyMain {
    public static void main(String[] args) {

        Set<String> mgrAndHrgs = new HashSet<>();
        mgrAndHrgs.add("123");
        mgrAndHrgs.add("456");
        log.info("1:{}", mgrAndHrgs);

        // Gson gson = new Gson();
        // TestJson testJson = gson.fromJson("{\n" +
        //         "\t\"s1\": \"little\"\n" +
        //         "}", TestJson.class);
        // System.out.println(testJson.toString());
    }
}
