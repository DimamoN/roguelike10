package com.dimamon.roguelike10;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.entities.Creature;
import com.dimamon.roguelike10.entities.CreatureFactory;

public class RoguelikeApp extends ApplicationAdapter {

	private SpriteBatch batch;

	private CreatureFactory creatureFactory;
	private Creature demon;

	@Override
	public void create () {
		batch = new SpriteBatch();


		creatureFactory = new CreatureFactory();
		demon = creatureFactory.getDemon();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		//HERE DRAW UNITS
		demon.render(batch);



		batch.end();
	}

    /**
     * Method for updating game logic
     */
    public void update (){

		demon.update();

	}

	@Override
	public void dispose () {
		batch.dispose();

		demon.dispose();
	}
}
