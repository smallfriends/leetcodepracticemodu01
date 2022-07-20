package zhiming.juc.sync;

import java.util.concurrent.locks.ReentrantLock;

/**Lock
 * 1.创建资源类,定义属性和操作方法
 * 2.创建多个线层,调用资源类的操作方法
 *
 * */
class LTicket {
    //票数
    private int number = 30;
    //创建可重入锁
    private final ReentrantLock lock = new ReentrantLock();

    //操作方法,买卖票
    public void sale() {
        //上锁
        lock.lock();

        try {
            //判断是否有票
            if(number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出:" + (number--) + "剩下:" + number);
            }
        } finally {     //防止try中的处理逻辑抛出异常,无法释放锁,finally中一定会执行释放锁
            //解锁
            lock.unlock();
        }

    }

}


public class LockSaleTicket {

    public static void main(String[] args) {
        LTicket ticket = new LTicket();
        //创建三个线程
        new Thread(()->{          //Runnable是函数式接口,可以使用lamdba表达式简化
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "AA").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "BB").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "BB").start();
    }


}
