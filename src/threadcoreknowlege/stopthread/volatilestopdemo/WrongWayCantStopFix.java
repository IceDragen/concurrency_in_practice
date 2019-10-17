package threadcoreknowlege.stopthread.volatilestopdemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * DESC: 用中断来修复刚才的无尽等待问题
 */
public class WrongWayCantStopFix {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        WrongWayCantStopFix wrongWayCantStopFix = new WrongWayCantStopFix();

        Producer producer = wrongWayCantStopFix.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = wrongWayCantStopFix.new Consumer(storage);
        while (consumer.needMore()) {
            System.out.println(consumer.storage.take()+"被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");

        producerThread.interrupt();
    }

    class Producer implements Runnable{

        BlockingQueue storage;

        public Producer(BlockingQueue<Integer> storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num < 10000 && !Thread.currentThread().isInterrupted()){
                    if (num % 100 == 0){
                        storage.put(num);
                        System.out.println(num + "被放进了仓库中");
                    }
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("生产者运行结束");
            }
        }
    }

    class Consumer{

        BlockingQueue storage;

        public Consumer(BlockingQueue<Integer> storage) {
            this.storage = storage;
        }



        public boolean needMore(){
            return Math.random() < 0.95;
        }
    }
}


