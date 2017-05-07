package com.dimamon.roguelike10.entities.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.dimamon.roguelike10.entities.creatures.impl.*;

/**
 * Created by dimamon on 4/9/17.
 */

public class CreatureFactory {

    private static final String DEMON_TEXTURE = "creatures/demon.png";
    private static final String ANDROID_TEXTURE = "creatures/android.png";
    private static final String ANDROID_BLUE_TEXTURE = "creatures/android_blue.png";

    public static Demon getDemon(int floor){
        return (Demon) new Demon(new Texture(DEMON_TEXTURE), "Small Demon", 10,10,10).setFloor(floor);
    }

    public static Android getAndroidPower(int floor) {
        return (Android) new Android(new Texture(ANDROID_TEXTURE), "Android", 100, 100, 100).setFloor(floor);
    }

    public static Android getAndroid(int floor){
        return (Android) new Android(new Texture(ANDROID_TEXTURE), "Android", 10,10,10).setFloor(floor);
    }

    public static Android getAndroidBlue(int floor, String name){
        return (Android) new Android(new Texture(ANDROID_BLUE_TEXTURE), "Android_blue:" + name, 10,10,10).setFloor(floor);
    }

}
