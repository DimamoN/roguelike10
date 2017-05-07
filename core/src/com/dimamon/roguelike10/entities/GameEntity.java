package com.dimamon.roguelike10.entities;


import com.badlogic.gdx.Gdx;
import com.dimamon.roguelike10.common.Log;

public class GameEntity {

    protected String name;
    protected Log log;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
