package com.dimamon.roguelike10.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dimamon on 5/31/17.
 */

public class TextureFactory {

    private static final String SAND_BACKGROUND = "ui/sand.jpg";

    private static final Texture SAND = new Texture(Gdx.files.internal(SAND_BACKGROUND));

    public static Texture getSand(){
        return SAND;
    }
}
