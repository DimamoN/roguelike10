package com.dimamon.roguelike10.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.App;
import com.dimamon.roguelike10.common.GdxClear;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.sound.Sounds;

public class LoseScreen extends AbstractScreen {

    public LoseScreen(final App app) {
        super(app);
    }

    @Override
    public void show() {
        Sounds.lose();
    }

    @Override
    public void render(float delta) {
        handleInput();
        GdxClear.clearScreen();

        app.batch.begin();
        app.font24.draw(app.batch,"YOU LOSE! Press SPACE to play again, Q to exit", GameConfig.WIDTH/3,GameConfig.HEIGHT/2);
        app.drawBackground(0.1f);
        app.batch.end();
    }

    private void handleInput(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            app.setScreen(new GameScreen(app));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            Gdx.app.exit();
        }

    }

    @Override
    public void dispose() {
    }
}
