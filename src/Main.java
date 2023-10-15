import ThreadRunning.StockAccount;
import ThreadRunning.TestStart;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //Example_1();
        //Exercise_12_3_2();
        Example_3();
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

    public static class Test extends Thread {

        public void run()
        {
            System.out.println("123");
        }

    }

    public static void Example_3() throws IOException {

        ServerSocket server = new ServerSocket(4242);
        System.out.println("Waiting...");

        Socket client = server.accept();
        System.out.println("Client connected!");

        InputStream input = client.getInputStream();
        OutputStream output = client.getOutputStream();

        Scanner scanner = new Scanner(input);
        PrintStream printStream = new PrintStream(output);

        printStream.println("Введите имя");
        printStream.println("Имя :" + scanner.nextLine());




    }

}
