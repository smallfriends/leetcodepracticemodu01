package zhiming.practice.buyAOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Boy boy = context.getBean("boy",Boy.class);
        Girl girl = (Girl) context.getBean("girl");
        String boyBought = boy.buy(35);
        String girlBought = girl.buy(99.8);
        System.out.println("男孩买到了：" + boyBought);
        System.out.println("女孩买到了：" + girlBought);
        //boy.buy();
        //girl.buy();
    }
}
