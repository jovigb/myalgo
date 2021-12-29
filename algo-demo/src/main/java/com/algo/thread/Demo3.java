package com.algo.thread;

import java.util.concurrent.CountDownLatch;

// 3. 线程 D 在A、B、C都同步执行完毕后执行
public class Demo3 {

    public static void demo1() {
        int count = 3;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        new Thread(() -> {
            System.out.println("INFO: D 等待 A B C 运行完成");
            try {
                countDownLatch.await();
                System.out.println("INFO: A B C 运行完成，D 开始运行");
                System.out.println("D is working");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (char threadName = 'A'; threadName <= 'C'; threadName++) {
            final String name = String.valueOf(threadName);
            new Thread(() -> {
                System.out.println(name + " is working");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " finished");
                countDownLatch.countDown();
            }).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        demo1();
    }

}
