package zhiming.juc.sync;


/**synchronized
 * 1.创建资源类,定义属性和操作方法
 * 2.创建多个线层,调用资源类的操作方法
 *
 * */

class Ticket {
    //票数
    private int number = 30;
    //操作方法,买卖票
    public synchronized void sale() {
        //判断是否有票
        if(number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出:" + (number--) + "剩下:" + number);
        }
    }

}

public class SaleTicket {
    public static void main(String[] args) {
        //创建ticket对象
        Ticket ticket = new Ticket();
        //创建3个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for(int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for(int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for(int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "CC").start();

    }


}
