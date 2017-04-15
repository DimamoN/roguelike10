package com.dimamon.roguelike10;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.entities.creatures.CreatureFactory;
import com.dimamon.roguelike10.entities.player.Player;
import com.dimamon.roguelike10.map.GameMap;

public class RoguelikeApp extends ApplicationAdapter {

	private SpriteBatch batch;
	private Player player;
	private GameMap gameMap;

	@Override
	public void create () {
		batch = new SpriteBatch();

		//TODO : fix add on floor!

		player = new Player(CreatureFactory.getAndroid(0));
		player.setPos(2,2);

		gameMap = new GameMap(player);
		gameMap.putPlayerToFloor(0);

		for (int i = 0; i < 10 ; i++) {
			gameMap.addOnFloorRndSpace(CreatureFactory.getAndroidBlue(0),0);
		}

	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		gameMap.render(batch);

		batch.end();
	}
    public void update (){
		gameMap.update();
	}
	@Override
	public void dispose () {
		gameMap.dispose();
		player.dispose();
		batch.dispose();
	}
}
