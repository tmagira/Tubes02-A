package com.example.tubes02_a.View;

import android.util.Log;

import java.util.Random;

public class MovingTileThread implements Runnable {

    protected Thread thread;
    protected ThreadHandler threadHandler;
    protected GameFragment gameFragment;

    public MovingTileThread (ThreadHandler threadHandler, GameFragment gameFragment){
        this.thread = new Thread(this);
        this.gameFragment = gameFragment;
        this.threadHandler = threadHandler;
    }

    public void startThread(){
        this.thread.start();
    }

    @Override
    public void run() {
        Random random = new Random();

        int column = random.nextInt(4 -1) + 1;

        if ( column == 1){

        }


    }
}
