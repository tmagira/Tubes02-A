package com.example.tubes02_a.View;

<<<<<<< Updated upstream
=======
import android.graphics.Color;
>>>>>>> Stashed changes
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
<<<<<<< Updated upstream

import com.example.tubes02_a.MainActivity;
=======
import android.util.Log;

import androidx.core.content.res.ResourcesCompat;

import com.example.tubes02_a.R;
>>>>>>> Stashed changes

public class ThreadHandler extends Handler {
    protected MainActivity mainActivity;
    protected final static int DRAW_RECT = 1;

<<<<<<< Updated upstream
    public ThreadHandler(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void handleMessage(Message msg){
        if(msg.what==ThreadHandler.DRAW_RECT){

        }
    }
    public void drawRect(PointF pointF,boolean isClicked){
        if(isClicked){
            return;
        }
=======
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
>>>>>>> Stashed changes
        Message msg = new Message();
        msg.what = ThreadHandler.DRAW_RECT;
        msg.obj = pointF;
        this.sendMessage(msg);
    }
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
}
