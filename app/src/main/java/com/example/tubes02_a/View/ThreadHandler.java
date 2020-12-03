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
    Random r = new Random();
    int col,gap;
    Canvas kolom;
    ImageView iv;
    boolean toDraw ;
    float topPost = 0 ;
//    Random r = new Random();
    float bottomPost;
    public ThreadHandler(GameFragment gameFragment){
        this.gameFragment = gameFragment;
        this.bottomPost =   this.gameFragment.getScreenHeight() / 4;
    }

    @Override
    public void handleMessage( Message msg){
//        if (msg.what == 1){
//            this.col = (int) msg.obj;
//        }
//        if (msg.what == 0){
//            this.random = (boolean) msg.obj;
//        }

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

        if(topPost<=this.gameFragment.getScreenHeight()){
            drawNote();
        }else {
            topPost = 0;
            bottomPost= this.gameFragment.getScreenHeight()/4;
        }



                Log.d("tracker", "handleMessage: "+this.col+" "+topPost);

    }

    public void colToDraw(int col){
        Message msg = new Message();
        msg.what = 0;
        msg.obj = col;
        this.sendMessage(msg);

        this.col = col;
    }

    public void drawNote(){
        this.topPost += 10;
        this.bottomPost += 10;


        if(this.topPost<=gameFragment.ivCanvas1.getHeight()) {
            if(toDraw){
                Log.d("sizez", "topPost: " + this.topPost);
                this.kolom.drawColor(Color.parseColor("#f55142"));
                this.kolom.drawRect(0, this.topPost, this.iv.getWidth(), this.bottomPost, this.gameFragment.notePaint);
                this.iv.invalidate();
            }
        }else if(this.topPost>gameFragment.getScreenHeight()){
            this.toDraw = r.nextBoolean();
            this.topPost=-50;
            this.bottomPost=topPost+gameFragment.getScreenHeight()/4;
            this.kolom.drawColor(Color.parseColor("#f55142"));
            this.iv.invalidate();

        }
        Log.d("gap", "topPost: " + toDraw);
    }
}
