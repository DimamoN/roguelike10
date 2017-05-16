package com.dimamon.roguelike10.game;


public class Statistics {

    int turnCount;
    int hit;
    int dodge;

    public void updTurnCount(){
        this.turnCount++;
    }

    public void updHitCount() {
        this.hit++;
    }

    public void updDodgeCount(){
        this.dodge++;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public int getHit() {
        return hit;
    }

    public int getDodge() {
        return dodge;
    }

}
