package com.dimamon.roguelike10.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.player.Player;

import static com.badlogic.gdx.math.MathUtils.*;

/**
 * Created by dimamon on 4/17/17.
 */

public class CoordsTextLog implements LibGdxable {

    Player player;
    BitmapFont font;
    float scaleInterfaceX = 1;
    float scaleInterfaceY = 1;

    public CoordsTextLog(Player player) {
        this.player = player;
        font = new BitmapFont();
    }

    @Override
    public void render(SpriteBatch batch) {

        // Block where mouse
        font.draw(batch,

                "X: "+ round(MapUtils.toMap(
                        round(Gdx.input.getX()/scaleInterfaceX)))+
                " Y: "+ round(MapUtils.toMap(
                        round(GameConfig.HEIGHT - Gdx.input.getY()/scaleInterfaceY))
                ) + " Turn: "+player.getStats().turnCount,
                0, GameConfig.HEIGHT);

        //Player stats
        font.draw(batch, "str:"+player.getAttributes().getStr()+
                " dex:"+player.getAttributes().getStr()+
                " mind:"+player.getAttributes().getMind()+
                " HP:"+player.getAttributes().getHp(),
                0, GameConfig.HEIGHT - 20);

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
