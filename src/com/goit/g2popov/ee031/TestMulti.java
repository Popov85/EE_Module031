package com.goit.g2popov.ee031;

import java.util.concurrent.Semaphore;

/**
 * Created by Andrey on 30.08.2016.
 */
public class TestMulti {

        private static int integer = 1000;

        private static final CustomMySemaphore semaphore = new CustomMySemaphore(10);

        public static void doJob() throws Exception {
                semaphore.acquire();
                integer--;
                Thread.sleep(1000);
                System.out.println("Thread "+ Thread.currentThread().getName()+" finished its job! integer= " + integer);
                semaphore.release();
        }
}
