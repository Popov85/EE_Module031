package com.goit.g2popov.ee031;

import java.util.concurrent.Semaphore;

/**
 * Created by Andrey on 30.08.2016.
 */
public class App implements Runnable {

        private TestMulti testMulti = new TestMulti();

        public static void main(String[] args) {
                //Semaphore semaphore = new Semaphore(1);
                for (int i = 0; i < 100 ; i++) {
                        new Thread(new App()).start();
                }
        }

        @Override
        public void run() {
                try {
                        TestMulti.doJob();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
