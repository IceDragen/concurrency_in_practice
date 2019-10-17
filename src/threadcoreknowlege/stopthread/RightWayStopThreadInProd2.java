package threadcoreknowlege.stopthread;

public class RightWayStopThreadInProd2 implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }


    @Override
    public void run() {
        while (true){
            if (Thread.currentThread().isInterrupted()){
                System.out.println("程序运行结束");
                break;
            }
            subTask();
        }
    }

    private void subTask(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
