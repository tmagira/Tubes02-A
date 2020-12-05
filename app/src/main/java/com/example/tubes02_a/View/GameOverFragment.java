package com.example.tubes02_a.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes02_a.R;

public class GameOverFragment extends AppCompatActivity implements View.OnClickListener {
    Button btnPlayAgain, btnMainMenu;
    TextView tvScore, tvHighScore;
    private FragmentListener listener;
    int score;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_menu_fragment, container, false);

        //Initiate Variables
        this.btnPlayAgain = view.findViewById(R.id.bt_playagain);
        this.btnMainMenu = view.findViewById(R.id.bt_mainmenu);
        this.tvHighScore = view.findViewById(R.id.score);
        this.tvScore = view.findViewById(R.id.tv_score);
        this.score = getIntent().getIntExtra("SCORE", 0);
        //Listeners
        tvScore.setText("Score:" + score);
        this.btnPlayAgain.setOnClickListener(this);
        this.btnMainMenu.setOnClickListener(this);

        //highscore
        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("HIGH_SCORE", 0);
        if (score > highScore) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.apply();

            tvHighScore.setText("Highs Score" + score);
        } else {
            tvHighScore.setText("Highs Score" + highScore);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == this.btnMainMenu) {
            listener.changePage(1);
        } else if (v == this.btnPlayAgain) {
            listener.changePage(2);

        }
    }
}

