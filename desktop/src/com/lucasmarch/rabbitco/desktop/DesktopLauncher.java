package com.lucasmarch.rabbitco.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lucasmarch.rabbitco.RabbitAndCo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width  = 800;
		config.height = 600;
		config.backgroundFPS = 20;
		config.foregroundFPS = 20;
		new LwjglApplication(new RabbitAndCo(), config);
	}
}
