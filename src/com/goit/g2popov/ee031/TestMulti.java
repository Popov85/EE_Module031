package com.goit.g2popov.ee031;

import java.util.concurrent.Semaphore;

/**
 * Created by Andrey on 30.08.2016.
 */
public class TestMulti {

        private static int integer = 1000;

        private static final CustomSemaphore SEMAPHORE = new CustomSemaphore(5);

        private static final CustomSemaphoreLikeNative SEMAPHORE_LIKE_JAVA = new CustomSemaphoreLikeNative(5);

        private static final Semaphore JAVA_SEMAPHORE = new Semaphore(5);

        public static void doJob() throws Exception {
                //SEMAPHORE.acquire();
                //JAVA_SEMAPHORE.acquire();
                SEMAPHORE_LIKE_JAVA.acquire();
                integer--;
                Thread.sleep(500);
                System.out.println("Thread "+ Thread.currentThread().getName()+" finished its job! integer= " + integer);
                SEMAPHORE_LIKE_JAVA.release(5);
                //SEMAPHORE.release();
                //JAVA_SEMAPHORE.release(5);
        }
}
