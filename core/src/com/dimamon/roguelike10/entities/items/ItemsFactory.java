package com.dimamon.roguelike10.entities.items;

import com.dimamon.roguelike10.entities.items.heals.Heal;
import com.dimamon.roguelike10.map.tiles.TileFactory;

/**
 * Created by dimamon on 5/8/17.
 */

public class ItemsFactory {

    public static Item getHeal(){
        return new Heal("Heal", TileFactory.getHeal().getTexture());
    }

    public static Item getEnd(){
        return new Heal("End", TileFactory.getEnd().getTexture());
    }

}
