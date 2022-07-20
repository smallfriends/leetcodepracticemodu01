package zhiming.juc.sync;


//第一步 创建资源类,定义属性和操作方法
class Share {
    //初始值
    private int number = 0;
    //+1的方法
    public synchronized void incr() throws InterruptedException {
        //第二步 判断 干活 通知
        if(number != 0) {       //判断number值是否是0,如果不是0,等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        //通知其他线程
        this.notify();
    }
    //-1的方法
    public synchronized void decr() {

    }

}

public class ThreadDemo1 {
}
