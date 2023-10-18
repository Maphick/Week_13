package NetClient;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;


public class ExtraTask_1 {
    static final Random random = new Random();
    static Queue<Integer> queue = new LinkedList<>();
    //private static ArrayBlockingQueue queue = new ArrayBlockingQueue < > (10);
    public static void main(String[] args) {
    Thread thread1 = new Thread() {
        public void run(){
            while (true){
                while (queue.size() < 10){
                    int value = random.nextInt(100);;
                    queue.add(value);
                    System.out.println("thread1_____________QUEUE: queue.size = " + queue.size() + " VALUE = " + value);
                }
            }
          }
        };
    Thread thread2 = new Thread() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int value = random.nextInt(10);
                System.out.println("thread2_____________RANDOM = " + value);
                if (value == 5)
                {
                    System.out.println("5 !!!! ");
                    System.out.println("thread2_____________queue.poll = " + queue.poll());
                    System.out.println("thread2_____________queue.size = " + queue.size());
                    //System.out.println("First threat: " + queue.take());
                }
                System.out.println(value);
            }
        }
    };
    thread1.start();
    thread2.start();
    }
}
