package Multithreading_Udemy.Callable_Future;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class Test13 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // we can use Void instead of Integer for just using Callable properties.
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception{
                Random random = new Random();
                int duration = random.nextInt(4000);
                if(duration > 1000){
                    throw new IOException("Sleeping for too long.");
                }
                System.out.println("Starting......");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e){};
                System.out.println("Finished......");
                return duration;
            }
        });
        executorService.shutdown();
        try{
            System.out.println("Wait Time is : "+future.get());
        } catch (InterruptedException| ExecutionException e){
            IOException ioException = (IOException) e.getCause();
            System.out.println(ioException.getMessage());
        };
    }
}
