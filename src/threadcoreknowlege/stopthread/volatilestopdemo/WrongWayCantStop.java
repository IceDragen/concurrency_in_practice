package threadcoreknowlege.stopthread.volatilestopdemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * DESC: 演示用volatile的局限part2 陷入阻塞时，
 * volatile是无法停止线程的 此例中，生产者的生产速度很快，消费者消费速度慢，所以阻塞队列满了以后，生产者会阻塞，等待消费者进一步消费
 */
public class WrongWayCantStop {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMore()) {
            System.out.println(consumer.storage.take()+"被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");

        //一旦消费不需要更多数据了，我们应该让生产者也停下来，但是实际情况
        producer.cancel=true;
        System.out.println(producer.cancel);
    }
}

class Producer implements Runnable{

    public volatile boolean cancel = false;

    BlockingQueue storage;

    public Producer(BlockingQueue<Integer> storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < 100000 && !cancel){
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
