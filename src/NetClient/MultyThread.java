package NetClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultyThread {

        public static void main(String[] args) throws InterruptedException {

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 1 часть
                    List<Integer> numbers = new ArrayList<>();
                    for(
                            int i = 1;
                            i <=10000000;i++)
                    {
                        numbers.add(i);
                    }
                    int max = Collections.max(numbers);
                    System.out.println("Max found: "+max);
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 2 часть
                    List<Integer> unsorted = new ArrayList<>();
                    for (int i = 10000000; i >= 1; i--) {
                        unsorted.add(i);
                    }
                    Collections.sort(unsorted);
                    System.out.println("List is sorted now");
                }
            });

            Thread thread3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 3 часть
                    List<Integer> list = new ArrayList<>();
                    for (int i = 1; i <= 10000000; i++) {
                        list.add(i);
                    }
                    while (list.size() != 0) {
                        list.remove(list.size() - 1);
                    }
                    System.out.println("List cleared");
                }
            });
            long start = System.currentTimeMillis();

            thread1.start();
            thread2.start();
            thread3.start();

            thread1.join();
            thread2.join();
            thread3.join();
            // Итог
            long end = System.currentTimeMillis();
            System.out.println("Total time: " + (end - start) + " ms");

        }



}


