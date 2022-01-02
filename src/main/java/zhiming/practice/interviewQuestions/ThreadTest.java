package zhiming.practice.interviewQuestions;


/**
 * 子线程循环5次，接着主线程循环10次，接着又回到子线程循环5次，接着再到主线程又循环10次，如此循环3次
 * */
public class ThreadTest {
    public static void main(String[] args) {
        final Mythread threads = new Mythread();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 1; i <= 3; i++) {
                            threads.subThread(i);
                        }
                    }
                }
        ).start();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 1; i <=3; i++) {
                            threads.mainThread(i);
                        }
                    }
                }
        ).start();
    }
}

class Mythread {
    boolean bShouldSub = true;  //标志子线程方法是否被调用
    public synchronized void subThread(int i) {
        if(!bShouldSub) {   //若子线程没被调用，即主线程正在运行，所以等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j = 1; j <= 5; j++) {
            System.out.println("sub thread : " + i + ", loop : " + j);
        }
        bShouldSub = false;     //子线程运行完毕
        this.notify();      //唤醒其他线程，即主线程
    }
    public synchronized void mainThread(int i) {
        if(bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j = 1; j <= 10; j++) {
            System.out.println("main thread :" + i + ",loop : " + j);
        }
        bShouldSub = true;
        this.notify();
    }
}