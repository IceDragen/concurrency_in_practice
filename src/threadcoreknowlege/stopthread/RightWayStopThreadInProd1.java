package threadcoreknowlege.stopthread;

/**
 * DESC: 将异常抛给run方法
 */
public class RightWayStopThreadInProd1 implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd1());
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }

    @Override
    public void run() {
        try {
            subTask();
        } catch (InterruptedException e) {
            System.out.println("已经保存了现场");
            e.printStackTrace();
        }
    }

    private void subTask() throws InterruptedException {
        Thread.sleep(1000);
    }
}
