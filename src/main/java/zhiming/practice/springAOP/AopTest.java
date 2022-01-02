package zhiming.practice.springAOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext (AopTestConfig.class);

        UserDao bean = context.getBean (UserDao.class);

        bean.userdao();
    }
}
