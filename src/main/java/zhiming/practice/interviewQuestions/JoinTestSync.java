package zhiming.practice.interviewQuestions;

/**
 * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
 * */
public class JoinTestSync {
    public static void main(String[] args) {
        ThreadJoinTest t1 = new ThreadJoinTest("今天");
        ThreadJoinTest t2 = new ThreadJoinTest("明天");
        ThreadJoinTest t3 = new ThreadJoinTest("后天");
        /*
        * 通过join方法来确保t1/t2/t3的执行顺序
        * */
        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ThreadJoinTest extends Thread {
    public ThreadJoinTest(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(this.getName() + ":" + i);
        }
    }
}