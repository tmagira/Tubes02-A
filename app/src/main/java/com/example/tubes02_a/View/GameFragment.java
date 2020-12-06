package com.example.tubes02_a.View;

import android.content.Context;
import android.content.Intent;
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
import android.view.View.OnTouchListener;
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

public class GameFragment extends Fragment implements View.OnTouchListener, View.OnClickListener {

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
    private ThreadStarter threadStarter;

    private Button start;
    private int score;
    private int life;
    private PointF pointF;
    private TextView tvScore, tvLife;

    public GameFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment, container, false);

        this.score = 0;
        this.threadList = new LinkedList<>();
        this.tileHandler = new TileHandler(this);
        this.threadStarter = new ThreadStarter(this);

        //Canvas
        this.ivCanvas1 = view.findViewById(R.id.iv_canvas1);
        this.ivCanvas2 = view.findViewById(R.id.iv_canvas2);
        this.ivCanvas3 = view.findViewById(R.id.iv_canvas3);
        this.ivCanvas4 = view.findViewById(R.id.iv_canvas4);

        this.start = view.findViewById(R.id.btnStart);

        start.setOnClickListener(this);
        this.ivCanvas1.setOnTouchListener(this);
        this.ivCanvas2.setOnTouchListener(this);
        this.ivCanvas3.setOnTouchListener(this);
        this.ivCanvas4.setOnTouchListener(this);

        //textview score
        this.tvScore = view.findViewById(R.id.score);
        this.tvLife = view.findViewById(R.id.tv_life);

        //bundle
      /*  GameFragment fragment = new GameFragment();
        Bundle args = getActivity().getIntent().getExtras();
        args.putInt("SCORE",score);
        fragment.setArguments(args);*/

        this.pointF = pointF;

        //jumlah nyawa
        this.life = 3;
        this.tvLife.setText(Integer.toString(this.life));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            this.listener = (FragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() + " Must Implement Fragment Listener");
        }
    }

    public static GameFragment newInstance() {
        GameFragment fragment = new GameFragment();
        return fragment;
    }

    public void createThreads() {

        //Menentukan kolom
        Random random = new Random();
        int randCol = random.nextInt(5 - 1) + 1;

        Tile tileEnd = new Tile(0, getScreenHeight(), getScreenWidth(), getScreenHeight() / 4, randCol);
        Tile tileStart = new Tile(0, 0, getScreenWidth(), getScreenHeight() / 4, randCol);

        this.threadList.addFirst(new TileThread(this.tileHandler, tileEnd, tileStart, randCol));
        this.threadList.getFirst().start();

    }

    @Override
    public void onClick(View v) {

        if (v == start) {
            initiateCanvas();

            this.threadStarter.startThread();

            this.start.setVisibility(View.GONE);

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int nomorKolom;
        switch (v.getId()) {
            case 2131230911:
                nomorKolom = 1;
                break;
            case 2131230912:
                nomorKolom = 2;
                break;
            case 2131230913:
                nomorKolom = 3;
                break;
            case 2131230914:
                nomorKolom = 4;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

        Tile touchedTile = new Tile(0, event.getY(), getScreenWidth() / 4, event.getY(), nomorKolom);
        for (int i = 0; i < this.threadList.size(); i++) {
            this.threadList.get(i).checkTouched(touchedTile);
        }

        return true;
    }


    public void initiateCanvas() {
        int screenWidth = getScreenWidth();
        int screenHeight = getScreenHeight();

        //Create BitMap
        this.mBitmap1 = Bitmap.createBitmap(screenWidth / 4, screenHeight, Bitmap.Config.ARGB_8888);
        this.mBitmap2 = Bitmap.createBitmap(screenWidth / 4, screenHeight, Bitmap.Config.ARGB_8888);
        this.mBitmap3 = Bitmap.createBitmap(screenWidth / 4, screenHeight, Bitmap.Config.ARGB_8888);
        this.mBitmap4 = Bitmap.createBitmap(screenWidth / 4, screenHeight, Bitmap.Config.ARGB_8888);

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

    public int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    //untuk tambah score
    public void addScore() {
        this.score += 1;
        Log.d("skor", "addScore: " + this.score);
        this.tvScore.setText(Integer.toString(this.score));
    }



    // public void setLife(int life){
    //    this.life =life;
    //}

    public void removeLife() {
        this.life--;
        if (life > 0) {
            this.tvLife.setText(Integer.toString(this.life));
        } else {
            this.threadStarter.terminate();

            for (int i = 0; i < this.threadList.size(); i++) {
                this.threadList.get(i).stopThread();
                this.threadList.remove(i);
            }
            if (this.threadList.isEmpty()) {
                this.listener.changePage(3);
            }

        }
    }

    public void setWhiteCirlce(Tile tile) {
        cekKolom(tile.getCol());
        this.kolom.drawRect(tile.getLeft(), tile.getTop(), tile.getRight() / 4, tile.getBottom(), paint);// tile.gettop
        this.iv.invalidate();
    }

    public void setRect(Tile tile) {
        cekKolom(tile.getCol());
        this.kolom.drawRect(tile.getLeft(), tile.getTop(), tile.getRight() / 4, tile.getBottom(), notePaint);// tile.gettop
        this.iv.invalidate();

    }

    public void cekKolom(int kolom) {
        switch (kolom) {
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
    }
}
