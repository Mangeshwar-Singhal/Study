package Multithreading_Udemy.Deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public Account account1 = new Account();
    public Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void aquireLock(Lock lock1, Lock lock2) throws InterruptedException{
        while(true){
            boolean gotLock1 = false;
            boolean gotLock2 = false;

            try{
                gotLock1 = lock1.tryLock();
                gotLock2 = lock2.tryLock();
            }
            finally {
                if(gotLock1 && gotLock2){
                    return;
                }
                if(gotLock1){
                    lock1.unlock();
                }
                if(gotLock2){
                    lock2.unlock();
                }
            }

            Thread.sleep(1);
        }
    }

    public void firstThread() throws InterruptedException{
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            aquireLock(lock1, lock2);
            try{
                App.transfer(account1, account2, random.nextInt(100));
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException{
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            // Try changing the order of getting below lock which will lead to deadlock.
            //lock1.lock();
            //lock2.lock();

            aquireLock(lock2, lock1);

            try{
                App.transfer(account2, account1, random.nextInt(100));
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public static void transfer(Account account1, Account account2, int amount){
        account1.amount = account1.amount - amount;
        account2.amount = account2.amount + amount;
    }

    public static void main(String[] args) throws InterruptedException{
        App app = new App();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    app.firstThread();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    app.secondThread();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Account 1 Balance : "+app.account1.balance());
        System.out.println("Account 2 Balance : "+app.account2.balance());
        System.out.println("Total Balance : "+(app.account1.balance()+app.account2.balance()));
    }
}
