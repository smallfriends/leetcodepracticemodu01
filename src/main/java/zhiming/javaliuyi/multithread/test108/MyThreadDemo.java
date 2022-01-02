package zhiming.javaliuyi.multithread.test108;

public class MyThreadDemo {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();       //快捷键Ctrl+D，复制上一行代码
        t1.start();
        t2.start();
    }
}
