package com.dimamon.roguelike10.map.generator;

import com.badlogic.gdx.math.MathUtils;
import com.dimamon.roguelike10.config.MapUtils;
import com.dimamon.roguelike10.entities.creatures.Creature;
import com.dimamon.roguelike10.entities.creatures.CreatureFactory;
import com.dimamon.roguelike10.entities.creatures.impl.Android;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimamon on 4/15/17.
 */

public class CreatureGenerator {

    /**
     * MOBS COUNT PER LEVEL (MAX)
     */
    private static int ANDROID_BLUE_START_MAX = 3;
    private static int ANDROID_BLUE_START_MIN = 2;

    private static int ANDROID_RED_START_MAX = 1;
    private static int ANDROID_RED_START_MIN = 0;

    /**
     * Generate creatures, more level - more creatures
     * @param floor
     * @return
     */
    public static List<Creature> generateCreatures(int floor){

        List<Creature> creatures = new ArrayList<>();

        int androidBlueCount = MathUtils.random(
                ANDROID_BLUE_START_MIN + floor,
                ANDROID_BLUE_START_MAX + floor);
        for(int i = 0; i < androidBlueCount ; i++){
            creatures.add(CreatureFactory.getAndroidBlue(floor,"-"+i));
        }

        int androidRedCount = MathUtils.random(
                ANDROID_RED_START_MIN + floor,
                ANDROID_RED_START_MAX + floor);
        for(int i = 0; i < androidRedCount ; i++){
            creatures.add(CreatureFactory.getAndroidRed(floor,"-"+i));
        }

        return creatures;
    }


}
