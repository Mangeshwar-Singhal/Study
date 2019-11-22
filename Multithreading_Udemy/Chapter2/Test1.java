package Multithreading_Udemy.Chapter2;

import java.util.Scanner;

class Processor extends Thread{

    private volatile boolean running = true;

    public void run(){
        while(running){
            System.out.println("Hello "+ getName());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopMethod(){
        running = false;
    }
}

public class Test1 {
    public static void main(String[] args) {
        Processor proc1 = new Processor();
        proc1.start();
        Processor proc2 = new Processor();
        proc2.start();
        System.out.println("Press return to stop...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        proc1.stopMethod();
        proc2.stopMethod();
    }
}
