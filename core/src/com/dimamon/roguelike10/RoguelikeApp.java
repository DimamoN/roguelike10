package com.dimamon.roguelike10;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.screens.GameOverScreen;
import com.dimamon.roguelike10.screens.GameScreen;
import com.dimamon.roguelike10.screens.WinScreen;
import com.dimamon.roguelike10.sound.Sounds;

public class RoguelikeApp extends Game {

	private Log log = new Log("App");

	//SCREENS
	GameScreen gameScreen;
	WinScreen winScreen;
	GameOverScreen gameOverScreen;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		gameScreen = new GameScreen(this);
		winScreen = new WinScreen(this);
		gameOverScreen = new GameOverScreen(this);

		this.setScreen(gameScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		gameScreen.dispose();
		winScreen.dispose();
		gameOverScreen.dispose();
		Sounds.dispose();
	}

}
