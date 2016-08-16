package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.game.GameboardScreen;
import com.mygdx.game.startscreen.StartScreen;
import com.mygdx.game.startscreen.StartScreen.Select;

public class Main extends Game {

	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 600;

	private SpriteBatch batch;

	private Texture img;

	private BitmapFont font;

	private Select mode;

	public void setMode(Select mode) {

		this.mode = mode;
	}

	public Select getMode() {

		return this.mode;
	}
	
	public BitmapFont getFont() {
		return this.font;
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

	public SpriteBatch getBatch() {
		return this.batch;
	}
}
