package NetClient;

import java.util.Random;

public class ChickenEgg extends Thread {

    public ChickenEgg(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                // Приостанавливаем поток
                sleep(getTimeSleep());
                System.out.println(getName());
            } catch (InterruptedException e) {
            }
        }
    }

    final Random random = new Random();

    int getTimeSleep() {

        return random.nextInt(300);
    }
}