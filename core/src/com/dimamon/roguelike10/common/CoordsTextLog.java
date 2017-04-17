package com.dimamon.roguelike10.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.entities.LibGdxable;

/**
 *
 * TODO: SUPPORT SCALE
 * Created by dimamon on 4/17/17.
 */

public class CoordsTextLog implements LibGdxable {

    BitmapFont font;

    public CoordsTextLog() {
        font = new BitmapFont();
    }

    @Override
    public void render(SpriteBatch batch) {
        font.draw(batch,
                "X: "+MathUtils.round(MapUtils.toMap(Gdx.input.getX()))+
                " Y: "+MathUtils.round(MapUtils.toMap(GameConfig.HEIGHT - Gdx.input.getY())
                ),
                0, GameConfig.HEIGHT);
    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }
}
