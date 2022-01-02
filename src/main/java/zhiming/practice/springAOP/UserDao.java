package zhiming.practice.springAOP;

import org.springframework.stereotype.Component;

@Component
public class UserDao {
    public  void  userdao(){
        System.out.println ("这是在UserDao中的一个方法");
    }

}
