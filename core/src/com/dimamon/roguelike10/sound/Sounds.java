package com.dimamon.roguelike10.sound;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.Random;

public class Sounds {

    private static final Sound STEP = Gdx.audio.newSound(Gdx.files.internal("sfx/step.mp3"));
    private static final Sound STEP2 = Gdx.audio.newSound(Gdx.files.internal("sfx/step2.mp3"));
    private static final Sound STEP3 = Gdx.audio.newSound(Gdx.files.internal("sfx/step3.mp3"));

    public static final void step(){
        int i = new Random().nextInt(3);
        switch (i) {
            case 0: {STEP.play(); break;}
            case 1: {STEP2.play(); break;}
            case 2: {STEP3.play(); break;}
            default:break;
        }
    }


    public static void dispose(){
        STEP.dispose();
        STEP2.dispose();
        STEP3.dispose();
    }

}
