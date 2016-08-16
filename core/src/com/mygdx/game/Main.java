package com.mygdx.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.game.GameboardScreen;
import com.mygdx.game.startscreen.StartScreen;
import com.mygdx.game.startscreen.StartScreen.Mode;

/**
 * Diese Klasse klärt grundlegendes. Sie enthält ein SpriteBatch Objekt für das Rendern der Grafik.
 * Zudem hat sie auch ein BitmapFont Objekt, für die Schrift.
 * Zudem ist hier die Größe des Fensters bekannt.
 * @author Ahmed
 */
public class Main extends Game {

	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 600;

	private SpriteBatch batch;

	private BitmapFont font;

	public BitmapFont getFont() {
		return this.font;
	}

	public SpriteBatch getBatch() {
		return this.batch;
	}
	
	@Override
	public void create() {
		
		batch = new SpriteBatch();

		font = new BitmapFont();

		font.setColor(0, 0, 0, 1);
		
		this.setScreen(new StartScreen(this));
	}

	@Override
	public void render() {
		super.render();

	}


	@Override
	public void dispose() {
	
		this.batch.dispose();
		this.font.dispose();
	}
}
