package com.mygdx.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.game.GameBoardScreen;
import com.mygdx.game.startscreen.StartScreen;
import com.mygdx.game.startscreen.StartScreen.Mode;

/**
 * Diese Klasse kl�rt grundlegendes. Sie enth�lt ein SpriteBatch Objekt f�r das
 * Rendern der Grafik. Zudem hat sie auch ein BitmapFont Objekt, f�r die
 * Schrift. Zudem ist hier die Gr��e des Fensters bekannt.
 * 
 * @author Ahmed
 */
public class Main extends Game {

	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 600;

	private SpriteBatch batch;

	private BitmapFont font;

	private OrthographicCamera camera;

	@Override
	public void create() {

		this.batch = new SpriteBatch();

		this.font = new BitmapFont();

		this.font.setColor(0, 0, 0, 1);

		this.camera = new OrthographicCamera();
		camera.setToOrtho(true,Main.WINDOW_WIDTH,8000);
		
		this.setScreen(new StartScreen(this));
	}

	public BitmapFont getFont() {
		return this.font;
	}

	public SpriteBatch getBatch() {
		return this.batch;
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
