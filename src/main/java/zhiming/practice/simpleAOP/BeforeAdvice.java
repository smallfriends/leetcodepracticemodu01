package zhiming.practice.simpleAOP;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
/**
 * 前置通知
 */
public class BeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("这是BeforeAdvice类的before方法。");
    }
}
