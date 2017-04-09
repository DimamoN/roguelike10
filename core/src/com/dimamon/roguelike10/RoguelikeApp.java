package com.dimamon.roguelike10;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.entities.Creature;
import com.dimamon.roguelike10.entities.CreatureFactory;
import com.dimamon.roguelike10.entities.Сontrollable;

public class RoguelikeApp extends ApplicationAdapter {

	private SpriteBatch batch;

	private CreatureFactory creatureFactory;
	private Creature demon;
	private Сontrollable player;

	@Override
	public void create () {
		batch = new SpriteBatch();


		creatureFactory = new CreatureFactory();
		demon = creatureFactory.getDemon();
		player = new Сontrollable(demon);
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		//HERE DRAW UNITS
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
		player.dispose();
		batch.dispose();
	}
}
