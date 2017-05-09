package com.dimamon.roguelike10.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dimamon.roguelike10.common.Direction;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.creatures.params.Pos;
import com.dimamon.roguelike10.entities.items.Item;
import com.dimamon.roguelike10.entities.items.ItemsFactory;
import com.dimamon.roguelike10.entities.items.heals.Heal;
import com.dimamon.roguelike10.map.GameMap;
import com.dimamon.roguelike10.sound.Sounds;

/**
 * Wrapper for Creatures
 * It makes Creature controllable by player
 * Created by dimamon on 4/9/17.
 */
public class Player extends Creature implements LibGdxable {

    /**
     * What player can do
     */
    private PlayerAbilities playerAbilities;

    public Player(Creature creature) {
        super("Player", creature);
        playerAbilities = new PlayerAbilities();
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            pickItem();
        }

        //TEST
        if(Gdx.input.isKeyJustPressed(Input.Keys.O)){
            map.getCurrentFloor().putItem(pos, ItemsFactory.getHeal());
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

    public void pickItem(){
        Item item = map.getCurrentFloor().pickItem(pos);
        if(item != null){
            useItem(item);
        }
    }

    /**
     * Main usage of the items by player
     * @param item
     */
    private void useItem(Item item){
        if(item instanceof Heal){
            playerAbilities.heal(item);
        }
    }


    class PlayerAbilities{

        public void heal(Item item){
            Heal heal = (Heal) item;
            log.log("Healing with: " + heal.getName());
            attributes.addHp(heal.getPower());
        }
    }

}
