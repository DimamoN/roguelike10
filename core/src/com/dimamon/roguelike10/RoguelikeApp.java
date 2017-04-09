package com.dimamon.roguelike10;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.creatures.CreatureFactory;
import com.dimamon.roguelike10.entities.map.GameFloor;
import com.dimamon.roguelike10.entities.map.GameMap;
import com.dimamon.roguelike10.entities.Сontrollable;

public class RoguelikeApp extends ApplicationAdapter {

	private SpriteBatch batch;

	private Creature android;
	private Сontrollable player;

	@Override
	public void create () {
		batch = new SpriteBatch();

		android = CreatureFactory.getAndroid(0);
		player = new Сontrollable(android);
		player.getControllableCreature().setPos(2,2);

		GameMap.addOnFloor(player.getControllableCreature(),0,3,3);
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		//DRAW HERE
		GameMap.getFloor(0).render(batch);

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
		GameMap.dispose();
		player.dispose();
		batch.dispose();
	}
}
