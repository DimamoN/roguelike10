package com.dimamon.roguelike10.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.dimamon.roguelike10.App;
import com.dimamon.roguelike10.common.GdxClear;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.game.gameLog.StaticGameLog;
import com.dimamon.roguelike10.sound.Sounds;

public class LoseScreen extends AbstractScreen {

    Image lose;

    public LoseScreen(final App app) {
        super(app);
    }

    @Override
    public void show() {
        Sounds.lose();
        StaticGameLog.reset();

        lose = new Image(new Texture("tiles/pic/lose.jpg"));
        lose.setBounds(0,0,1280,720);
    }

    @Override
    public void render(float delta) {
        handleInput();
        GdxClear.clearScreen();

        app.batch.begin();
        lose.draw(app.batch,0.75f);
        app.font24.draw(app.batch,"Press SPACE to play again, Q to exit",
                220,
                GameConfig.HEIGHT/2 - 80);
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
