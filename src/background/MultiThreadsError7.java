package background;

import javax.xml.transform.Source;

/**
 * Description: 用工厂模式解决观察者模式的逸出问题
 */
public class MultiThreadsError7 {
    int count;
    EventListener listener;

    private MultiThreadsError7(MySource mySource) {
        listener = e -> System.out.println("\n我得到的数字是" + count);

        //模拟其他初始化逻辑
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static MultiThreadsError7 getInstance(MySource source){
        MultiThreadsError7 safeListener = new MultiThreadsError7(source);
        source.registerListener(safeListener.listener);
        return  safeListener;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        MultiThreadsError7 multiThreadsError5 = getInstance(mySource);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new Event() {
                });
            }
        }).start();

    }

    static class MySource {

        private MultiThreadsError7.EventListener listener;

        void registerListener(MultiThreadsError7.EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(MultiThreadsError7.Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }

    }

    interface EventListener {

        void onEvent(MultiThreadsError7.Event e);
    }

    interface Event {

    }
}
