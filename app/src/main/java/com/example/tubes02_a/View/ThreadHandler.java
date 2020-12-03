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

import java.util.Timer;
import java.util.TimerTask;

public class ThreadHandler extends Handler {

    protected GameFragment gameFragment;
    String random = "1";
    Canvas kolom;
    ImageView iv;
    float topPost ;
    float bottomPost ;
    public ThreadHandler(GameFragment gameFragment){
        this.gameFragment = gameFragment;
    }

    @Override
    public void handleMessage( Message msg){
        Timer timer = new Timer();
        Handler handler = new Handler();
        if (msg.what == 1){
            random = msg.obj.toString();
        }
        this.topPost = 0;
        this.bottomPost =  this.gameFragment.ivCanvas1.getHeight() / 4;

        switch(random) {
            case "1":
                this.kolom = this.gameFragment.mCanvas1;
                this.iv = this.gameFragment.ivCanvas1;
                break;
            case "2":
                this.kolom = this.gameFragment.mCanvas2;
                this.iv = this.gameFragment.ivCanvas2;
                break;
            case "3":
                this.kolom = this.gameFragment.mCanvas3;
                this.iv = this.gameFragment.ivCanvas3;
                break;
            case "4":
                this.kolom = this.gameFragment.mCanvas4;
                this.iv = this.gameFragment.ivCanvas4;
                break;
        }

        Log.d("sizez", "handleMessage: "+ random);
        //this.kolom.drawRect(0,this.topPost, 180,this.bottomPost, this.gameFragment.notePaint);
        this.iv.invalidate();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                final int[] i = {0};
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(topPost>gameFragment.ivCanvas1.getHeight()+10){
                            cancel();
                            Log.d("test", "run: berenti");
                        }else {drawNote();}
                    }
                });
            }
        }, 0, 20);

    }

    public void colToDraw(int output){
        Message msg = new Message();
        msg.what = 1;
        msg.obj = output;
        this.sendMessage(msg);
    }


    public void drawNote(){
        Log.d("sizez", "topPost: "+this.topPost);
        this.topPost+=10;
        this.bottomPost+=10;
        this.kolom.drawColor(Color.parseColor("#f55142"));
        this.kolom.drawRect(0,this.topPost, this.iv.getWidth(),this.bottomPost, this.gameFragment.notePaint);
        this.iv.invalidate();
    }
}
