package zhiming.practice.simpleAOP;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class CompareInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object result = null;
        String stuName = methodInvocation.getArguments()[0].toString();
        if(stuName.equals("dragon")) {
            //如果学生是dragon时，执行目标方法，
            result = methodInvocation.proceed();
        } else {
            System.out.println("此学生是" + stuName + "而不是dragon，不批准其加入。");
        }
        return result;
    }
}
