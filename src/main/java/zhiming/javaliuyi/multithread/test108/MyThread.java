package zhiming.javaliuyi.multithread.test108;

public class MyThread extends Thread{
    @Override
    public void run() {
        //代码就是线程开启之后执行的代码
        for (int i = 0; i < 10; i++) {     //快捷键：10.fori
            System.out.println("线程开启了" + i);
        }
    }
}
