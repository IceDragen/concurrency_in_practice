package background;

/**
 * 描述： 初始化未完毕，就this赋值
 */
public class MultiThreadsError4 {

    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();

        Thread.sleep(10);
        if (point != null) {
            System.out.println("等待10毫秒的结果：" + point);
        }

        //确保PointMaker线程已经运行完
        Thread.sleep(105);
        if (point != null) {
            System.out.println("等待105毫秒的结果：" + point);
        }
    }
}

class Point {

    private final int x, y;

    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        //重点在这里，没有把y初始化完就this赋值
        MultiThreadsError4.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}

class PointMaker extends Thread {

    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}