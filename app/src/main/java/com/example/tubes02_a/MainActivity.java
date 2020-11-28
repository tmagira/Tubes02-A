package com.example.tubes02_a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentListener{

    private MainMenuFragment mainMenuFragment;
    private FragmentManager fragmentManager;
    private GameFragment gameFragment;
    private FragmentTransaction ft;
//caca ubah ini
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initiate Variables
        this.fragmentManager = this.getSupportFragmentManager();
        this.mainMenuFragment = MainMenuFragment.newInstance();
        this.gameFragment = GameFragment.newInstance();
        //test//
        changePage(1);
    }

    @Override
    public void changePage(int page) {
        this.ft = this.fragmentManager.beginTransaction();

        if (page == 1) {
            ft.replace(R.id.fragment_container, this.mainMenuFragment).addToBackStack(null);
        }else if (page == 2) {
            ft.replace(R.id.fragment_container, this.gameFragment).addToBackStack(null);
        }
        this.ft.commit();
    }

    @Override
    public void closeApplication() {

    }
}