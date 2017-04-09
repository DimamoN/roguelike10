package com.dimamon.roguelike10.entities.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimamon.roguelike10.entities.GameObject;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.items.Item;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by dimamon on 4/10/17.
 */
public class GameTile implements GameObject {

    //Items on the floor
    private Queue<Item> items;
    //Creature that stay at this tile
    private Creature creature;
    //Background
    private Tile background;

    public GameTile(Tile background) {
        this.items = new ArrayDeque<Item>();
        this.background = background;
    }

    public GameTile(Queue<Item> items, Tile background) {
        this.items = items;
        this.background = background;
    }

    public void addItem(Item o){
        items.add(o);
    }

    public Item getItem(){
        return items.poll();
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
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
        if(creature != null) creature.render(batch, x, y);
    }

    //TODO: REMOVE!
    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void update() {
        creature.update();
    }

    @Override
    public void dispose() {
        for(Item item : items){
            item.dispose();
        }
    }
}
