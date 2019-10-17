package threadcoreknowlege.stopthread;

/**
 * DESC: 一种在try/catch中不能处理中断的情况
 * 由于当线程在sleep状态下收到中断请求时，待系统处理完后，会清除当前线程的interrupt标记位，所以会导致我们无法线程无法正常停止
 */
public class CantInerrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {

            int num = 0;
            while (num < 300) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num++;
            }
            System.out.println("程序运行结束");
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
