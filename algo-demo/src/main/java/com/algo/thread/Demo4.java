package com.algo.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// 4. 三个运动员分开准备同时开跑
public class Demo4 {

    public static void demo1() {
        int count = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        Random random = new Random();
        for (char threadName = 'A'; threadName <= 'C' ; threadName++) {
            final String name = String.valueOf(threadName);
            new Thread(() -> {
                int prepareTime = random.nextInt(10000);
                System.out.println(name + " 准备时间：" + prepareTime);
                try {
                    Thread.sleep(prepareTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " 准备好了，等待其他人");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " 开始跑步");
            }).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        demo1();
    }

}
