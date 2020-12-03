package com.example.tubes02_a.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes02_a.R;

public class GameFragment extends Fragment implements GestureDetector.OnGestureListener, View.OnTouchListener, View.OnClickListener{

    private FragmentListener listener;

    Bitmap mBitmap1, mBitmap2, mBitmap3, mBitmap4;
    ImageView ivCanvas1, ivCanvas2, ivCanvas3, ivCanvas4;
    Canvas mCanvas1, mCanvas2, mCanvas3, mCanvas4;
    Paint paint, notePaint;
    GestureDetector gestureDetector;
    boolean canvasInitiated = false;
    Button start;
    protected ThreadHandler threadHandler;
    protected MovingTileThread movingTileThread;

    public GameFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment,container, false);

        //Canvas
        this.ivCanvas1 = view.findViewById(R.id.iv_canvas1);
        this.ivCanvas2 = view.findViewById(R.id.iv_canvas2);
        this.ivCanvas3 = view.findViewById(R.id.iv_canvas3);
        this.ivCanvas4 = view.findViewById(R.id.iv_canvas4);
//        this.ivCanvas.setOnTouchListener(this);

        this.start = view.findViewById(R.id.btnStart);
        start.setOnClickListener(this);

        //Gesture Detector
        this.gestureDetector = new GestureDetector(getContext(),this);

        this.threadHandler = new ThreadHandler(this);
        this.movingTileThread = new MovingTileThread(this.threadHandler,this);

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

    @Override
    public void onClick(View v) {
        if ( v == start){
            initiateCanvas();
            movingTileThread.startThread();
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

        int mColorBackground = ResourcesCompat.getColor(getResources(), R.color.teal_200, null);
        paint = new Paint();
        paint.setColor(mColorBackground);

        int noteColor = ResourcesCompat.getColor(getResources(), R.color.black, null);
        notePaint = new Paint();
        notePaint.setColor(noteColor);

        this.mCanvas1.drawColor(mColorBackground);
        this.mCanvas2.drawColor(Color.parseColor("#f55142"));
        this.mCanvas3.drawColor(mColorBackground);
        this.mCanvas4.drawColor(Color.parseColor("#f55142"));


        this.canvasInitiated = true;
        this.ivCanvas1.invalidate();
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
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
}
