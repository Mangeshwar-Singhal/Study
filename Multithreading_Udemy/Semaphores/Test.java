package Multithreading_Udemy.Semaphores;

import java.util.concurrent.Semaphore;

public class Test {
    public static void main(String[] args) throws InterruptedException{
        Semaphore semaphore = new Semaphore(0);
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                    System.out.println("1");
                    semaphore.release();
                } catch(InterruptedException e){}
            }
        });
        t1.start();
        t1.join();
        System.out.println("2");
        semaphore.acquire();
        semaphore.release();
        System.out.println("Available Permits : "+semaphore.availablePermits());
    }
}
