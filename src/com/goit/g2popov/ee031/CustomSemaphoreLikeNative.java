package com.goit.g2popov.ee031;

/**
 * Created by Andrey on 31.08.2016.
 */
public class CustomSemaphoreLikeNative implements MySemaphore {

        private int currentPermits;

        public CustomSemaphoreLikeNative(int permits) {
                this.currentPermits = permits;
        }

        @Override
        public synchronized void acquire() throws InterruptedException {
                if (currentPermits == 0) {
                        System.out.println("Thread "+Thread.currentThread().getName()+"is waiting!");
                        this.wait();
                }
                currentPermits--;
        }

        @Override
        public synchronized void release() {
                this.notify();
                System.out.println("One thread was notified!");
                currentPermits++;
        }

        @Override
        public synchronized void acquire(int permits) throws IllegalArgumentException, InterruptedException {
                if ((currentPermits-permits) > 0) {
                        currentPermits -= permits;
                        for (int i = 0; i < permits; i++) {
                                this.wait();
                        }
                } else
                        throw new IllegalArgumentException("Cannot acquire this many permits!");
        }

        @Override
        public synchronized void release(int permits) {
                currentPermits += permits;
                for (int i = 0; i < permits; i++) {
                        this.notify();
                }
        }

        @Override
        public synchronized int getAvailablePermits() {
                return currentPermits;
        }
}
