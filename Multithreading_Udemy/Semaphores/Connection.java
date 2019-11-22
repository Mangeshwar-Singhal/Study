package Multithreading_Udemy.Semaphores;

import java.util.concurrent.Semaphore;

public class Connection {
    private static Connection instance = new Connection();
    private int connections = 0;
    private Semaphore semaphore = new Semaphore(10, true);

    private Connection(){};

    public static Connection getInstance(){
        return instance;
    }

    public void connect(){
        try{
            semaphore.acquire();
        } catch (InterruptedException e){};
        try{
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    private void doConnect(){
        synchronized (this) {
            connections++;
            System.out.println("Current Connections : " + connections);
        }

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){};

        synchronized (this){
            connections--;
        }
    }
}
