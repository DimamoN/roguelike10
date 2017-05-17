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

    private static final Sound PICK = Gdx.audio.newSound(Gdx.files.internal("sfx/pick.mp3"));

    private static final Sound WIN = Gdx.audio.newSound(Gdx.files.internal("sfx/win.mp3"));
    private static final Sound LOSE = Gdx.audio.newSound(Gdx.files.internal("sfx/lose.mp3"));

    public static void ambient(){
        CAVE.play(0.5f);
    }

    public static void step(){

        int i = new Random().nextInt(3);
        switch (i) {
            case 0: {STEP.play(); break;}
            case 1: {STEP2.play(); break;}
            case 2: {STEP3.play(); break;}
            default:break;
        }
    }

    public static void monsterDeath(){
        DEATH.play(0.3f);
    }

    public static void attack(){
        ATTACK.play(0.4f);
    }

    public static void attackMob(){
        ATTACK.play(0.1f);
    }

    public static void pick(){
        PICK.play(0.8f);
    }

    public static void dispose(){
        STEP.dispose();
        STEP2.dispose();
        STEP3.dispose();
        ATTACK.dispose();
        DODGE.dispose();
        DEATH.dispose();
        PICK.dispose();
        WIN.dispose();
        LOSE.dispose();
    }

    public static void dodge(){
        DODGE.play(0.5f);
    }

    public static void win(){
        WIN.play(0.5f);
    }

    public static void lose(){
        LOSE.play(0.5f);
    }

    public static void click() {
        //TODO
    }
}
