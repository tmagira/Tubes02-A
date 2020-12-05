package com.example.tubes02_a.View;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

import com.example.tubes02_a.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadHandler extends Handler {
    protected GameFragment gameFragment;
    private Random r = new Random();
    private int col;
    private Canvas kolom;
    private ImageView iv;
    private boolean toDraw ;
    private float topPost = 0 ;
    private float bottomPost;
    protected final static int ADD_SCORE=1;
    protected final static int MIN_LIFE=2;

    public ThreadHandler(GameFragment gameFragment){
        this.gameFragment = gameFragment;
        this.bottomPost =   this.gameFragment.getScreenHeight() / 4;
    }

    @Override
    public void handleMessage( Message msg){

        //read message
        if (msg.what == 0){
            this.col = (int) msg.obj;
        }

        //menentukan kolom yang akan digambar
        switch(this.col) {
            case 1:
                this.kolom = this.gameFragment.mCanvas1;
                this.iv = this.gameFragment.ivCanvas1;
                break;
            case 2:
                this.kolom = this.gameFragment.mCanvas2;
                this.iv = this.gameFragment.ivCanvas2;
                break;
            case 3:
                this.kolom = this.gameFragment.mCanvas3;
                this.iv = this.gameFragment.ivCanvas3;
                break;
            case 4:
                this.kolom = this.gameFragment.mCanvas4;
                this.iv = this.gameFragment.ivCanvas4;
                break;
        }


        if(topPost<=this.gameFragment.getScreenHeight()){ //jika belum melewati batas bawah akan menggambar note
            drawNote();
        }else{                                            //jika sudah akan reset
            topPost = 0;
            bottomPost= this.gameFragment.getScreenHeight()/4;
        }
        if(msg.what == ThreadHandler.ADD_SCORE){
            this.gameFragment.addScore();
        }else if(msg.what == ThreadHandler.MIN_LIFE){
            this.gameFragment.minLife();
        }
    }
    //menambahkan score
    public void addScore(){
        Message msg = new Message();
        msg.what = ADD_SCORE;
        this.sendMessage(msg);
    }

    public void minLife(){
        Message msg = new Message();
        msg.what = MIN_LIFE;
        this.sendMessage(msg);
    }

    public void colToDraw(int col){
        Message msg = new Message();
        msg.what = 0;
        msg.obj = col;
        this.sendMessage(msg);
    }

    public void drawNote(){

        //menggerakkan koordinat note
        this.topPost += 10;
        this.bottomPost += 10;


        //jika belum melewati batas bawah layar dan true akan draw rect
        //jika sudah akan melewati maka akan random menentukan apakah akan draw atau tidak (true - ya, false - tidak)
        if(this.topPost<=this.gameFragment.getScreenHeight()) {
            if(toDraw){
                this.kolom.drawColor(Color.parseColor("#FF03DAC5"));
                this.kolom.drawRect(0, this.topPost, this.iv.getWidth(), this.bottomPost, this.gameFragment.notePaint);
                this.iv.invalidate();
            }
        }else if(this.topPost>this.gameFragment.getScreenHeight()){
            this.toDraw = r.nextBoolean();
            this.topPost=-50;
            this.bottomPost=topPost+gameFragment.getScreenHeight()/4;
            this.kolom.drawColor(Color.parseColor("#FF03DAC5"));
            this.iv.invalidate();

        }
    }
}
