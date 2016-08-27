package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.DebugMode;
import com.mygdx.game.Main;
import com.mygdx.game.observer.Event;
import com.mygdx.game.observer.Event.Event_Message;
import com.mygdx.game.observer.Observer;
import com.mygdx.game.startscreen.StartScreen;
import com.mygdx.game.startscreen.StartScreen.Mode;

/**
 * Diese Klasse repr�sentiert das eigentliche Spiel. Sie enth�lt das eigentliche
 * Spielfeld und den modus .
 * 
 * @author Ahmed
 *
 */
public class GameBoardScreen extends ScreenAdapter implements Observer {

	public enum PlayerId {
		PLAYER1, PLAYER2;
	}

	private Main game;

	private Mode mode;

	private GameBoard gameboard;

	private String message;

	public GameBoardScreen(Main game, Mode mode) {
		this.game = game;

		this.setMode(mode);

		this.message = "";
		
		this.gameboard = new GameBoard();

		this.gameboard.registry(this);
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
			// Gdx.graphics.setWindowedMode(Main.WINDOW_WIDTH,
			// Main.WINDOW_HEIGHT);
		}
	}

	/**
	 * Diese Methode schreibt die �berschrift, um welchen modus es sich handelt.
	 */
	private void printHeadline() {
		this.game.getFont().draw(this.game.getBatch(), message, Main.WINDOW_WIDTH / 2 - 50, Main.WINDOW_HEIGHT - 50);
	}

	public void setMessage(String message) {

		this.message = message;
	}

	public void readEventMessage(Event_Message e) {

		String tmp = "";
		switch (e) {
		case PLAYER1_TURN:
			tmp = "Spieler 1 ist dran";
			break;
		case PLAYER2_TURN:
			tmp = "Spieler 2 ist dran";
			break;
		case PLAYER1_WIN:
			tmp = "Spieler 1 hat gewonnen";
			break;
		case PLAYER2_WIN:
			tmp = "Spieler 2 hat gewonnen";
			break;
		}

		this.setMessage(tmp);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.InputHandler();

		this.gameboard.update();

		//Draw
		this.game.getBatch().begin();
		
		if(DebugMode.DEBUNG_ON){
			this.game.getFont().draw(this.game.getBatch(), "PosX: " + Gdx.input.getX(), 10, 10);
			int posY = Main.WINDOW_HEIGHT - Gdx.input.getY();
			this.game.getFont().draw(this.game.getBatch(), "PosY: " + posY, 10, 30);
		}

		this.gameboard.render(this.game.getBatch());

		this.printHeadline();

		this.game.getBatch().end();
	}

	@Override
	public void dispose() {

		this.gameboard.dispose();

	}

	@Override
	public void notifyObserver(Event.Event_Message event) {

		this.readEventMessage(event);

	}
}