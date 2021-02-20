package question1.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import question1.autconfig.CompactDisc;

/**
 * @author deguang
 * @date 2021/02/21
 */

@Component
public class CDPlayer {

    private CompactDisc cd;

    @Autowired
    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    @Autowired
    public void setCd(CompactDisc cd) {
        this.cd = cd;
    }

    @Autowired
    public void insertDisc(CompactDisc cd) {
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }

}
