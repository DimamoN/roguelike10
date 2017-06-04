package com.dimamon.roguelike10.map.gameTile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.entities.LibGdxable;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.items.Item;
import com.dimamon.roguelike10.map.tiles.Tile;
import com.dimamon.roguelike10.map.tiles.TileFactory;

import java.util.ArrayDeque;
import java.util.Queue;

public class GameTile implements LibGdxable {

    //Items on the floor
    private Queue<Item> items;

    //Trap or monument
    private Item object;

    //Background
    private Tile background;

    public GameTile(Tile background) {
        this.items = new ArrayDeque<>();
        this.background = background;
    }

    public boolean isBlocking(){
        return background.isBlocking();
    }

    /**
     * Render in this way:
     * Creature - Items - Background
     * @param batch
     * @param x
     * @param y
     */
    public void render(SpriteBatch batch, int x, int y) {
        background.render(batch, x, y);
        if(!items.isEmpty()) items.peek().render(batch,x,y);
        if(object != null) object.render(batch,x,y);
    }

    public void put(Item item){
        items.add(item);
    }

    public Queue<Item> getItems() {
        return items;
    }

    public Item getObject() {
        return object;
    }

    public void setObject(Item object) {
        this.object = object;
    }

    //TODO: REMOVE!
    @Override
    public void render(SpriteBatch batch) {

    }
    @Override
    public void update() {
    }
    @Override
    public void dispose() {
        for(Item item : items){
            item.dispose();
        }
    }

    public boolean isStepLow() {
        if(background == TileFactory.getStepLow()){
            return true;
        } else return false;
    }

    public boolean isStepUp() {
        if(background == TileFactory.getStepUp()){
            return true;
        } else return false;
    }

    public Item pick() {
        return items.poll();
    }
}
