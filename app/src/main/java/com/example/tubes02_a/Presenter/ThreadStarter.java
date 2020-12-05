package com.example.tubes02_a.Presenter;

import com.example.tubes02_a.View.GameFragment;


public class ThreadStarter implements Runnable {
    protected Thread thread;
    GameFragment gameFragment;
    private volatile boolean running = true;

    public void terminate() {
        running = false;
    }

    public ThreadStarter(GameFragment gameFragment){  this.thread = new Thread(this);
    this.gameFragment = gameFragment;
    }
    public void startThread() {
        this.thread.start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1500);
                this.gameFragment.createThreads();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}