package threadcoreknowlege.stopthread;

/**
 * DESC: 用stop()来停止线程，会导致线程运行一半突然停止，没办法完成一个基本单位的操作(一个连队)，
 * 会造成脏数据(有的连队多领取或者少领取装备)。
 */
public class WrongWayStopThread1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("连队" + i + "开始领取装备");
            for (int j = 0; j < 10; j++) {
                System.out.println(j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队" + i + "领取装备结束");

        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WrongWayStopThread1());
        thread.start();
        Thread.sleep(1000);
        /*
          假设一个连队必须全部领完装备才能上战场，那么直接调用stop方法会导致部分连队没有全部领完装备就被拉上战场。即stop方法不是
         一种协作式的方法，而是一种命令式的方法。
         */
        thread.stop();
    }
}
