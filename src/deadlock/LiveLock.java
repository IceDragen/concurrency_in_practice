package deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Description: 演示活锁
 *      一对夫妻只有一把勺子，吃饭的时候相互谦让造成谁都没有办法吃
 */
public class LiveLock {
    public static class Spoon{
        private Diner owner;

        public Spoon(Diner owner) {
            this.owner = owner;
        }

        public Diner getOwner() {
            return owner;
        }

        public void setOwner(Diner owner) {
            this.owner = owner;
        }

        public synchronized void use(){
            System.out.printf("%s 吃完了", owner.name);
        }
    }

    public static class Diner {

        public String name;
        public boolean isHungry;

        public Diner(String name) {
            this.name = name;
            isHungry = true;
        }

        public void eatWith(Spoon spoon, Diner spouse){
            while (isHungry){
                if (spoon.getOwner() != this){
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                Random random = new Random();
                if (spouse.isHungry && random.nextInt(9) < 8){
                    System.out.println(name + ": 亲爱的" + spouse.name + "你先吃吧");
                    spoon.setOwner(spouse);
                    continue;
                }

                spoon.use();
                isHungry = false;
                System.out.println(name + "我吃完了");
                spoon.setOwner(spouse);
            }
        }
    }

    public static void main(String[] args) {
        Diner husband = new Diner("牛郎");
        Diner wife = new Diner("织女");
        Spoon spoon = new Spoon(husband);

        new Thread(() -> {
            husband.eatWith(spoon, wife);
        }).start();

        new Thread( () -> {
            wife.eatWith(spoon, husband);
        }).start();
    }
}
