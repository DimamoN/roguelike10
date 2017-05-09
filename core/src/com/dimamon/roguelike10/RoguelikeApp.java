package com.dimamon.roguelike10;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.common.CoordsTextLog;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.entities.creatures.CreatureFactory;
import com.dimamon.roguelike10.entities.player.Player;
import com.dimamon.roguelike10.map.GameMap;
import com.dimamon.roguelike10.sound.Sounds;

public class RoguelikeApp extends ApplicationAdapter {

	private Log log = new Log("Main");

	private CoordsTextLog coordsTextLog;

	private SpriteBatch batch;
	private Player player;
	private GameMap gameMap;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		player = new Player(CreatureFactory.getAndroidBlackPower(0));
		gameMap = new GameMap(player);
		coordsTextLog = new CoordsTextLog(gameMap);

		Sounds.ambient();
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		gameMap.render(batch);
		coordsTextLog.render(batch);

		batch.end();
	}
    public void update (){
		gameMap.update();
		testInput();
	}
	@Override
	public void dispose () {
		gameMap.dispose();
		player.dispose();
		batch.dispose();
		Sounds.dispose();
	}

	/**
	 * Input handle for test controls
	 */
	private void testInput(){
		//SWITCHING LEVELS
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
			log.log("Go to level 1");
			gameMap.setCurrentFloor(0);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
			log.log("Go to level 2");
			gameMap.setCurrentFloor(1);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
			log.log("Go to level 3");
			gameMap.setCurrentFloor(2);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)){
			log.log("Go to level 4");
			gameMap.setCurrentFloor(3);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)){
			log.log("Go to level 5");
			gameMap.setCurrentFloor(4);
		}

		//GENERATE FLOOR
		if(Gdx.input.isKeyJustPressed(Input.Keys.G)){
			log.log("Regenerate current floor");
			gameMap.getCurrentFloor().initMap();
		}

	}
}
