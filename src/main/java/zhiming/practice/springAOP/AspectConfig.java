package zhiming.practice.springAOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect     //申明为一个切面类
public class AspectConfig {
    @Pointcut("execution(* zhiming.practice.springAOP.UserDao.*(..))")
    //定义切点方法
    public void pointcuttest(){
    }

    @Around("pointcuttest()")
    //指定增强的方法在切入点方法之前之后都执行
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println ("=======Around前=======");
        Object proceed = joinPoint.proceed ();
        System.out.println ("=======Around后=======");
        return  proceed;
    }

    /*@Before("pointcuttest()")
    //指定增强的方法在切入点方法之前执行
    public  void  advice1() {
        System.out.println ("=====Before=========");
    }

    @After("pointcuttest()")
    //指定增强的方法在切入点方法之后执行
    public  void  advice2() {
        System.out.println ("=====after=========");
    }*/

}
