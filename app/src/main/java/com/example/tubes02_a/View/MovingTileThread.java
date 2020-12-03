package com.example.tubes02_a.View;

import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MovingTileThread implements Runnable {

    protected Thread thread;
    protected ThreadHandler threadHandler;
    protected GameFragment gameFragment;
    boolean isCancelled;
    int col ;


    public MovingTileThread (ThreadHandler threadHandler, GameFragment gameFragment, int col){
        this.thread = new Thread(this);
        this.gameFragment = gameFragment;
        this.threadHandler = threadHandler;
        this.col = col;
    }

    public void startThread(){

        this.thread.start();
    }

    private void refresh() {
        threadHandler.postDelayed(this,2000);
    }

    @Override
    public void run() {
        while(!isCancelled) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
            threadHandler.removeCallbacksAndMessages(null);
            threadHandler.colToDraw(this.col);;

            if(isCancelled) break;
        }

    }
}
