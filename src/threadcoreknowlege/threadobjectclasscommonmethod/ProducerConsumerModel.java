package threadcoreknowlege.threadobjectclasscommonmethod;

import java.util.LinkedList;

/**
 * Description: 用wait和notify这两个底层方法实现一个消费者生产者模型
 */
public class ProducerConsumerModel {

    public static void main(String[] args) {
        EventStorage storage = new EventStorage();
        Thread producer = new Thread(new Producer(storage), "producer");
        Thread consumer = new Thread(new Consumer(storage), "consumer");
        producer.start();
        consumer.start();

    }

}

class EventStorage{
    private int maxSize;
    private LinkedList<Integer> storage;

    public EventStorage() {
        this.maxSize = 10;
        this.storage = new LinkedList<>();
    }

    public synchronized void put(Integer e){
        while (storage.size() == maxSize){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        storage.add(e);
        System.out.println(Thread.currentThread().getName() + "生产了一个产品，" + "仓库中现在有" + storage.size() + "个产品");
        notify();
    }

    public synchronized void take(){
        while (storage.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("拿到了" + storage.poll() + "    仓库中现在还有" + storage.size() + "个产品");
        notify();
    }

    public boolean isEmpty(){
        return storage.isEmpty();
    }
}

class Producer implements Runnable{

    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put(i);
        }
    }
}

class Consumer implements Runnable{

    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}
