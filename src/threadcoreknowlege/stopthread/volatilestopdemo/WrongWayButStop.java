package threadcoreknowlege.stopthread.volatilestopdemo;

/**
 * 演示用volatile的局限：part1 看似可行
 */
public class WrongWayButStop implements Runnable{

    private volatile boolean cancel = false;

    @Override
    public void run() {
        int num = 0;
        while (num < 100000 && !cancel){
            if (num % 100 == 0){
                System.out.println(num + "是100的倍数");
            }
            num++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayButStop wrongWayButStop = new WrongWayButStop();
        Thread thread = new Thread(wrongWayButStop);
        thread.start();
        Thread.sleep(10);
        wrongWayButStop.cancel = true;
    }
}
