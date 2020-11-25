package com.example.tubes02_a;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainMenuFragment extends Fragment implements View.OnClickListener{
    private FragmentManager fragmentManager;
    private FragmentListener listener;
    private Button btnPlay;

    public MainMenuFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_menu_fragment,container, false);

        this.btnPlay = view.findViewById(R.id.btn_play);

        this.btnPlay.setOnClickListener(this);
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

    public static MainMenuFragment newInstance(){
        MainMenuFragment fragment = new MainMenuFragment();
        return fragment;
    }

    @Override
    public void onClick(View v) {
        if(v==this.btnPlay){
            this.listener.changePage(2);
        }
    }
}
