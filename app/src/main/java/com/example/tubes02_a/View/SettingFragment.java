package com.example.tubes02_a.View;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes02_a.R;

public class SettingFragment extends Fragment implements View.OnClickListener {
    private FragmentManager fragmentManager;
    RadioGroup radioGroup;
    RadioButton rb_1, rb_3, rb_5;
    GameFragment gameFragment;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);
        this.radioGroup = (RadioGroup) view.findViewById(R.id.rg_life);
       // this.radioGroup.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);
        this.rb_1 = view.findViewById(R.id.radio_1);
        this.rb_3 = view.findViewById(R.id.radio_3);
        this.rb_5 = view.findViewById(R.id.radio_5);
        this.gameFragment = new GameFragment();

        //listener
        // this.rb_1.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        // this.rb_3.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        //this.rb_5.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        return view;
    }

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }


 /*   public void onCheckedChanged(RadioGroup group, int checkId) {
        switch (checkId) {
            case R.id.radio_1:
                gameFragment.setLife(Integer.getInteger((String) rb_1.getText()));
                Toast.makeText(getActivity().getApplicationContext(), "Life menjadi 1", Toast.LENGTH_LONG);
                break;

            case R.id.radio_3:
                gameFragment.setLife(Integer.getInteger((String) rb_3.getText()));
                Toast.makeText((getActivity().getApplicationContext()), "Life menjadi 3", Toast.LENGTH_LONG);
                break;

            case R.id.radio_5:
                gameFragment.setLife(Integer.getInteger((String) rb_5.getText()));
                Toast.makeText((getActivity().getApplicationContext()), "Life menjadi 5", Toast.LENGTH_LONG);
                break;

        }
 }  */


    @Override
    public void onClick(View v) {

    }


}

