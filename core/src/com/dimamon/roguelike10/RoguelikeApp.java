package com.dimamon.roguelike10;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.creatures.CreatureFactory;
import com.dimamon.roguelike10.entities.map.GameFloor;
import com.dimamon.roguelike10.entities.Сontrollable;

public class RoguelikeApp extends ApplicationAdapter {

	private SpriteBatch batch;

	private GameFloor gameFloor;

	private Creature demon;
	private Creature android;



	private Сontrollable player;

	@Override
	public void create () {
		batch = new SpriteBatch();

		gameFloor = new GameFloor();

		demon = CreatureFactory.getDemon(0);
		android = CreatureFactory.getAndroid(0);

		player = new Сontrollable(android);
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		//DRAW HERE
		gameFloor.render(batch);
		player.render(batch);


		batch.end();
	}

    /**
     * Method for updating game logic
     */
    public void update (){
		player.update();
	}

	@Override
	public void dispose () {
		gameFloor.dispose();
		player.dispose();
		batch.dispose();
	}
}
