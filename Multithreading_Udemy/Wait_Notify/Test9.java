package Multithreading_Udemy.Wait_Notify;

import java.util.LinkedList;
import java.util.Random;

public class Test9 {
    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException{
        int value = 0;
        while(true){
            synchronized (lock){
                while(list.size() == LIMIT){
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException{
        Random random = new Random();
        while(true){
            synchronized (lock){
                System.out.print("List size : "+list.size());
                while(list.size() == 0){
                    lock.wait();
                }
                int value = list.removeFirst();
                System.out.println("; Fetched Value : "+value);
                lock.notify();
                Thread.sleep(random.nextInt(1000));
            }
        }
    }

    public static void main(String[] args) {
        Test9 test = new Test9();
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
