package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main;


public class DesktopLauncher {
	
	private final static int WINDOW_WIDTH = 800;

	private final static int WINDOW_HEIGHT = 600;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width =  DesktopLauncher.WINDOW_WIDTH;

		config.height =  DesktopLauncher.WINDOW_HEIGHT;
		
		new LwjglApplication(new Main(), config);
	}
}
