package Multithreading_Udemy.Reentrant_Lock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test10 {
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void increment(){
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException{
        lock.lock();
        System.out.println("Waiting.");
        condition.await();
        System.out.println("Woken up.");
        try {
            increment();
        }
        finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException{
        Thread.sleep(1000);
        lock.lock();
        System.out.println("Press return.");
        new Scanner(System.in).nextLine();
        System.out.println("Got Return");
        condition.signal();
        try {
            increment();
        }
        finally {
            lock.unlock();
        }
    }

    public void finished(){
        System.out.println("Count is : "+count);
    }

    public static void main(String[] args) throws InterruptedException{
        Test10 test = new Test10();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.firstThread();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.secondThread();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        test.finished();
    }
}
