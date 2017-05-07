package com.dimamon.roguelike10.tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.common.CoordsTextLog;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.entities.creatures.CreatureFactory;
import com.dimamon.roguelike10.entities.player.Player;
import com.dimamon.roguelike10.map.GameFloor;
import com.dimamon.roguelike10.map.GameMap;
import com.dimamon.roguelike10.map.generator.Coord;
import com.dimamon.roguelike10.map.generator.floor.impl.GridFloorGenerator;
import com.dimamon.roguelike10.map.generator.floor.impl.SimpleFloorGenerator;
import com.dimamon.roguelike10.sound.Sounds;

public class RoguelikeAppMapTest extends ApplicationAdapter {

	SpriteBatch batch;
	GameFloor floor;
	CoordsTextLog coordsTextLog;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		coordsTextLog = new CoordsTextLog();
//		floor = new GameFloor(new GridFloorGenerator(), 0, Coord.random());
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		floor.render(batch);
		coordsTextLog.render(batch);

		batch.end();
	}

    public void update (){
	}

	@Override
	public void dispose () {
		batch.dispose();
		floor.dispose();
		coordsTextLog.dispose();
		Sounds.dispose();
	}
}
