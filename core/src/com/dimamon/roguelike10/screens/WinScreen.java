package com.dimamon.roguelike10.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.dimamon.roguelike10.App;
import com.dimamon.roguelike10.common.GdxClear;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.sound.Sounds;


public class WinScreen extends AbstractScreen {

    Image win;
    Color fontColor = new Color(0.5f,0.3f,0f,1f);
    BitmapFont font;

    public WinScreen(final App app) {
        super(app);

        font = new BitmapFont();
        font.setColor(fontColor);

        win = new Image(new Texture("tiles/pic/win.jpg"));
        win.setBounds(0,0,1280,720);

    }

    @Override
    public void show() {
        Sounds.win();
    }

    @Override
    public void render(float delta) {
        handleInput();
        GdxClear.clearScreen();

        app.batch.begin();
        win.draw(app.batch,0.75f);
        font.draw(app.batch,"YOU WIN! Press SPACE to play again, Q to exit",
                GameConfig.WIDTH/2 - 50,GameConfig.HEIGHT/2);
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
        font.dispose();
    }
}
