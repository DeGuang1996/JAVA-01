package question1;

import java.util.concurrent.Semaphore;

/**
 * @author deguang
 * @date 2021/02/21
 */

public class SemaphoreThread {
    public static String test;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(0);
        new Thread(() -> {
            test = "test";
            semaphore.release();
        }).start();

        try {
            semaphore.acquire();
            System.out.println("child thread：" + test);
            System.out.println("curr：" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
