package zhiming.juc.lamdba;



@FunctionalInterface
interface Foo {
    //public void sayHello();
    public int add(int x, int y);

    public default int mul(int x, int y) {
        return x * y;
    }

}

/**
 *
 * 函数式接口,有且仅有一个方法
 * lamdba express解决了匿名内部类代码冗余的问题
 * 拷贝小括号,写死右箭头,落地大括号,函数式接口,有且仅有一个方法才可以用lamdba express
 * default
 * static
 * */

public class LamdbaExpressDemo {
    public static void main(String[] args) {
        /*Foo foo = new Foo() {       //匿名内部类
            @Override
            public void sayHello() {
                System.out.println("****hello hzm");
            }
        };
        foo.sayHello();*/

        Foo foo1 = (int x, int y) -> {
            System.out.println("come in add method");
            return x + y;
        };
        System.out.println(foo1.add(3, 5));
        System.out.println(foo1.mul(3, 5));
    }
}
