package com.game;

public class DamageLabel {
    String number;
    int x;
    int y;
    boolean isCrit;
    public DamageLabel(String number,int x, int y, boolean isCrit) {
        this.number = number;
        this.x = x;
        this.y = y;
        this.isCrit = isCrit;
    }
    public String getNumber() {
        return this.number;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setX(int x) {
        this.x -= x;
    }
    public void setY(int y) {
        this.y += y;
    }
    public boolean getIsCrit() {
        return this.isCrit;
    }
}
