package com.algo.thread;

// 2. 如何让两个线程按照指定的方式有序相交？
// 如果现在我们希望 B线程在 A 线程打印 1 后立即打印 1，2，3，
// 然后 A 线程继续打印 2，3，那么我们需要更细粒度的锁来控制执行顺序。
public class Demo2 {

    public static void demo1() {
        Object lock = new Object();
        Thread A = new Thread(() -> {
            synchronized (lock) {
                System.out.println("A 1");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A 2");
                System.out.println("A 3");
            }
        });

        Thread B = new Thread(() -> {
            synchronized (lock) {
                System.out.println("B 1");
                System.out.println("B 2");
                System.out.println("B 3");
                lock.notify();
            }
        });

        A.start();
        B.start();
    }

    public static void main(String[] args) throws InterruptedException {
        demo1();
    }

}
