package com.algo.thread;

// 1. 如何让两个线程依次执行？
public class Demo1 {

    public static void demo1() {
        Thread a = new Thread(() -> {
            printNumber("A");
        });

        Thread b = new Thread(() -> {
            printNumber("B");
        });

        a.start();
        b.start();
    }

    public static void printNumber(String threadName) {
        int i = 0;
        while (i++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " print: " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        demo1();
    }

}
