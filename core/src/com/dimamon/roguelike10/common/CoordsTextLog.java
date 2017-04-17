package com.dimamon.roguelike10.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.entities.LibGdxable;

import static com.badlogic.gdx.math.MathUtils.*;

/**
 * Created by dimamon on 4/17/17.
 */

public class CoordsTextLog implements LibGdxable {

    BitmapFont font;
    float scaleInterfaceX = 1;
    float scaleInterfaceY = 1;

    public CoordsTextLog() {
        font = new BitmapFont();
    }

    @Override
    public void render(SpriteBatch batch) {
        font.draw(batch,
                "X: "+ round(MapUtils.toMap(
                        round(Gdx.input.getX()/scaleInterfaceX)))+
                " Y: "+ round(MapUtils.toMap(
                        round(GameConfig.HEIGHT - Gdx.input.getY()/scaleInterfaceY))
                ),
                0, GameConfig.HEIGHT);

        update();
    }

    @Override
    public void update() {
        scaleInterfaceX = (float)Gdx.graphics.getWidth() / GameConfig.WIDTH;
        scaleInterfaceY = (float)Gdx.graphics.getHeight() / GameConfig.HEIGHT;
    }

    @Override
    public void dispose() {

    }
}
