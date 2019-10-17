package threadcoreknowlege.sixstates;


/**
 * Desc: 展示Blocking，Waiting和Time_Waiting三种状态
 */
public class BlockedWaitingTimeWaiting implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimeWaiting run =  new BlockedWaitingTimeWaiting();
        Thread thread1 = new Thread(run);
        thread1.start();
        Thread thread2 = new Thread(run);
        thread2.start();

        //会打印出Time_Waiting,因为此时线程在执行sleep方法
        System.out.println("thread1: " + thread1.getState());
        //会打印出Blocked，因为此时thread2无法获得sync()锁
        System.out.println("thread2: " + thread2.getState());

        //确保子线程已经过了sleep的时间
        Thread.sleep(1300);

        //会打印出Waiting状态，因为执行了wait()方法
        System.out.println("thread1: " + thread1.getState());
        //为什么会打印出Time_Waiting状态？是因为thread2已经获得了锁吗？
        System.out.println("thread2: " + thread2.getState());

        Thread.sleep(1200);
        System.out.println("thread2: " + thread2.getState());

    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
