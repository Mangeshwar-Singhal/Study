package Multithreading_Udemy.Chapter6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{
    private CountDownLatch latch;
    private int id;
    public Processor(CountDownLatch latch, int id){
        this.latch = latch;
        this.id = id;
    }
    public void run(){
        System.out.println("Started. "+id);
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){};
        latch.countDown();
        System.out.println("Ended. "+id);
    }
}

public class Test6 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(4);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 7; i++) {
            executor.submit(new Processor(latch,i));
        }
        executor.shutdown();
        try{
            latch.await();
        } catch (InterruptedException e){};
        System.out.println("Completed");
    }
}
