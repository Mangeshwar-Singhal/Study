package Multithreading_Udemy.Chapter1;

class Runner extends Thread{
    @Override
    public void run(){
        System.out.println("Thread-Run method called.");
        for (int i=0;i<10;i++){
            System.out.println("Hello "+i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread-Run method ended.");
    }
}

public class Chapter1_1 {
    public static void main(String[] args) {
        System.out.println("-----------Normal program Invocation----------");
        Runner r1 = new Runner();
        r1.run();
        Runner r2 = new Runner();
        r2.run();
        System.out.println("----------Thread program Invocation----------");
        r1.start();
        r2.start();
    }
}
