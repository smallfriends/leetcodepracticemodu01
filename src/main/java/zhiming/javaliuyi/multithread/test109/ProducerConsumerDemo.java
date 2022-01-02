package zhiming.javaliuyi.multithread.test109;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Desk desk = new Desk();
        Foodie f1 = new Foodie(desk);
        Cooker c1 = new Cooker(desk);
        f1.start();
        c1.start();

    }

}
