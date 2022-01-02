package zhiming.practice.simpleAOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
/**
 * 测试代码
 */
public class Test {
    public static void main(String[] args) {

        ApplicationContext ctx = new FileSystemXmlApplicationContext("file:C:\\Users\\huangzhiming\\IdeaProjects" +
                "\\leetcodepracticemodu01\\src\\main\\resources\\applicationContext.xml");
        Student s = (Student) ctx.getBean("student");
        s.addStudent("aaa");
    }
}
