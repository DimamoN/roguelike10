package com.dimamon.roguelike10.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.creatures.params.Pos;
import com.dimamon.roguelike10.entities.player.Player;
import com.dimamon.roguelike10.map.GameMap;

import static com.badlogic.gdx.math.MathUtils.*;

/**
 * Created by dimamon on 4/17/17.
 */

public class CoordsTextLog implements LibGdxable {

    GameMap map;
    Player player;

    BitmapFont font;
    float scaleInterfaceX = 1;
    float scaleInterfaceY = 1;

    // In Game Blocks
    int cursorX;
    int cursorY;

    public CoordsTextLog(GameMap map) {
        this.map = map;
        this.player = map.getPlayer();
        font = new BitmapFont();
    }

    @Override
    public void render(SpriteBatch batch) {

        // Block where mouse
        font.draw(batch,
                "X: " + cursorX + " Y: " + cursorY + " Level: " + map.currentFloor()+1,
                0, GameConfig.HEIGHT);

        // Player stats
        font.draw(batch, "str:"+player.getAttributes().getStr()+
                " dex:"+player.getAttributes().getStr()+
                " mind:"+player.getAttributes().getMind()+
                " HP:"+player.getAttributes().getHp()+
                " Turn: "+player.getStats().turnCount,
                0, GameConfig.HEIGHT - 20);

        // GameCreature
        font.draw(batch, map.getCurrentFloor().getOnPos(new Pos(cursorX,cursorY)).toString(),
                180,GameConfig.HEIGHT);



        update();
    }

    @Override
    public void update() {

        cursorX = round(MapUtils.toMap(
                round(Gdx.input.getX()/scaleInterfaceX)));
        cursorY = round(MapUtils.toMap(
                round(GameConfig.HEIGHT - Gdx.input.getY()/scaleInterfaceY)));

        scaleInterfaceX = (float)Gdx.graphics.getWidth() / GameConfig.WIDTH;
        scaleInterfaceY = (float)Gdx.graphics.getHeight() / GameConfig.HEIGHT;
    }

    @Override
    public void dispose() {

    }
}
