package Multithreading_Udemy.Chapter7;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test7 {
    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Program Terminated.");
    }

    private static void producer() throws InterruptedException{
        Random random = new Random();
        while(true){
            blockingQueue.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException{
        Random random = new Random();
        while(true){
            Thread.sleep(100);
            if(random.nextInt(10) == 0){
                int value = blockingQueue.take();
                System.out.println("Taken value : "+value+"; Queue size is : "+blockingQueue.size());
            }
        }
    }
}
