package com.example.tubes02_a.Model;

public class Tile {
    float left;
    float top;
    float right;
    float bottom;
    int col;

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Tile(float left, float top, float right, float bottom, int col) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.col = col;
    }
}
