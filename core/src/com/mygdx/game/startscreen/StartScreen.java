package com.mygdx.game.startscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Main;
import com.mygdx.game.game.GameboardScreen;
import com.mygdx.game.icons.Cursor;

public class StartScreen extends ScreenAdapter {

	public enum Select {
		VS_PLAYER, VS_CPU, OPTION, END;
	}

	private Main game;

	private Select select;

	private int selectNumber;

	private Cursor cursor;

	private Texture title;

	public StartScreen(Main game) {
		this.game = game;

		this.title = new Texture("title.png");

		this.cursor = new Cursor(Main.WINDOW_WIDTH / 2 - 150, Main.WINDOW_HEIGHT / 2);

		this.select = Select.VS_CPU;
	}

	private void drawMenu() {

		int poxY = Main.WINDOW_HEIGHT / 2;
		this.game.getBatch().draw(this.title, 0, 0);

		this.game.getFont().draw(game.getBatch(), "Player vs CPU", Main.WINDOW_WIDTH / 2 - 50, Main.WINDOW_HEIGHT / 2);
		this.game.getFont().draw(game.getBatch(), "Player vs Player", Main.WINDOW_WIDTH / 2 - 50,
				Main.WINDOW_HEIGHT / 2 - 50);
		this.game.getFont().draw(game.getBatch(), "Spiel Beenden", Main.WINDOW_WIDTH / 2 - 50,
				Main.WINDOW_HEIGHT / 2 - 100);

		this.game.getBatch().draw(this.cursor.getTex(), this.cursor.getPosX(), this.cursor.getPosY());
	}

	private int maxChoice = 3;

	private void InputHandler() {

		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			this.executeEnter();

		} else {

			if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
				this.selectNumber++;
			}
			if (Gdx.input.isKeyJustPressed(Keys.UP)) {
				this.selectNumber--;
			}

			if (selectNumber >= maxChoice)
				this.selectNumber = 0;
			if (selectNumber < 0)
				this.selectNumber = maxChoice - 1;

			this.readSelectorByNumber();
		}
	}

	private void readSelectorByNumber() {
		switch (selectNumber) {
		case 0:
			this.select = this.select.VS_CPU;
			this.cursor.setPos(Main.WINDOW_WIDTH / 2 - 100, Main.WINDOW_HEIGHT / 2 - 20);
			break;
		case 1:
			this.select = this.select.VS_PLAYER;
			this.cursor.setPos(Main.WINDOW_WIDTH / 2 - 100, Main.WINDOW_HEIGHT / 2 - 70);
			break;
		case 2:
			this.select = this.select.END;
			this.cursor.setPos(Main.WINDOW_WIDTH / 2 - 100, Main.WINDOW_HEIGHT / 2 - 170);
			break;
		default:
			;
		}
	}

	private void executeEnter() {

		switch (this.select) {
		case VS_CPU:
			this.game.setScreen(new GameboardScreen(game));

			break;
		case VS_PLAYER:
			this.game.setScreen(new GameboardScreen(game));
			break;
		case END:
			super.dispose();
			this.dispose();
			this.game.dispose();

			System.exit(0);
			break;
		default:

		}
		this.game.setMode(this.select);
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
