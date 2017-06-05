package com.dimamon.roguelike10.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.App;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.creatures.params.Pos;
import com.dimamon.roguelike10.entities.items.Item;
import com.dimamon.roguelike10.entities.player.Player;
import com.dimamon.roguelike10.map.GameMap;

import java.util.List;
import java.util.Queue;

import static com.badlogic.gdx.math.MathUtils.*;

/**
 * Created by dimamon on 4/17/17.
 */

public class CoordsTextLog implements LibGdxable {

    GameMap map;
    Player player;
    Color fontColor = new Color(0.5f,0.3f,0f,1f);

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
        font.setColor(fontColor);
    }

    @Override
    public void render(SpriteBatch batch) {

        // Block where mouse
//        App.font24.draw(batch,
//                "X: " + cursorX + " Y: " + cursorY + " Level: " + (map.currentFloor()+1),
//                0, GameConfig.HEIGHT);

//         Player stats
//        font.draw(batch, "str:"+player.getAttributes().getStr()+
//                " dex:"+player.getAttributes().getStr()+
//                " perc:"+player.getAttributes().getPerc()+
//                " HP:"+player.getAttributes().getHp()+
//                " Turn: "+player.getStats().getTurnCount()+
//                " Hit: "+ player.getStats().getHit()+
//                " Dodge: "+ player.getStats().getDodge(),
//                0, GameConfig.HEIGHT - 20);

        // GameCreature
        List<Creature> onPos = map.getCurrentFloor().getOnPos(new Pos(cursorX, cursorY));
        if(!onPos.isEmpty()){
            App.font24.draw(batch, onPos.toString(),
                    180,GameConfig.HEIGHT);
        }

        Queue<Item> onPosItems = map.getCurrentFloor().getOnPosItems(new Pos(cursorX, cursorY));
        if(onPosItems !=null && !onPosItems.isEmpty()){
            App.font24.draw(batch, onPosItems.toString(),
                    GameConfig.WIDTH/2,GameConfig.HEIGHT);
        }


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
