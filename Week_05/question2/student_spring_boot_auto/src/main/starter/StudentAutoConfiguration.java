package main.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author deguang
 * @date 2021/02/21
 */

@Configuration
@ConditionalOnClass(Student.class)
@EnableConfigurationProperties(StudentProperties.class)
public class StudentAutoConfiguration {

    @Bean
    public Student student(StudentProperties studentProperties){
        return new Student(studentProperties.getId(), studentProperties.getName());
    }
}
