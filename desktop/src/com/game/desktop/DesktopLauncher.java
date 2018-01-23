package com.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.NaturalBestiality;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Natural Bestiality";
		config.width = 1080;
		config.height = 720;
		new LwjglApplication(new NaturalBestiality(), config);
	}
}
