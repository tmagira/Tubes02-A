package com.example.tubes02_a.View;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.core.content.res.ResourcesCompat;

import com.example.tubes02_a.R;

import java.util.Timer;
import java.util.TimerTask;

public class ThreadHandler extends Handler {

    protected GameFragment gameFragment;
    String left = "1";
    String right = "1";
    int i=0;

    float leftPost , rightPost ;
    float topPost ;
    float bottomPost ;

    public ThreadHandler(GameFragment gameFragment){
        this.gameFragment = gameFragment;
    }

    @Override
    public void handleMessage( Message msg){
       Timer timer = new Timer();
       Handler handler = new Handler();

        if (msg.what == 0){
            left = (String)msg.obj;
        }
        if (msg.what == 1){
            right = (String)msg.obj;
        }

        this.leftPost = Float.valueOf(left);
        this.rightPost = Float.valueOf(right);
        this.topPost = -20;
        this.bottomPost =  this.gameFragment.ivCanvas.getHeight() / 4;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                final int[] i = {0};
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       if(topPost>gameFragment.ivCanvas.getHeight()){
                           cancel();
                           Log.d("test", "run: berenti");
                       }else {drawNote();}
                    }
                });
            }
        }, 0, 100);
    }

    public void setLeft(String output){
        Message msg = new Message();
        msg.what = 0;
        msg.obj = output;
        this.sendMessage(msg);
    }

     public void setRight(String output){
        Message msg = new Message();
        msg.what = 1;
        msg.obj = output;
        this.sendMessage(msg);
    }

    public void drawNote(){
        this.topPost+=10;
        this.bottomPost+=10;
        this.gameFragment.mCanvas.drawColor(Color.parseColor("#a6fff6"));
        this.gameFragment.mCanvas.drawRect(leftPost,topPost, rightPost, bottomPost, this.gameFragment.paint);
        this.gameFragment.ivCanvas.invalidate();
    }
}
