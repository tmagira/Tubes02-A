package com.example.tubes02_a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class GameFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentListener listener;

    Bitmap mBitmap;
    ImageView ivCanvas;
    Canvas mCanvas;
    boolean canvasInitiated = false;

    public GameFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment,container, false);

        this.ivCanvas = view.findViewById(R.id.iv_canvas);

        initiateCanvas();
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

    public void initiateCanvas(){
        //Getting Screen Sizes
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;

        //Create BitMap
        this.mBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);

        this.ivCanvas.setImageBitmap(mBitmap);

        this.mCanvas = new Canvas(mBitmap);

        int mColorBackground = ResourcesCompat.getColor(getResources(), R.color.teal_200, null);
        this.mCanvas.drawColor(mColorBackground);

        this.canvasInitiated = true;
        this.ivCanvas.invalidate();
    }

}
