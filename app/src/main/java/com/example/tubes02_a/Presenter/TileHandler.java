package com.example.tubes02_a.Presenter;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.tubes02_a.Model.Tile;
import com.example.tubes02_a.View.GameFragment;
import com.example.tubes02_a.View.MainActivity;

import java.util.logging.LogRecord;
import android.os.Message;

public class TileHandler extends Handler{
    protected final static int SET_RECT=0;
    protected final static int REMOVE_RECT=1;
    protected final static int ADD_SCORE=2;
    protected final static int REMOVE_LIFE=3;

    protected GameFragment gameFragment;

    public TileHandler(GameFragment gameFragment){
        this.gameFragment = gameFragment;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        if(msg.what == TileHandler.SET_RECT){
            Tile param = (Tile) msg.obj;
            this.gameFragment.setRect(param);
        } else if (msg.what == TileHandler.REMOVE_RECT){
            Tile param = (Tile) msg.obj;
            this.gameFragment.setWhiteCirlce(param);
        } else if(msg.what == TileHandler.ADD_SCORE){
            this.gameFragment.addScore();
        } else if(msg.what == TileHandler.REMOVE_LIFE){
            this.gameFragment.removeLife();
        }
    }


    public void addTile(Tile tile){
        Message msg = new Message();
        msg.what = SET_RECT;
        msg.obj = tile;
        this.sendMessage(msg);
    }

    public void removeTile(Tile tile){
        Message msg = new Message();
        msg.what = REMOVE_RECT;
        msg.obj = tile;
        this.sendMessage(msg);
    }

    public void addScore(){
        Message msg = new Message();
        msg.what = ADD_SCORE;
        this.sendMessage(msg);
    }

    public void removeLife(){
        Message msg = new Message();
        msg.what = REMOVE_LIFE;
        this.sendMessage(msg);
    }
}