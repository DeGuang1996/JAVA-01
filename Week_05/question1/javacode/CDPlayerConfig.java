package question1.javacode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import question1.autconfig.CompactDisc;
import question1.autconfig.SgtPeppers;

/**
 * @author deguang
 * @date 2021/02/21
 */

@Configuration
public class CDPlayerConfig {

    @Bean("sgtPeppers")
    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }
}
