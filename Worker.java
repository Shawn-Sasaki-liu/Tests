package com.lxr.SkillTest;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RunnableFuture;

/**
 * Created by SoulCalculator on 16/5/10.
 */
public class Worker implements Runnable {
    private static int orderCount = 0;
    private BlockingQueue<Order> workQueue = new ArrayBlockingQueue<Order>(5);

    @Override
    public void run() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    generateOrder();
                }catch (Exception e ) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    work();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println("Work completed!");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void generateOrder() throws InterruptedException {
        while(orderCount < 10) {
            // order Id start from 1
            Order order = new Order(orderCount+1);
            workQueue.put(order);
            System.out.println("Order " + order.getOrderId() + " generated with status: " + order.getStatus());
            orderCount++;
            Thread.sleep(new Random().nextInt(500));
        }
    }

    public void work() throws InterruptedException {
        while (true) {
            Thread.sleep(100);
            Order order = workQueue.take();
            System.out.println("Order " + order.getOrderId() + " is taken by the worker and start working...");
            // work with order
            Thread.sleep(1000);
            order.setStatus("FULLFILLED");
            System.out.println("Order " + order.getOrderId() + " finished with status: " + order.getStatus());
            if (order.getOrderId() == orderCount) {
                break;
            }
        }
    }
}
