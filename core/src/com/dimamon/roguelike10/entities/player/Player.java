package com.dimamon.roguelike10.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.map.GameMap;
import com.dimamon.roguelike10.sound.Sounds;

/**
 * Wrapper for Creatures
 * It makes Creature controllable by player
 * Created by dimamon on 4/9/17.
 */
public class Player extends Creature implements LibGdxable {

    public Player(Creature creature) {
        super("Player", creature);
    }

    @Override
    public void update() {
        updateUserInput();
    }
    @Override
    public void dispose() {
        super.dispose();
    }
    @Override
    public void turn() {
//        log.log("Turn ["+stats.turnCount+"]");
        Sounds.step();
    }

    /**
     * Method for using in main update loop
     */
    public void updateUserInput(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)){

            //TODO: Нажатие кнопкки должно начинать ход!
            //а не ход, после перемещения игрока

            act(Direction.UP);
            initTurn();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            act(Direction.DOWN);
            initTurn();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            act(Direction.LEFT);
            initTurn();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            act(Direction.RIGHT);
            initTurn();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            onStairs();
        }
    }

    private void initTurn(){
        map.turn();
    }

    private void onStairs(){
        map.onStairs();
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public int getFloor() {
        return pos.floor;
    }

}
