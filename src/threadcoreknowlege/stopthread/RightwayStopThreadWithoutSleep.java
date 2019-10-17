package threadcoreknowlege.stopthread;

/**
 * Desc: run方法中没有用sleep和wait方法的情况下停止线程
 */
public class RightwayStopThreadWithoutSleep implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightwayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(1000); //先让thread运行1s
        thread.interrupt(); //给thread发送中断信号

    }

    @Override
    public void run() {
        int num = 0;

        while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE){
                if (num % 10000 == 0){
                    System.out.println(num + "是10000的倍数");
                }
                num++;

        }
        System.out.println("程序运行结束");

    }
}
