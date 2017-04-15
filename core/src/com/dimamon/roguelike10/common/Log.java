package com.dimamon.roguelike10.common;


import com.badlogic.gdx.Gdx;

public class Log {

    private String name;

    public Log(String name) {
        this.name = name;
    }

    public void log(String msg){
        Gdx.app.log(name,msg);
    }

    public void debug(String msg) {
        Gdx.app.debug(name, msg);
    }

    public void error(String msg) {
        Gdx.app.error(name, msg);
    }

    public void error(String msg, Throwable throwable) {
        Gdx.app.error(name, msg, throwable);
    }

}
