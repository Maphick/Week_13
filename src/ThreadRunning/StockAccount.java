package ThreadRunning;

public class StockAccount extends Thread{
    public int money = 1000;
    @Override
     public void run()
     {
         while (true) {
             money++;
         }
     }

}
