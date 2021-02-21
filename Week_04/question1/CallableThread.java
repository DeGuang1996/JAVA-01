package question1;

import java.util.concurrent.Callable;

/**
 * @author deguang
 * @date 2021/02/21
 */

public class CallableThread {

    public static void main(String[] args) {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "test callable";
            }
        };
        System.out.println("子线程：" + callable);
        System.out.println("父线程：" + Thread.currentThread().getName());
    }
}
