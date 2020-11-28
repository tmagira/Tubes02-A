package com.example.tubes02_a.Model;

public class Tile {
    int positionX;
    int positionY;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Tile(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
