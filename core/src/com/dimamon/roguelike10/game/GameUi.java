package com.dimamon.roguelike10.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.App;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.player.Player;

/**
 * Contains render of player stats
 */
public class GameUi implements LibGdxable{

    private App app;
    private Player player;

    public GameUi(App app, Player player) {
        this.app = app;
        this.player = player;
    }

    @Override
    public void render(SpriteBatch batch) {

        // Player
        app.font24.draw(batch,
                        "LVL:"+player.getAttributes().level.level+
                        " XP:"+player.getAttributes().level.xp + "/" + player.getAttributes().level.nextLevelXP()+
                        " STR:"+player.getAttributes().getStr()+
                        " DEX:"+player.getAttributes().getStr()+
                        " PERC:"+player.getAttributes().getPerc()+
                        " HP:"+player.getAttributes().getHp() + "/" + player.getAttributes().getMaxHp(),
                0, GameConfig.HEIGHT - 20);
    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }
}
