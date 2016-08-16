package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Main;
import com.mygdx.game.game.GameboardPoint.StoneSide;
import com.mygdx.game.startscreen.StartScreen;
import com.mygdx.game.startscreen.StartScreen.Mode;

/**
 * Diese Klasse repräsentiert das eigentliche Spiel. Sie enthält das eigentliche Spielfeld und den modus.
 * @author Ahmed
 *
 */
public class GameboardScreen extends ScreenAdapter {

	private Main game;

	private Mode mode;

	private Gameboard gameboard;
	
	public GameboardScreen(Main game, Mode mode) {
		this.game = game;

		this.setMode(mode);
		
		this.gameboard = new Gameboard();
		
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Mode getMode() {
		return this.mode;
	}

	public void InputHandler() {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			this.game.setScreen(new StartScreen(game));
		}
	}

	/**
	 * Diese Methode schreibt die Überschrift, um welchen modus es sich handelt.
	 */
	private void printHeadline() {
		String title = (this.getMode() == Mode.VS_PLAYER)?"Player Vs Player":"Player vs CPU";
		this.game.getFont().draw(this.game.getBatch(),title , Main.WINDOW_WIDTH/2 - 50, Main.WINDOW_HEIGHT - 50);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		InputHandler();
		
		this.game.getBatch().begin();
		
		this.game.getBatch().draw(this.gameboard.getGamefield(), (Main.WINDOW_WIDTH / 2) - (this.gameboard.getGamefield().getWidth() / 2), 0, 400, 400);

		this.printHeadline();
		
		this.game.getBatch().end();
	}

	@Override
	public void dispose() {
		
		this.gameboard.dispose();
	}
}