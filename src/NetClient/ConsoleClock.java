package NetClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public  class ConsoleClock extends Thread {
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    public void run() {
        while ((!isInterrupted())) {
            try {
                String date = dateFormat.format(new Date());
                System.out.println(date);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.out.println("The clock was stopped");
                break;
            }
        }
    }
}

