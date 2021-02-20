package main.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author deguang
 * @date 2021/02/21
 */

@Data
@ConfigurationProperties(prefix = "student")
public class StudentProperties {

    private Integer id;

    private String name;
}
