package com.example.tubes02_a.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tubes02_a.R;

public class MainActivity extends AppCompatActivity implements FragmentListener {

    MainMenuFragment mainMenuFragment;

    FragmentManager fragmentManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mainMenuFragment = MainMenuFragment.newInstance("MainMenu Fragment");

        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        this.fragmentManager = this.getSupportFragmentManager();
        this.changePage(1);
    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if ( page == 1){
            ft.replace(R.id.fragment_container, this.mainMenuFragment).addToBackStack(null);
        }

        ft.commit();
    }

    @Override
    public void changeMessage(String message) {

    }

    @Override
    public void closeApplication() {

    }
}