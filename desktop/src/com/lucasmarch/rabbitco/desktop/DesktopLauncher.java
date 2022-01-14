package com.lucasmarch.rabbitco.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lucasmarch.rabbitco.RabbitAndCo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width  = 850;
		config.height = 650;
		config.backgroundFPS = 30;
		config.foregroundFPS = 30;
		new LwjglApplication(new RabbitAndCo(), config);
	}
}
