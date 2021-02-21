package question1;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author deguang
 * @date 2021/02/21
 */

public class FutureThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(new Task() {
            @Override
            protected Object call() throws Exception {
                return null;
            }
        });

        System.out.println("child：" + future.get());

        System.out.println("curr：" + Thread.currentThread().getName());
    }
}
