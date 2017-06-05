package com.dimamon.roguelike10.game.gameLog;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.entities.LibGdxable;


public class GameLogRenderer implements LibGdxable{

    BitmapFont font;

    public GameLogRenderer(BitmapFont font) {
        this.font = font;
    }

    @Override
    public void render(SpriteBatch batch) {
        font.draw(batch, StaticGameLog.getLastMessage(),
                GameConfig.WIDTH / 1.8f, GameConfig.HEIGHT - 20);
    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }
}
