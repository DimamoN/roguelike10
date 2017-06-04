package com.dimamon.roguelike10.entities.items;

import com.badlogic.gdx.graphics.Texture;
import com.dimamon.roguelike10.config.GameConfig;
import com.dimamon.roguelike10.entities.items.end.EndTerminal;
import com.dimamon.roguelike10.entities.items.heals.Heal;
import com.dimamon.roguelike10.entities.object.Trap;

/**
 * Created by dimamon on 5/8/17.
 */

public class ItemsFactory {

    // ITEMS
    private static final String HEAL = "tiles/items/heal.png";
    private static final String END = "tiles/items/end.png";
    private static final String TRAP = "tiles/items/trap.png";

    private static final Texture heal = new Texture(HEAL);
    private static final Texture end = new Texture(END);
    private static final Texture trap = new Texture(TRAP);


    public static Item getHeal(){
        return new Heal("Small repair pack", GameConfig.DEFAULT_HEAL, heal);
    }

    public static Item getEndTerminal(){
        return new EndTerminal("End", end);
    }

    public static Item getTrap(){
        return new Trap("Trap", trap);
    }

}
