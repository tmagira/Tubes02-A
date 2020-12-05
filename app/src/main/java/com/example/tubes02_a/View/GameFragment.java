package com.example.tubes02_a.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes02_a.Model.Tile;
import com.example.tubes02_a.Model.TileThread;
import com.example.tubes02_a.Presenter.ThreadStarter;
import com.example.tubes02_a.Presenter.TileHandler;
import com.example.tubes02_a.R;

import java.util.LinkedList;
import java.util.Random;

public class GameFragment extends Fragment implements GestureDetector.OnGestureListener, View.OnTouchListener, View.OnClickListener{

    private FragmentListener listener;

    private Bitmap mBitmap1, mBitmap2, mBitmap3, mBitmap4;
    protected ImageView ivCanvas1, ivCanvas2, ivCanvas3, ivCanvas4, iv;
    protected Canvas mCanvas1, mCanvas2, mCanvas3, mCanvas4, kolom;
    protected Paint paint, notePaint;
    private int mColorBackground;
    private boolean canvasInitiated = false;

    private GestureDetector gestureDetector;
    private TileHandler tileHandler;
    private LinkedList<TileThread> threadList;

    private Button start;
    private int score;
    private int life = 3;
    private PointF pointF;

    private TextView tvScore, tvLife;

    public GameFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment,container, false);

        this.score = 0;
        this.threadList = new LinkedList<>();
        this.tileHandler = new TileHandler(this);

        //Canvas
        this.ivCanvas1 = view.findViewById(R.id.iv_canvas1);
        this.ivCanvas2 = view.findViewById(R.id.iv_canvas2);
        this.ivCanvas3 = view.findViewById(R.id.iv_canvas3);
        this.ivCanvas4 = view.findViewById(R.id.iv_canvas4);
//        this.ivCanvas.setOnTouchListener(this);

        this.start = view.findViewById(R.id.btnStart);
        start.setOnClickListener(this);

        //textview score
        this.tvScore = view.findViewById(R.id.score);
        this.score=0;

        this.pointF =pointF;

        //jumlah nyawa
        this.life = 3;

        //Gesture Detector
        this.gestureDetector = new GestureDetector(getContext(),this);

        //Threads
