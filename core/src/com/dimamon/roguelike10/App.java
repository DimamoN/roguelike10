package com.dimamon.roguelike10;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.screens.LoadingScreen;
import com.dimamon.roguelike10.screens.LoseScreen;
import com.dimamon.roguelike10.screens.GameScreen;
import com.dimamon.roguelike10.screens.MainMenuScreen;
import com.dimamon.roguelike10.screens.WinScreen;
import com.dimamon.roguelike10.sound.Sounds;

import java.awt.Font;

/**
 * Main application class
 * Contains screens
 */
public class App extends Game {

	private Log log = new Log("App");

	public OrthographicCamera camera;
	public BitmapFont font24;

	/**
	 * Render all staff
	 */
	public SpriteBatch batch;

	/**
	 * Asset Manager, to load textures, sounds, etc.
	 */
	public AssetManager assets;

	/**
	 * Screens
	 */
	public LoadingScreen loadingScreen;
	public MainMenuScreen menuScreen;
	public GameScreen gameScreen;
	public WinScreen winScreen;
	public LoseScreen loseScreen;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		batch = new SpriteBatch();

		assets = new AssetManager();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameConfig.WIDTH,GameConfig.HEIGHT);

		loadingScreen = new LoadingScreen(this);
		menuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		winScreen = new WinScreen(this);
		loseScreen = new LoseScreen(this);

		initFont();

		this.setScreen(loadingScreen);
	}

	private void initFont(){
		FreeTypeFontGenerator fg = new FreeTypeFontGenerator(Gdx.files.internal("fonts/micra.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		params.size = 24;
		params.color = Color.BLACK;
		font24 = fg.generateFont(params);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		gameScreen.dispose();
		winScreen.dispose();
//		gameScreen.dispose();
		Sounds.dispose();
		assets.dispose();
	}

}
