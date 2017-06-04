package com.dimamon.roguelike10.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.App;
import com.dimamon.roguelike10.common.GdxClear;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.entities.TextureFactory;

/**
 * First screen, loads assets
 */
public class LoadingScreen extends AbstractScreen {

    private ShapeRenderer shapeRenderer;
    private float progress;

    public LoadingScreen(App app) {
        super(app);
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        this.progress = 0f;
        loadAssets();
    }

    @Override
    public void render(float delta) {
        GdxClear.clearScreen();
        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32,app.camera.viewportHeight/2 - 10, GameConfig.WIDTH - 64,20);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(32,app.camera.viewportHeight/2 - 10,progress * (GameConfig.WIDTH - 64),20);
        shapeRenderer.end();

        app.batch.begin();
        app.drawBackground(0.3f);
        app.font24.draw(app.batch, "LOADING...", GameConfig.WIDTH/2, 40);
        app.batch.end();

    }

    private void update(float delta){
        progress = MathUtils.lerp(progress,app.assets.getProgress(),.2f);

        if(app.assets.update() && progress >= app.assets.getProgress() - 0.1f){
            app.setScreen(app.menuScreen);
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    private void loadAssets(){
        //TEXTURES
        app.assets.load("tiles/road1.png", Texture.class);
        app.assets.load("tiles/road2.png", Texture.class);
        app.assets.load("tiles/rock.png", Texture.class);
        app.assets.load("tiles/stepLow.png", Texture.class);
        app.assets.load("tiles/stepUp.png", Texture.class);
        app.assets.load("tiles/wall2.png", Texture.class);
        app.assets.load("tiles/wallLeft.png", Texture.class);
        app.assets.load("tiles/wallRight.png", Texture.class);
        app.assets.load("tiles/wallUp.png", Texture.class);

        app.assets.load("tiles/items/end.png", Texture.class);
        app.assets.load("tiles/items/heal.png", Texture.class);

        //CREATURES
        app.assets.load("creatures/android.png", Texture.class);
        app.assets.load("creatures/android_blue.png", Texture.class);
        app.assets.load("creatures/android_red.png", Texture.class);
        app.assets.load("creatures/demon.png", Texture.class);

        //SOUNDS
        app.assets.load("sfx/cave.mp3", Sound.class);
        app.assets.load("sfx/click.mp3", Sound.class);
        app.assets.load("sfx/death.mp3", Sound.class);
        app.assets.load("sfx/dodge.wav", Sound.class);
        app.assets.load("sfx/hit07.mp3", Sound.class);
        app.assets.load("sfx/lose.mp3", Sound.class);
        app.assets.load("sfx/pick.mp3", Sound.class);
        app.assets.load("sfx/step.mp3", Sound.class);
        app.assets.load("sfx/step2.mp3", Sound.class);
        app.assets.load("sfx/step3.mp3", Sound.class);
        app.assets.load("sfx/lose.mp3", Sound.class);
        app.assets.load("sfx/click.mp3", Sound.class);

        //Font
//        app.assets.load("fonts/font.fnt", BitmapFont.class);

        app.assets.load("ui/uiskin.atlas", TextureAtlas.class);
        app.assets.load("ui/uiskin.png", Texture.class);

        // Backgrounds
        app.assets.load("ui/sand.jpg", Texture.class);

//        app.assets.load("ui/uiskin.json", Document.class);
    }

}