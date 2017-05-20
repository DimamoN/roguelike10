package com.dimamon.roguelike10.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import static com.dimamon.roguelike10.config.GameConfig.GAME_SCREEN_BACKGROUND;

/**
 * Created by dimamon on 5/20/17.
 */

public class GdxClear {

    public static void clearScreen(){
        Gdx.gl.glClearColor(GAME_SCREEN_BACKGROUND.r, GAME_SCREEN_BACKGROUND.g, GAME_SCREEN_BACKGROUND.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
