package zhiming.practice.simpleAOP;
/**
 * 目标对象
 */
public class StudentImpl implements Student{
    @Override
    public void addStudent(String name) {
        System.out.println("欢迎" + name + "加入spring家庭");
    }
}
