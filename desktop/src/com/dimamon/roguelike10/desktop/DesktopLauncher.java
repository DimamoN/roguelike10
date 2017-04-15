package com.dimamon.roguelike10.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dimamon.roguelike10.RoguelikeApp;
import com.dimamon.roguelike10.config.GameConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = GameConfig.HEIGHT;
		config.width = GameConfig.WIDTH;
		config.title = "roguelike10 - dev";
		new LwjglApplication(new RoguelikeApp(), config);
	}
}
