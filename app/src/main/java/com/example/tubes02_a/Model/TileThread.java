package com.example.tubes02_a.Model;

import android.util.Log;

import com.example.tubes02_a.Presenter.TileHandler;

public class TileThread extends Thread{
    protected TileHandler tileHandler;
    protected Tile tileStart, tileEnd;
    protected float leftStart, topStart, rightStart, botStart;
    protected float leftEnd, topEnd, rightEnd, botEnd;
    protected boolean stopped = false;
    protected boolean isClicked = false;
    protected int randCol;


    public TileThread(TileHandler tileHandler, Tile tileEnd, Tile tileStart, int col){
        this.tileHandler = tileHandler;

        //Set Koordinat Start
        this.tileStart = tileStart;
        this.leftStart = tileStart.getLeft();
        this.rightStart = tileStart.getRight();
        this.topStart = tileStart.getTop();
        this.botStart = tileStart.getBottom();

        //Set Koordinat Akhir
        this.tileEnd = tileEnd;
        this.leftEnd = tileEnd.getLeft();
        this.rightEnd = tileEnd.getRight();
        this.topEnd = tileEnd.getTop();
        this.botEnd = tileEnd.getBottom();

        this.randCol = col;
    }

    public void stopThread(){
        Log.d("TAG", "stopThread: ");
        this.stopped = true;
    }

    public void run() {
        this.stopped = false;
        while(checkValid(this.tileStart.getTop())){
            try {
                Thread.sleep(100);
                if(!this.isClicked){
                    tileHandler.removeTile(new Tile(this.tileStart.getLeft(), this.tileStart.getTop(), this.tileStart.getRight(), this.tileStart.bottom, randCol));
                }
                this.tileStart.setTop(this.tileStart.getTop() + 20);
                this.tileStart.setBottom(this.tileStart.getBottom() + 20);
                if(!this.isClicked){
                    tileHandler.addTile(new Tile(this.tileStart.getLeft(), this.tileStart.getTop(), this.tileStart.getRight(), this.tileStart.bottom, randCol));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(!this.isClicked){
            tileHandler.removeLife();
        }
        return;
    }

//    public void checkTap(Coordinate tap){
//        if(!isClicked){
//            if(tap.getX() >= this.coordinateStart.getX() - 100 && tap.getX() <= this.coordinateStart.getX() + 100){
//                if(tap.getY() >= this.coordinateStart.getY() - 100 && tap.getY() <= this.coordinateStart.getY() + 100){
//                    this.uiThreadedWrapper.addScore();
//                    this.isClicked = true;
//                    //this.stopThread();
//                    uiThreadedWrapper.clearCircle(new Coordinate(this.coordinateStart.getX(), this.coordinateStart.getY()));
//                }
//            }
//        }
//    }

    public boolean checkValid(float top){
        if(top < 0){
            return false;
        }
        if(top > this.tileEnd.getTop()+10){
            return false;
        }
        return true;
    }
}
