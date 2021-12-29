package com.algo.thread;

import java.util.Random;
import java.util.concurrent.*;

public class Test1 {
    public static void demo1 () {
        Callable<Integer> callable = () -> {
          System.out.println("子任务开始执行");
          Thread.sleep(1000);
          int result = 0;
          for (int i=0; i<=100; i++) {
              result +=i;
          }
          System.out.println("子任务执行完成并返回结果");
          return result;
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        try {
            System.out.println("开始执行 futureTask.get()");
            Integer result = futureTask.get();
            System.out.println("执行的结果：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        demo1();
    }
}
