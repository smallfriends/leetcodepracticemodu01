package zhiming.javaliuyi.multithread.test109;

public class Desk {
    //定义一个标记
    //true 表示桌子上有汉堡包。允许吃货执行
    //false 表示桌子上没有汉堡包。允许厨师执行
    //public static boolean flag = false;
    private boolean flag = false;

    //汉堡包的总数量
    //public static int count = 10;
    private int count = 10;

    //锁对象
    //public static final Object lock = new Object();
    private final Object lock = new Object();


    public Desk() {
    }
    public Desk(boolean flag, int count) {
        this.flag = flag;
        this.count = count;
    }

    public boolean isFlag() {
        return flag;
    }

    public int getCount() {
        return count;
    }

    public Object getLock() {
        return lock;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
