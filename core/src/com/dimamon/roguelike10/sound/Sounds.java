package com.dimamon.roguelike10.sound;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.Random;

public class Sounds {

    private static final Sound CAVE = Gdx.audio.newSound(Gdx.files.internal("sfx/cave.mp3"));

    private static final Sound STEP = Gdx.audio.newSound(Gdx.files.internal("sfx/step.mp3"));
    private static final Sound STEP2 = Gdx.audio.newSound(Gdx.files.internal("sfx/step2.mp3"));
    private static final Sound STEP3 = Gdx.audio.newSound(Gdx.files.internal("sfx/step3.mp3"));

    private static final Sound DEATH = Gdx.audio.newSound(Gdx.files.internal("sfx/death.mp3"));
    private static final Sound ATTACK = Gdx.audio.newSound(Gdx.files.internal("sfx/hit07.mp3"));
    private static final Sound DODGE = Gdx.audio.newSound(Gdx.files.internal("sfx/dodge.wav"));

    public static final void ambient(){
        CAVE.play(0.5f);
    }

    public static final void step(){

        int i = new Random().nextInt(3);
        switch (i) {
            case 0: {STEP.play(); break;}
            case 1: {STEP2.play(); break;}
            case 2: {STEP3.play(); break;}
            default:break;
        }
    }

    public static final void monsterDeath(){
        DEATH.play(0.5f);
    }

    public static final void attack(){
        ATTACK.play(0.4f);
    }

    public static final void attackMob(){
        ATTACK.play(0.2f);
    }

    public static void dispose(){
        STEP.dispose();
        STEP2.dispose();
        STEP3.dispose();
        ATTACK.dispose();
    }

    public static final void dodge(){
        DODGE.play();
    }
}
