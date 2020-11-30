package com.example.tubes02_a.View;

import android.graphics.PointF;

public class MainThread extends Thread {
    Thread thread;
    ThreadHandler threadHandler;
    PointF pointF;

    boolean mulai;
    boolean selesai;

    public MainThread(ThreadHandler threadHandler, PointF pointF) {
        this.threadHandler = threadHandler;
        this.pointF = pointF;
        this.thread = new Thread(this);
        this.mulai = false;
        this.selesai=false;

    }
    public void run(){

    }
    public void checkScore(){}

}
