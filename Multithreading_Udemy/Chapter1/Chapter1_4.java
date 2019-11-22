package Multithreading_Udemy.Chapter1;

public class Chapter1_4 {
    public static void main(String[] args) {
        System.out.println("-----------Normal program Invocation----------");
        Thread r1 = new Thread(() -> {
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
        });
        r1.run();
        Thread r2 = new Thread(() -> {
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
        });
        r2.run();
        System.out.println("----------Thread program Invocation----------");
        r1.start();
        r2.start();
        r2.start();
    }
}
