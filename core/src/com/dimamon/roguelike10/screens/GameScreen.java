package com.dimamon.roguelike10.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.App;
import com.dimamon.roguelike10.common.CoordsTextLog;
import com.dimamon.roguelike10.common.Log;
import com.dimamon.roguelike10.entities.creatures.CreatureFactory;
import com.dimamon.roguelike10.entities.player.Player;
import com.dimamon.roguelike10.game.gameLog.GameLogRenderer;
import com.dimamon.roguelike10.map.GameMap;
import com.dimamon.roguelike10.sound.Sounds;

/**
 * MAIN GAME PROCESS SCREEN
 * Created by dimamon on 5/16/17.
 */
public class GameScreen extends AbstractScreen{

    private Log log = new Log("Game");

    private CoordsTextLog coordsTextLog;
    private Player player;
    private GameMap gameMap;

    private GameLogRenderer gameLogRenderer;

    public GameScreen(final App app) {
        super(app);
        player = new Player(CreatureFactory.getAndroidBlackPower(0), app);
        gameMap = new GameMap(player, app);
        coordsTextLog = new CoordsTextLog(gameMap);
        gameLogRenderer = new GameLogRenderer(app.font24);
    }

    @Override
    public void show() {
        Sounds.ambient();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        app.batch.begin();

        gameMap.render(app.batch);
        gameLogRenderer.render(app.batch);
        coordsTextLog.render(app.batch);
        app.drawBackground(0.1f);

        app.batch.end();
    }

    public void update (){
        gameMap.update();
        testInput();
    }

    @Override
    public void dispose () {
        gameMap.dispose();
        player.dispose();
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
