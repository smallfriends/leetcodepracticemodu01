package zhiming.practice.buyAOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*
* 切面类
* */
@Aspect
@Component
public class BuyAspectJ {

    @Pointcut("execution(String zhiming.practice.buyAOP.IBuy.buy(double)) && args(price) && bean(girl)")
    public void gif(double price) {
    }

    @Around("gif(price)")
    public String hehe(ProceedingJoinPoint pj, double price) {
        try {
            System.out.println("这是切点类Around的通知方法");
            pj.proceed();
            if(price > 68) {
                System.out.println("女孩买衣服超过了68元，赠送一双袜子");
                return "衣服和袜子";
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "衣服";
    }

    //通过注解声明切点表达式
    /*@Pointcut("execution(* zhiming.practice.buyAOP.IBuy.buy(..))")      //切点表达式
    public void point(){}

    @Before("point()")
    public void haha() {                 //通知方法
        System.out.println("before ...");
    }
    @After("point()")
    public void hehe() {
        System.out.println("After ...");
    }
    @AfterReturning("point()")
    public void xixi() {
        System.out.println("AfterReturning ...");
    }
    @Around("point()")
    public void xxx(ProceedingJoinPoint pj) {
        try {
            System.out.println("Around aaa ...");
            pj.proceed();
            System.out.println("Around bbb ...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }*/

    /*@Before("execution(* zhiming.practice.buyAOP.IBuy.buy(..)) && within(zhiming.practice.buyAOP.*) && bean(girl)")*/
    /*@Before("execution(* zhiming.practice.buyAOP.IBuy.buy(..))")
    public void haha() {
        System.out.println("before ...");
    }
    @After("execution(* zhiming.practice.buyAOP.IBuy.buy(..))")
    public void hehe() {
        System.out.println("After ...");
    }
    @AfterReturning("execution(* zhiming.practice.buyAOP.IBuy.buy(..))")
    public void xixi() {
        System.out.println("AfterReturning ...");
    }
    @Around("execution(* zhiming.practice.buyAOP.IBuy.buy(..))")
    public void xxx(ProceedingJoinPoint pj) {
        try {
            System.out.println("Around aaa ...");
            pj.proceed();
            System.out.println("Around bbb ...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }*/
}
