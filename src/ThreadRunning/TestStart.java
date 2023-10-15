package ThreadRunning;

public class TestStart extends Thread{
    int i;

    public TestStart(int i)
    {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(i);
    }
}
