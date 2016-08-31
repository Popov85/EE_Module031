package com.goit.g2popov.ee031;


/**
 * Created by Andrey on 30.08.2016.
 */
public class App implements Runnable {

        private TestMulti testMulti = new TestMulti();

        public static void main(String[] args) throws InterruptedException {
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
