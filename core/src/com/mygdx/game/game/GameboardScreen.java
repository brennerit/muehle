package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Main;
import com.mygdx.game.startscreen.StartScreen;
import com.mygdx.game.startscreen.StartScreen.Mode;

/**
 * Diese Klasse repr�sentiert das eigentliche Spiel. Sie enth�lt das eigentliche
 * Spielfeld und den modus.
 * 
 * @author Ahmed
 *
 */
public class GameboardScreen extends ScreenAdapter {

	public enum PlayerId {
		PLAYER1, PLAYER2;
	}

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
		if (Gdx.input.isKeyJustPressed(Keys.ALT_LEFT)) {
	
		}
		if (Gdx.input.isKeyJustPressed(Keys.ALT_RIGHT)) {
			//Gdx.graphics.setWindowedMode(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
		}
	}

	/**
	 * Diese Methode schreibt die �berschrift, um welchen modus es sich handelt.
	 */
	private void printHeadline() {
		String title = (this.getMode() == Mode.VS_PLAYER) ? "Player Vs Player" : "Player vs CPU";
		this.game.getFont().draw(this.game.getBatch(), title, Main.WINDOW_WIDTH / 2 - 50, Main.WINDOW_HEIGHT - 50);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		InputHandler();

		this.gameboard.update();

		this.game.getBatch().begin();

		this.game.getBatch().draw(this.gameboard.getGamefieldTexture(),
				(Main.WINDOW_WIDTH / 2) - (this.gameboard.getGamefieldTexture().getWidth() / 2), 0, 400, 400);

		
		//Test GIb mausposition an
		this.game.getFont().draw(this.game.getBatch(), "PosX: " + Gdx.input.getX(), 10, 10);
	
		int posY = Main.WINDOW_HEIGHT - Gdx.input.getY()  ;
		this.game.getFont().draw(this.game.getBatch(), "PosY: " + posY, 10, 30);
		// Test Ende
		
		this.gameboard.render(this.game.getBatch());

		this.printHeadline();

		this.game.getBatch().end();
	}

	@Override
	public void dispose() {

		this.gameboard.dispose();
	}
}