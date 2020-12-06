package com.example.tubes02_a.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

import com.example.tubes02_a.R;

<<<<<<< Updated upstream
public class GameFragment extends Fragment implements GestureDetector.OnGestureListener, View.OnTouchListener, View.OnClickListener, SensorEventListener {

=======
import java.util.LinkedList;
import java.util.Random;

public class GameFragment extends Fragment implements View.OnTouchListener, View.OnClickListener{
    //test
>>>>>>> Stashed changes
    private FragmentListener listener;

    Bitmap mBitmap1, mBitmap2, mBitmap3, mBitmap4;
    ImageView ivCanvas1, ivCanvas2, ivCanvas3, ivCanvas4;
    Canvas mCanvas1, mCanvas2, mCanvas3, mCanvas4;
    Paint paint, notePaint;
    GestureDetector gestureDetector;
    boolean canvasInitiated = false;
    Button start;
    TextView tvScore;
    int score;
    int life;
    PointF pointF;
    protected ThreadHandler threadHandler, threadHandler2,threadHandler3,threadHandler4;
    protected MovingTileThread movingTileThread, movingTileThread2, movingTileThread3, movingTileThread4;
    SensorManager mSensorManager;
    Sensor accelerometer;
    Sensor magnetometer;
    float[] accelerometerReading;
    float[] magnetometerReading;
    float VALUE_DRIFT = 0.05f;

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

        //textview score
        this.tvScore = view.findViewById(R.id.score);
        this.score=0;

        this.pointF =pointF;

        //jumlah nyawa
        this.life = 1;

        //Gesture Detector
        this.gestureDetector = new GestureDetector(getContext(),this);

        //Threads
        this.threadHandler = new ThreadHandler(this);
        this.threadHandler2 = new ThreadHandler(this);
        this.threadHandler3 = new ThreadHandler(this);
        this.threadHandler4 = new ThreadHandler(this);

        this.movingTileThread = new MovingTileThread(this.threadHandler,this,1);
        this.movingTileThread2 = new MovingTileThread(this.threadHandler2,this,2);
        this.movingTileThread3 = new MovingTileThread(this.threadHandler3,this,3);
        this.movingTileThread4 = new MovingTileThread(this.threadHandler4,this,4);

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        this.accelerometer = this.mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.magnetometer = this.mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

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

            //Memulai Thread
            movingTileThread.startThread();
            movingTileThread2.startThread();
            movingTileThread3.startThread();
            movingTileThread4.startThread();


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

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        switch (sensorType){
            case Sensor.TYPE_ACCELEROMETER:
                this.accelerometerReading = event.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                this.magnetometerReading = event.values.clone();
                break;
        }
        if(magnetometerReading != null && accelerometerReading != null) {
            final float[] rotationMatrix = new float[9];
            mSensorManager.getRotationMatrix(rotationMatrix, null, accelerometerReading, magnetometerReading);

            final float[] orientationAngles = new float[3];
            mSensorManager.getOrientation(rotationMatrix, orientationAngles);

            float azimuth = orientationAngles[0];
            float pitch = orientationAngles[1];
            float roll = orientationAngles[2];

            if (Math.abs(azimuth) < VALUE_DRIFT) {
                azimuth = 0;
            }

            if (Math.abs(pitch) < VALUE_DRIFT) {
                pitch = 0;
            }

            if (Math.abs(roll) < VALUE_DRIFT) {
                roll = 0;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if ( accelerometer != null){
            mSensorManager.registerListener(this,accelerometer,mSensorManager.SENSOR_DELAY_NORMAL);
        }
        if ( magnetometer != null){
            mSensorManager.registerListener(this,magnetometer,mSensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