//        this.threadHandler = new ThreadHandler(this);
//        this.threadHandler2 = new ThreadHandler(this);
//        this.threadHandler3 = new ThreadHandler(this);
//        this.threadHandler4 = new ThreadHandler(this);
//
//        this.movingTileThread = new MovingTileThread(this.threadHandler,this,1);
//        this.movingTileThread2 = new MovingTileThread(this.threadHandler2,this,2);
//        this.movingTileThread3 = new MovingTileThread(this.threadHandler3,this,3);
//        this.movingTileThread4 = new MovingTileThread(this.threadHandler4,this,4);


        return view;
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }else{
            throw new ClassCastException(context.toString()+ " Must Implement Fragment Listener");
        }
    }

    public static GameFragment newInstance(){
        GameFragment fragment = new GameFragment();
        return fragment;
    }

    public void createThreads(){

            for (int i = 0; i < 10; i++) {
                Log.d("masuk", "onClick: life" + life);
            }


            Random random = new Random();
            float randX = random.nextInt(20) - 10;//edit
            float randTop = random.nextInt(20) - 10;
            int randCol = random.nextInt(5 - 1) + 1;

//            switch(randCol) {
//                case 1:
//                    this.kolom = mCanvas1;
//                    this.iv = ivCanvas1;
//                    break;
//                case 2:
//                    this.kolom = mCanvas2;
//                    this.iv = ivCanvas2;
//                    break;
//                case 3:
//                    this.kolom = mCanvas3;
//                    this.iv = ivCanvas3;
//                    break;
//                case 4:
//                    this.kolom = mCanvas4;
//                    this.iv = ivCanvas4;
//                    break;
//            }

            Tile tileEnd = new Tile(0, getScreenHeight(), getScreenWidth(), getScreenHeight() / 4, randCol);//top float)getScreenHeight()
            Tile tileStart = new Tile(0, 0, getScreenWidth() / 4, getScreenHeight() / 4, randCol);//top random
            Log.d("masuk", "onClick: " + tileStart.getRight());
            // Log.d("test", "onClick: "+coordinateDir.x);
            this.threadList.addFirst(new TileThread(this.tileHandler, tileEnd, tileStart, randCol));
            this.threadList.getFirst().start();

//            //Memulai Thread
//            movingTileThread.startThread();
//            movingTileThread2.startThread();
//            movingTileThread3.startThread();
//            movingTileThread4.startThread();




    }

    @Override
    public void onClick(View v) {

        if ( v == start){
            Log.d("masuk", "onClick: life"+life);
            initiateCanvas();


            ThreadStarter threadStarter = new ThreadStarter(this);
            threadStarter.startThread();

            this.start.setVisibility(View.GONE);

        }
    }

    public void initiateCanvas(){
       int screenWidth = getScreenWidth();
       int screenHeight = getScreenHeight();

        //Create BitMap
        this.mBitmap1 = Bitmap.createBitmap(screenWidth/4, screenHeight,Bitmap.Config.ARGB_8888);
        this.mBitmap2 = Bitmap.createBitmap(screenWidth/4, screenHeight,Bitmap.Config.ARGB_8888);
        this.mBitmap3 = Bitmap.createBitmap(screenWidth/4, screenHeight,Bitmap.Config.ARGB_8888);
        this.mBitmap4 = Bitmap.createBitmap(screenWidth/4, screenHeight,Bitmap.Config.ARGB_8888);

        this.ivCanvas1.setImageBitmap(mBitmap1);
        this.ivCanvas2.setImageBitmap(mBitmap2);
        this.ivCanvas3.setImageBitmap(mBitmap3);
        this.ivCanvas4.setImageBitmap(mBitmap4);

        this.mCanvas1 = new Canvas(mBitmap1);
        this.mCanvas2 = new Canvas(mBitmap2);
        this.mCanvas3 = new Canvas(mBitmap3);
        this.mCanvas4 = new Canvas(mBitmap4);

        this.mColorBackground = ResourcesCompat.getColor(getResources(), R.color.teal_200, null);
        paint = new Paint();
        paint.setColor(mColorBackground);

        int noteColor = ResourcesCompat.getColor(getResources(), R.color.black, null);
        notePaint = new Paint();
        notePaint.setColor(noteColor);

        this.mCanvas1.drawColor(mColorBackground);
        this.mCanvas2.drawColor(mColorBackground);
        this.mCanvas3.drawColor(mColorBackground);
        this.mCanvas4.drawColor(mColorBackground);

        this.canvasInitiated = true;

        this.ivCanvas1.invalidate();
        this.ivCanvas2.invalidate();
        this.ivCanvas3.invalidate();
        this.ivCanvas4.invalidate();
    }

    public  int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public  int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    //untuk tambah score
    public void addScore(){
        this.score += 1;
        this.tvScore.setText("Score"+Integer.toString(this.score));
    }

    public void minLife(){
        this.life -=1;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        PointF tap = new PointF();

        return this.gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("gesture", "onDown: onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
            Log.d("gesture", "onDown: onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("gesture", "onDown: onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("gesture", "onDown: on_scroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("gesture", "onDown: onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("gesture", "onDown: on_fling");
        return false;
    }

    public void addPenalty() {
    }

    public void setWhiteCirlce(Tile tile) {
        switch(tile.getCol()) {
                case 1:
                    this.kolom = mCanvas1;
                    this.iv = ivCanvas1;
                    break;
                case 2:
                    this.kolom = mCanvas2;
                    this.iv = ivCanvas2;
                    break;
                case 3:
                    this.kolom = mCanvas3;
                    this.iv = ivCanvas3;
                    break;
                case 4:
                    this.kolom = mCanvas4;
                    this.iv = ivCanvas4;
                    break;
            }
        this.kolom.drawRect(tile.getLeft(), tile.getTop(), tile.getRight()/4, tile.getBottom(), paint);// tile.gettop
        this.iv.invalidate();
    }

    public void setRect(Tile tile) {
        switch(tile.getCol()) {
                case 1:
                    this.kolom = mCanvas1;
                    this.iv = ivCanvas1;
                    break;
                case 2:
                    this.kolom = mCanvas2;
                    this.iv = ivCanvas2;
                    break;
                case 3:
                    this.kolom = mCanvas3;
                    this.iv = ivCanvas3;
                    break;
                case 4:
                    this.kolom = mCanvas4;
                    this.iv = ivCanvas4;
                    break;
            }
        this.kolom.drawRect(tile.getLeft(), tile.getTop(), tile.getRight()/4, tile.getBottom(), notePaint);// tile.gettop
        this.iv.invalidate();

    }
}
