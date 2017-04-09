package com.dimamon.roguelike10.entities.creatures;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dimamon on 4/9/17.
 */

public class CreatureFactory {

    private static final String DEMON_TEXTURE = "creatures/demon.png";

    public Demon getDemon(){
        return new Demon(new Texture(DEMON_TEXTURE), "Small Demon", 10,10,10);
    }



}
