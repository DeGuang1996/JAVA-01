package question1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author deguang
 * @date 2021/02/21
 */

public class CompletableFutureThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Integer num = CompletableFuture.supplyAsync(() -> {
            return 1;
        }).get();
        System.out.println("num：" + num);
        System.out.println("cur：" + Thread.currentThread().getName());
    }

}
