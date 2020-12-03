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
//        if ( check == true){
//            widthTile = this.gameFragment.getScreenWidth() / 4;
//            heightTile = this.gameFragment.getScreenHeight()/ 4;
//            check = false;
//        }
//
        Random random = new Random();
        int columnATM = random.nextInt(5 - 1) + 1;
        Log.d("tHREADZ", Integer.toString(columnATM));

        //kiri = startX + (( (float) columnATM - 1) * widthTile);
       // kanan = kiri + widthTile;

        Log.d("threadz", "run: masuk 1");

        threadHandler.colToDraw(columnATM);
        //refresh();
        //
    }
}
