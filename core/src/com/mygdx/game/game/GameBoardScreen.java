package com.mygdx.game.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.DebugMode;
import com.mygdx.game.Main;
import com.mygdx.game.game.GameBoardPoint.StoneSide;
import com.mygdx.game.observer.Event;
import com.mygdx.game.observer.Event.Event_Message;
import com.mygdx.game.observer.Observer;
import com.mygdx.game.player.CPU;
import com.mygdx.game.player.Human;
import com.mygdx.game.player.Player;
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

	private Main game;

	private Mode mode;

	private GameBoard gameboard;

	private String headlineMode;
	private String message;
	private String messageAction;
	
	private Music music;

	private Player[] player;

	public GameBoardScreen(Main game, Mode mode) {
		this.game = game;

		this.setMode(mode);
		
		this.music = Gdx.audio.newMusic(Gdx.files.internal("music\\battle_theme.mp3"));
		this.music.setLooping(true);
		this.music.play();
		
		if (getMode() == Mode.VS_PLAYER) {
			this.headlineMode = "Spieler vs Spieler";
		} else {
			this.headlineMode = "Spieler vs CPU";

		}

		this.gameboard = new GameBoard();

		this.gameboard.getGameLogic().registry(this);

		this.player = new Player[2];
		this.player[0] = new Human(StoneSide.PLAYER1);
		if (this.mode == Mode.VS_PLAYER) {
			this.player[1] = new Human(StoneSide.PLAYER2);

		} else if ((this.mode == Mode.VS_CPU)) {
			this.player[1] = new CPU(StoneSide.PLAYER2);

		}

		this.gameboard.getGameLogic().notifyAllObserver(Event_Message.PLAYER1_TURN);
		this.gameboard.getGameLogic().notifyAllObserver(Event_Message.PLAYER_SET_STONE);

	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Mode getMode() {
		return this.mode;
	}

	private void InputHandler() {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			this.game.setScreen(new StartScreen(game));
			this.dispose();
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
		this.game.getFont().draw(this.game.getBatch(), this.headlineMode, Main.WINDOW_WIDTH / 2 - 50,
				Main.WINDOW_HEIGHT - 30);

		if (this.mode != Mode.VS_CPU) {
			this.game.getFont().draw(this.game.getBatch(), this.message, Main.WINDOW_WIDTH / 2 - 50,
					Main.WINDOW_HEIGHT - 70);

		}

		this.game.getFont().draw(this.game.getBatch(), this.messageAction, Main.WINDOW_WIDTH / 2 - 50,
				Main.WINDOW_HEIGHT - 110);

	}

	/**
	 * 
	 * @param event
	 */
	public void readEventMessage(Event_Message event) {

		String tmp = "";
		switch (event) {
		case PLAYER1_TURN:

			this.message = "Spieler 1 ist am Zug";
			this.gameboard.setPlayer(this.player[0]);
			break;
		case PLAYER2_TURN:
			if (this.mode == Mode.VS_PLAYER) {
				this.message = "Spieler 2 ist am Zug";
			} else {
				this.message = "Spieler 2 (CPU) ist am Zug";
			}
			this.gameboard.setPlayer(this.player[1]);
			break;
		case PLAYER1_WIN:
			this.message = "Spieler 1 hat gewonnen";
			break;
		case PLAYER2_WIN:
			this.message = "Spieler 2 hat gewonnen";
			break;
		case PLAYER_SET_STONE:
			this.messageAction = "Platziere einen Stein";
			break;
		case PLAYER_CAN_DELETE:
			this.messageAction = "Entferne einen Stein";
			break;
		case PLAYER_MOVE_STONE:
			this.messageAction = "Ziehe einen Stein";
			break;
		case PLAYER_CHOOSE_STONE:
			this.messageAction = " Wähle einen Stein um Ziehen ";
			break;
		}

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.InputHandler();

		// Draw
		this.game.getBatch().begin();

		this.printHeadline();

		if (DebugMode.DEBUNG_ON) {
			this.game.getFont().draw(this.game.getBatch(), "PosX: " + Gdx.input.getX(), 10, 20);
			int posY = Main.WINDOW_HEIGHT - Gdx.input.getY();
			this.game.getFont().draw(this.game.getBatch(), "PosY: " + posY, 10, 50);
			this.game.getFont().draw(this.game.getBatch(), "Runde: " + gameboard.getRoundNumber(), 10, 80);

		}

		this.gameboard.render(this.game.getBatch());

		this.printHeadline();

		this.game.getBatch().end();
	}

	@Override
	public void dispose() {

		this.gameboard.dispose();
		this.music.stop();
		this.music.dispose();

	}

	@Override
	public void notifyObserver(Event.Event_Message event) {

		this.readEventMessage(event);

	}
}