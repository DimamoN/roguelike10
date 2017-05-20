package com.dimamon.roguelike10.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.dimamon.roguelike10.App;
import com.dimamon.roguelike10.common.GdxClear;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.sound.Sounds;

/**
 * Created by dimamon on 5/16/17.
 */
public class MainMenuScreen extends AbstractScreen{

    private Log log = new Log("Menu");

    Skin skin;
    TextButton buttonPlay, buttonExit;
    ShapeRenderer shapeRenderer;
    Stage stage;

    public MainMenuScreen(App app) {
        super(app);
        this.stage = new Stage(new StretchViewport(GameConfig.WIDTH, GameConfig.HEIGHT,app.camera));
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
        stage.clear();

        skin = new Skin();

        System.out.println("SKIN!!!" + skin);
        System.out.println("Font : " + app.font24);

        skin.add("default-font", app.font24);
        skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        skin.load(Gdx.files.internal("ui/uiskin.json"));

        initButtons();
    }

    private void initButtons(){
        buttonPlay = new TextButton("Play",skin,"default");
        buttonPlay.setPosition(110,260);
        buttonPlay.setSize(280,60);
        buttonPlay.addAction(Actions.moveBy(0,-20,.5f));
        buttonPlay.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Sounds.click();
                app.setScreen(new GameScreen(app));
            }
        });

        buttonExit = new TextButton("Exit", skin, "default");
        buttonExit.setPosition(110,190);
        buttonExit.setSize(280,60);
        buttonExit.addAction(Actions.moveBy(0,-20,.5f));
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Sounds.click();
                Gdx.app.exit();
            }
        });

        stage.addActor(buttonPlay);
        stage.addActor(buttonExit);
    }

    @Override
    public void render(float delta) {
        GdxClear.clearScreen();
        stage.draw();

        update(delta);
    }

    private void update(float delta){
        stage.act();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
