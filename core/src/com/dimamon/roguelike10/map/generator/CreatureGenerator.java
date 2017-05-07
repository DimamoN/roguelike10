package com.dimamon.roguelike10.map.generator;

import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.creatures.CreatureFactory;
import com.dimamon.roguelike10.entities.creatures.impl.Android;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimamon on 4/15/17.
 */

public class CreatureGenerator {

    public static List<Creature> generateCreatures(int count, int floor){

        List<Creature> creatures = new ArrayList<>();

        for(int i = 0; i <count; i++){
            creatures.add(CreatureFactory.getAndroidBlue(floor,"android-"+i));
        }
        return creatures;
    }


}
