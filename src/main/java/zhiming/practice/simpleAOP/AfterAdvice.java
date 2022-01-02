package zhiming.practice.simpleAOP;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;
/**
 * 后置通知
 */
public class AfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("这是AfterAdvice类的afterReturning方法。");
    }
}
