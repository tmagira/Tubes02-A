package com.example.tubes02_a.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
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

    Bitmap mBitmap;
    ImageView ivCanvas;
    Canvas mCanvas;
    Paint paint;
    GestureDetector gestureDetector;
    boolean canvasInitiated = false;
    Button start;
    protected ThreadHandler objThread;
    protected MovingTileThread objTest;

    public GameFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment,container, false);

        //Canvas
        this.ivCanvas = view.findViewById(R.id.iv_canvas);
//        this.ivCanvas.setOnTouchListener(this);

        this.start = view.findViewById(R.id.btnStart);
        start.setOnClickListener(this);

        //Gesture Detector
        this.gestureDetector = new GestureDetector(getContext(),this);

        this.objThread = new ThreadHandler(this);
        this.objTest = new MovingTileThread(this.objThread,this);

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
            objTest.startThread();
        }
    }

    public void initiateCanvas(){

        //Create BitMap
        this.mBitmap = Bitmap.createBitmap(ivCanvas.getWidth(),ivCanvas.getHeight(),Bitmap.Config.ARGB_8888);
        this.ivCanvas.setImageBitmap(mBitmap);
        this.mCanvas = new Canvas(mBitmap);

        int mColorTest = ResourcesCompat.getColor(getResources(), R.color.design_default_color_primary_dark, null);

        paint = new Paint();
        paint.setColor(mColorTest);

        this.ivCanvas.invalidate();
    }

    public float screenX(){
        return this.ivCanvas.getWidth();
    }

    public float screenY(){
        return this.ivCanvas.getHeight();
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
