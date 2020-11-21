package com.example.tubes02_a.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.tubes02_a.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainMenuFragment extends Fragment implements View.OnClickListener {

    FragmentListener listener;
    FloatingActionButton play;
    Button button;

    public MainMenuFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_menu_fragment,container,false);

        this.play = view.findViewById(R.id.playButton);
        play.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if ( context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }else{
            throw new ClassCastException(context.toString()
                    + "must implement FragmentListener");
        }
    }
    public static MainMenuFragment newInstance(String title){
        MainMenuFragment fragment = new MainMenuFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        if ( play == v){
            this.listener.changePage(2);
        }
    }
}
