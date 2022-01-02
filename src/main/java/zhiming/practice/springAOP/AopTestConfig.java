package zhiming.practice.springAOP;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("zhiming.practice.springAOP")
@Configuration
@EnableAspectJAutoProxy     //项目支持aspectJ的风格
public class AopTestConfig {

}
