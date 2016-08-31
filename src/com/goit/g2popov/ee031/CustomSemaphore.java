package com.goit.g2popov.ee031;

/**
 * Created by Andrey on 30.08.2016.
 */
public class CustomSemaphore implements MySemaphore {

        // E.g. Max = 5; Not more than 5 threads are acceptable.
        private final int maxPermits;

        private int currentPermits;

        public CustomSemaphore(int permits) {
                this.maxPermits = permits;
                this.currentPermits = permits;
        }

        @Override
        public synchronized void acquire() throws InterruptedException {
                if (this.currentPermits == 0) {
                        System.out.println("Thread "+Thread.currentThread().getName()+"is waiting!");
                        this.wait();
                }
                this.currentPermits--;
        }

        @Override
        public synchronized void acquire(int permits) throws IllegalArgumentException, InterruptedException {
                if ((currentPermits-permits) > 0) {
                        this.currentPermits -= permits;
                        for (int i = 0; i < permits; i++) {
                                this.wait();
                        }
                } else
                        throw new IllegalArgumentException("Cannot acquire "+permits+" permits!" +
                                " The threshold of "+this.maxPermits+" is reached!");
        }

        @Override
        public synchronized void release() {
                if (this.currentPermits < maxPermits) {
                        this.notify();
                        System.out.println("One thread was notified!");
                        this.currentPermits++;
                } else
                        throw new IllegalArgumentException("Cannot release a permit!" +
                                " The threshold of "+this.maxPermits+" is asked!");
        }

        @Override
        public synchronized void release(int permits) {
                if ((this.currentPermits+permits) < this.maxPermits) {
                        this.currentPermits +=permits;
                        for (int i = 0; i < permits; i++) {
                               this.notify();
                        }
                } else
                        throw new IllegalArgumentException("Cannot release "+permits+" permits!" +
                                " The threshold of "+this.maxPermits+" is reached!");
        }

        @Override
        public synchronized int getAvailablePermits() {
                return this.currentPermits;
        }
}
