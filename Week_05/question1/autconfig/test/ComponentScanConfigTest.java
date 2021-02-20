package question1.autconfig.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import question1.autconfig.CDPlayerConfig;
import question1.autconfig.SgtPeppers;

/**
 * @author deguang
 * @date 2021/02/21
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= CDPlayerConfig.class)
public class ComponentScanConfigTest {

    @Autowired
    private SgtPeppers sgtPeppers;

    @Test
    public void softMusicIsNotNull(){
        Assert.assertNotNull(sgtPeppers);
    }
}
