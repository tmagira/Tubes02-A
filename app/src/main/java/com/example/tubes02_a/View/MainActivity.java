package com.example.tubes02_a.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.tubes02_a.R;

public class MainActivity extends AppCompatActivity implements FragmentListener{

    private MainMenuFragment mainMenuFragment;
    private GameFragment gameFragment;
    private FragmentManager fragmentManager;
    private GameOverFragment gameOverFragment;
    private SettingFragment settingFragment;
    private FragmentTransaction ft;
    private int score;

//caca ubah ini
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initiate Variables
        this.fragmentManager = this.getSupportFragmentManager();
        this.mainMenuFragment = MainMenuFragment.newInstance();
        this.gameFragment = GameFragment.newInstance();
        this.gameOverFragment = GameOverFragment.newInstance();
        this.settingFragment =SettingFragment.newInstance();
        this.score=0;


        // hapus test
        changePage(1);
    }

    @Override
    public void changePage(int page) {
        this.ft = this.fragmentManager.beginTransaction();

        if (page == 1) {
            ft.replace(R.id.fragment_container, this.mainMenuFragment);
        }else if (page == 2) {
            ft.replace(R.id.fragment_container, this.gameFragment).addToBackStack(null);
        }else if (page == 3) {
            ft.replace(R.id.fragment_container, this.gameOverFragment).addToBackStack(null);
        }else if (page == 4) {
            ft.replace(R.id.fragment_container, this.settingFragment).addToBackStack(null);
        }
        this.ft.commit();
    }

    @Override
    public void closeApplication() {

    }

    @Override
    public void receiveMassage(String mssg) {
        Log.d("taheta", "receiveMassage: " +mssg);
        this.gameOverFragment.setScore(mssg);
        changePage(3);
    }


}