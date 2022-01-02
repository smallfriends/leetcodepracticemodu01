package zhiming.javaliuyi.multithread.test108;

public class Ticket implements Runnable {
    //票的数量
    private int ticket = 100;
    @Override
    public void run() {
        while(true) {
            if(ticket <= 0) {
                //卖完了
                break;
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket--;
                System.out.println(Thread.currentThread().getName() + "在买票，还剩下" + ticket + "张票");
            }
        }
    }
}
