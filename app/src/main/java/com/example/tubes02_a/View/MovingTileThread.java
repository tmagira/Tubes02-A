//package com.example.tubes02_a.View;
//
//import android.util.Log;
//import android.widget.Toast;
//
//import java.util.Random;
//
//public class MovingTileThread implements Runnable {
//
//    protected Thread thread;
//    protected ThreadHandler threadHandler;
//    protected GameFragment gameFragment;
//    boolean isCancelled;
//    boolean isClicked = false;
//    int col;
//
//    public MovingTileThread(ThreadHandler threadHandler, GameFragment gameFragment, int col) {
//        this.thread = new Thread(this);
//        this.gameFragment = gameFragment;
//        this.threadHandler = threadHandler;
//        this.col = col;
//    }
//
//    public void startThread() {
//        this.thread.start();
//    }
//
//
//    @Override
//    public void run() {
//        while (!isCancelled) {
//            try {
//                Thread.sleep(0,000000001);
//            } catch (InterruptedException e) {
//                Thread.interrupted();
//            }
//            threadHandler.removeCallbacksAndMessages(null);
//            threadHandler.colToDraw(this.col);
//            ;
//            if (isCancelled) break;
//        }
//
//    }
//
//    public void checkTap() {
//      //  if () {
//
//      //  }
//    }
//}
