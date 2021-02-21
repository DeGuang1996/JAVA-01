package question1;

import java.util.concurrent.CountDownLatch;

/**
 * @author deguang
 * @date 2021/02/21
 */

public class CountDownLatchThread {

    private static int num;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            num = 1;
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();

        System.out.println("子线程：" + num);

        System.out.println("父线程：" + Thread.currentThread().getName());
    }
}
