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

public class ThreadHandler extends Handler {

    protected GameFragment gameFragment;
    String left = "1";
    String right = "1";

    public ThreadHandler(GameFragment gameFragment){
        this.gameFragment = gameFragment;
    }

    @Override
    public void handleMessage( Message msg){
        if (msg.what == 0){
            left = (String)msg.obj;
        }
        if (msg.what == 1){
            right = (String)msg.obj;
        }


        float leftPost = Float.valueOf(left), rightPost = Float.valueOf(right);
        float topPost = 0, bottomPost = this.gameFragment.ivCanvas.getHeight() / 4;
        this.gameFragment.mCanvas.drawColor(Color.parseColor("#a6fff6"));
        this.gameFragment.mCanvas.drawRect(leftPost,topPost, rightPost,bottomPost, this.gameFragment.paint);
        this.gameFragment.ivCanvas.invalidate();
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

}
