package com.example.tubes02_a.View;

import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MovingTileThread implements Runnable {

    protected Thread thread;
    protected ThreadHandler threadHandler;
    protected GameFragment gameFragment;
    float widthTile = 0, heightTile = 0, startX = 0;
    boolean check = true;


    public MovingTileThread (ThreadHandler threadHandler, GameFragment gameFragment){
        this.thread = new Thread(this);
        this.gameFragment = gameFragment;
        this.threadHandler = threadHandler;
    }

    public void startThread(){
        this.thread.start();
    }

    private void refresh() {
        threadHandler.postDelayed(this,1000);
    }

    @Override
    public void run() {

        float kiri, kanan;

        if ( check == true){
            widthTile = this.gameFragment.screenX() / 4;
            heightTile = this.gameFragment.screenY()/ 4;
            check = false;
        }

        Random random = new Random();

        int columnATM = random.nextInt(5 - 1) + 1;

//        Log.d("test", Integer.toString(columnATM));

        kiri = startX + (( (float) columnATM - 1) * widthTile);
        kanan = kiri + widthTile;

        threadHandler.setLeft(String.valueOf(kiri));
        threadHandler.setRight(String.valueOf(kanan));

        refresh();
    }
}
