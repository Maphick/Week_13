
import NetClient.ChickenEgg;
import NetClient.ConsoleClock;
import ThreadRunning.StockAccount;
import ThreadRunning.TestStart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ChickenEgg();
        //CheckResults.main(args);
        //JoinExample.main(args);
       // InterruptExample.main(args);

        /*

        ConsoleClock cc = new ConsoleClock();
        cc.start();
        Thread.sleep(3000);
        cc.interrupt();

         */
    }

    public static void ChickenEgg()
    {
        {
            // Создание потоков с именами
            ChickenEgg chicken = new ChickenEgg("Chicken");
            ChickenEgg egg = new ChickenEgg("Egg");
            System.out.println("Начинаем спор: кто появился первым?");
            // Запуск потоков
            chicken.start();
            egg.start();
            // Пока оба потока работают
            while (chicken.isAlive() || egg.isAlive()) {
                try {
                    // Приостанавливаем поток "судьи"
                    Thread.sleep(100);
                    System.out.println("Chicken");
                } catch (InterruptedException e) {
                }
            }
            // Cказало ли яйцо последнее слово?
            if (egg.isAlive()) {
                try {
                    // Прерываем яйцо
                    //egg.interrupt();
                    // Ждем, пока яйцо закончит высказываться
                    egg.join();
                } catch (InterruptedException e) {
                }

                System.out.println("Первым появилось egg!");
            } else {
                try {
                    // Прерываем курицу
                   // chicken.interrupt();
                    // Ждем, пока курица закончит высказываться
                    chicken.join();
                } catch (InterruptedException e) {
                }
                System.out.println("Первой появилась chicken!");
            }
            System.out.println("Спор закончен");
        }
    }


    public class InterruptExample {

        public static void main(String[] args) throws InterruptedException { // 4
            Thread threadToInterrupt = new Thread(){
                public void run(){
                    while (!isInterrupted()) {
                        System.out.println("Working hard");
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        //Перехватываем молча
                    }
                    System.out.println("All done");
                }
            };

            threadToInterrupt.start();
            Thread.currentThread().sleep(2500); // 3
            threadToInterrupt.interrupt();
            threadToInterrupt.join();
            System.out.println("Thread finished");
        }
    }

    public void Example_12_3_4() throws InterruptedException {
        Thread t1 = new Thread() {
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Thread.currentThread().start(); // 1
            }
        };
        t1.start(); // 2
        //t1.start(); // 2

        t1.join();
    }

      public class JoinExample {

        private static int counter = 0;
        public static void main(String[] args) throws InterruptedException {
        Thread thread =  new Thread() {
                public void run(){
                    for (int i = 0; i < 50000000; i++) {
                        JoinExample.counter++;
                    }
                }
            };
            thread.start();
            thread.join(); // main() поток блокируется и ждет, пока не завершится поток thread
            System.out.println("Counter value: " + counter); // Counter value: 0
        }

    }

    public class CheckResults {
        private static int counter = 0;

        public static void main(String[] args) {
            new Thread() {
                public void run(){
                    for (int i = 0; i < 100; i++) {
                        System.out.println(CheckResults.counter++);
                        try {
                            Thread.sleep(1000); // 1 секунда
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }.start();
            while (CheckResults.counter < 100) {
                System.out.println("Not reached yet");
            }
            System.out.println("Reached");
        }

    }

    public static void Examlple_0() {
        StockAccount sA = new StockAccount();
        sA.run();
        long profit = 0;
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (command != "exit") {
            command = scanner.nextLine();
            switch (command) {
                case "check":
                    System.out.println(sA.money);
                    break;
                case "profit":
                    profit = (long) sA.money - 1000;
                    System.out.println(profit);
                    sA.money = 1000;
            }
        }
    }

    public static void Example_1() {
        for (int i = 0; i < 10; i++) {
            TestStart tS = new TestStart(i);
            //tS.start();
        }
        TestStart tS_1 = new TestStart(1);
        TestStart tS_3 = new TestStart(3);
        tS_1.start();
        tS_3.run();

    }

    public static void Exercise_12_3_2() {

        Test new_test = new Test();
        new_test.start();
    }


    class Advertisement implements Runnable {
        public void run() {

        }
    }

    public static void Exercise_12_3_4() {

    }

    public static void Exercise_12_4_1() throws InterruptedException {
        Thread t1 = new Thread() {
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.currentThread().start(); // 1
            }
        };
        t1.start(); // 2
        t1.join();
    }




    public static class Test extends Thread {

        public void run()
        {
            System.out.println("123");
        }

    }


    class Client implements Runnable {
        Socket socket;

        public Client(Socket socket){

            this.socket = socket;
        }

        public void run() {
            try {
                // получаем потоки ввода и вывода
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                // создаем удобные средства ввода и вывода
                Scanner in = new Scanner(is);
                PrintStream out = new PrintStream(os);

                // читаем из сети и пишем в сеть
                out.println("Welcome to mountains!");
                String input = in.nextLine();
                while (!input.equals("bye")) {
                    out.println(input + "-" + input + "-" +
                            input.substring(input.length() / 2) + "...");
                    input = in.nextLine();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void Example_3() throws IOException {
        Servak();

    }

    public static void Servak() throws IOException {
        ServerSocket server = new ServerSocket(1234);
        // создаем серверный сокет на порту 1234
        while (true) {
            System.out.println("Waiting...");
            // ждем клиента
            Socket socket = server.accept();
            System.out.println("Client connected!");

            // получаем потоки ввода и вывода
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // создаем удобные средства ввода и вывода
            Scanner in = new Scanner(inputStream);
            PrintStream out = new PrintStream(outputStream);

            // читаем из сети и пишем в сеть
            out.println("Welcome to mountains!");
            String input = in.nextLine();
            while (!input.equals("bye")) {
                out.println(input + "-" + input + "-" +
                        input.substring(input.length() / 2) + "...");
                input = in.nextLine();
            }
            socket.close();
        }
    }


    public  static void ServerEcho() throws IOException {
        ServerSocket echoServer = new ServerSocket(1234);
        while (true)
        {
            System.out.println("Waiting...");
            Socket socket = echoServer.accept();
            System.out.println("Client connected!");
            ClientEcho client = new ClientEcho(socket);
            Thread thread = new Thread(client);
            thread.start();
        }

    }
}
