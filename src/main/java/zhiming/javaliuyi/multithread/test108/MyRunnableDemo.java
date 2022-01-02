package zhiming.javaliuyi.multithread.test108;

public class MyRunnableDemo {
    public static void main(String[] args) {
        //创建了一个参数对象
        MyRunnable mr = new MyRunnable();
        //创建了一个线程对象，并把参数传递给这个线程
        //在线程启动之后，执行的就是参数里面的run方法
        Thread t1 = new Thread(mr);
        //开启线程
        t1.start();
    }
}
