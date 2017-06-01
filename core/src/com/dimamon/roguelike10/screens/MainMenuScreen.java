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
import com.dimamon.roguelike10.entities.TextureFactory;
import com.dimamon.roguelike10.sound.Sounds;


public class MainMenuScreen extends AbstractScreen{

    private Log log = new Log("Menu");

    Skin skin;
    TextButton buttonPlay, buttonExit;
    ShapeRenderer shapeRenderer;
    Stage stage;

    private static int BUTTON_WIDTH = 350;
    private static int BUTTON_HEIGHT = 100;

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
        skin.add("default-font", app.font24);
        skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        skin.load(Gdx.files.internal("ui/uiskin.json"));

        initButtons();
    }

    private void initButtons(){
        buttonPlay = new TextButton("Play",skin,"default");
        buttonPlay.setPosition(GameConfig.WIDTH/2 - buttonPlay.getWidth(),GameConfig.HEIGHT/2);
        buttonPlay.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        buttonPlay.addAction(Actions.moveBy(0,-20,.5f));
        buttonPlay.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Sounds.click();
                stage.clear();
                app.setScreen(new GameScreen(app));
            }
        });

        buttonExit = new TextButton("Exit", skin, "default");
        buttonExit.setPosition(GameConfig.WIDTH/2 - buttonExit.getWidth(),
                GameConfig.HEIGHT/2 - BUTTON_HEIGHT);

        buttonExit.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);
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

        app.batch.begin();

        app.font24.draw(app.batch,GameConfig.FULL_NAME,GameConfig.WIDTH/2,GameConfig.HEIGHT - 100);

        app.drawBackground(0.08f);
        app.batch.end();

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
