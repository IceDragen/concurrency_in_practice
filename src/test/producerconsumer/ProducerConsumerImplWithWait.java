package test.producerconsumer;

import threadcoreknowlege.stopthread.RightwayStopThreadWithoutSleep;

/**
 * Description:
 */
public class ProducerConsumerImplWithWait {


    public static void main(String[] args) throws InterruptedException {
        Object resourceA = new Object();
        Object resourceB = new Object();
        int[] cur = new int[]{0};
        boolean[] over = new boolean[]{false};
        Thread producer = new Thread(() -> {
            synchronized (resourceA){
                for (int i = 0; i < 10; i++) {
                    cur[0] = i;
                    System.out.println("produce " + i);
                    try {
                        resourceA.wait();
                        resourceB.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                over[0] = true;
            }
        });

        Thread consumer = new Thread(() -> {
            synchronized (resourceB){
                while (!over[0]){
                    System.out.println("consume " + cur[0]);
                    try {
                        resourceB.wait();
                        resourceA.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        producer.start();
        Thread.sleep(100);
        consumer.start();
    }
}
