package com.mygdx.game.startscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Main;
import com.mygdx.game.game.GameBoardScreen;
import com.mygdx.game.icons.Cursor;

public class StartScreen extends ScreenAdapter {

	public enum Mode {
		VS_PLAYER, VS_CPU, END;
	}

	private Main game;

	private Mode mode;

	private int menuPointNumber;

	private Cursor cursor;

	private Texture title;

	private int maxChoice = 3;

	private final int posYofMenuPoint;
	private final int posXofMenuPoint;

	public StartScreen(Main game) {
		this.game = game;

		this.title = new Texture("title.png");

		this.cursor = new Cursor(Main.WINDOW_WIDTH / 2 - 150, Main.WINDOW_HEIGHT / 2);

		this.posYofMenuPoint = Main.WINDOW_HEIGHT / 2;
		this.posXofMenuPoint = Main.WINDOW_WIDTH / 2 - 50;

		this.mode = Mode.VS_CPU;
	}

	/**
	 * Hier wird das Men� gezeichnet. Wichtig: Diese Methode muss innerhalb von
	 * batch.begin() und batch.end() ausgef�hrt werden
	 */
	private void drawMenu() {
		
		this.game.getBatch().draw(this.title, 0, 0);

		this.game.getFont().draw(game.getBatch(), "Player vs CPU", this.posXofMenuPoint, this.posYofMenuPoint);

		this.game.getFont().draw(game.getBatch(), "Player vs Player", this.posXofMenuPoint, this.posYofMenuPoint - 50);

		this.game.getFont().draw(game.getBatch(), "Spiel Beenden", this.posXofMenuPoint, this.posYofMenuPoint - 100);

		this.game.getBatch().draw(this.cursor.getTex(), this.cursor.getPosX(), this.cursor.getPosY());
	}

	/**
	 * Der Spieler kann entweder ENTER dr�cken, um einen Punkt auszuw�hlen oder
	 * aber mit den Pfeiltasten oben und unten den Cursor bewegen. Die Stelle
	 * des Cursors wird anhand der Variable menuPointNumber festgelegt. Damit
	 * wei� die Klasse an welcher stelle im men� sich der Cursor befindet.
	 */
	private void InputHandler() {

		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			this.executeEnter();

		} else {
			if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
				this.menuPointNumber++;
			}
			if (Gdx.input.isKeyJustPressed(Keys.UP)) {
				this.menuPointNumber--;
			}
			if (menuPointNumber >= maxChoice) {
				this.menuPointNumber = 0;
			}
			if (menuPointNumber < 0) {
				this.menuPointNumber = maxChoice - 1;
			}

			this.readCursorPosition();
			this.readMode();
		}
	}

	/**
	 * Hier wird die Position des Cursors Aktualisiert.
	 */
	private void readCursorPosition() {

		int posYCursor = this.posYofMenuPoint - 20;
		
		int posXCursor = this.posXofMenuPoint - 50;
		
		switch (this.menuPointNumber) {
		case 0:
			this.cursor.setPos(posXCursor, posYCursor);
			break;
		case 1:
			this.cursor.setPos(posXCursor, posYCursor - 50);
			break;
		case 2:
			this.cursor.setPos(posXCursor, posYCursor - 100);
			break;
		}
	}

	/**
	 * Hier wird der modus anhand des MenuPointNumber angepasst.
	 */
	private void readMode() {
		switch (this.menuPointNumber) {
		case 0:
			this.mode = Mode.VS_CPU;
			break;
		case 1:
			this.mode = Mode.VS_PLAYER;
			break;
		case 2:
			this.mode = Mode.END;
			break;
		}
	}

	/**
	 * Falls der Spieler ENTER dr�ckt, wird diese Methode aufgerufen.
	 */
	private void executeEnter() {
		switch (this.mode) {
		case VS_CPU:
			this.game.setScreen(new GameBoardScreen(this.game, Mode.VS_CPU));
			break;
		case VS_PLAYER:
			this.game.setScreen(new GameBoardScreen(this.game, Mode.VS_PLAYER));
			break;
		case END:
			this.dispose();
			this.game.dispose();
			Gdx.app.exit();
			break;
		}
		this.dispose();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.InputHandler();

		this.game.getBatch().begin();

		this.drawMenu();

		this.game.getBatch().end();

	}

	@Override
	public void dispose() {
		super.dispose();
		this.cursor.dispose();
		this.title.dispose();
	}
}
