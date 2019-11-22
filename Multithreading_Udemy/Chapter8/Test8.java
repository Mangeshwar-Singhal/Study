package Multithreading_Udemy.Chapter8;

import java.util.Scanner;

public class Test8 {

    public void produce() throws InterruptedException{
        synchronized (this){
            System.out.println("Produce Method called.");
            wait();
            System.out.println("Produce Method resumed.");
        }
    }

    public void consume() throws InterruptedException{
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (this){
            System.out.println("Consume Method called.");
            System.out.println("Waiting to press return...");
            scanner.nextLine();
            System.out.println("Return pressed.");
            notify();
            Thread.sleep(5000);
        }
    }

    public static void main(String[] args) {
        Test8 test = new Test8();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.produce(); // try using new Test8().produce();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.consume();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
