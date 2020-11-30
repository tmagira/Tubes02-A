package com.example.tubes02_a.View;

import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;

import com.example.tubes02_a.MainActivity;

public class ThreadHandler extends Handler {
    protected MainActivity mainActivity;
    protected final static int DRAW_RECT = 1;

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
        Message msg = new Message();
        msg.what = ThreadHandler.DRAW_RECT;
        msg.obj = pointF;
        this.sendMessage(msg);
    }
}
