package com.example.tubes02_a.Presenter;

import android.util.Log;

import com.example.tubes02_a.Model.TileThread;
import com.example.tubes02_a.View.GameFragment;


public class ThreadStarter implements Runnable {
    protected Thread thread;
    GameFragment gameFragment;
    private volatile boolean running = true;
    TileThread tileThread;

    public void terminate() {
        running = false;
//        this.tileThread.stopThread();
        Log.d("life", "terminate: mati");
    }

    public ThreadStarter(GameFragment gameFragment){ this.thread = new Thread(this);
    this.tileThread  = tileThread;
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