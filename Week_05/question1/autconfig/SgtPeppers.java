package question1.autconfig;

import org.springframework.stereotype.Component;

/**
 * @author deguang
 * @date 2021/02/21
 */

@Component
public class SgtPeppers implements CompactDisc {


    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";


    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
