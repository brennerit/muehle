package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Main;
import com.mygdx.game.game.gameStone.StoneSide;
import com.mygdx.game.startscreen.StartScreen;
import com.mygdx.game.startscreen.StartScreen.Select;

/**
 * Diese Klasse Repr‰sentiert das Spielfeld. Das Spielfeld besteht aus
 * Spielsteine und ist 9 * 9 groﬂ Dabei sind die viele Felder NULL, da sie nicht
 * gebraucht werden.
 * 
 * @author Ahmed
 *
 */
public class GameboardScreen extends ScreenAdapter {

	private gameStone[] field;

	private final int FIELD_LENGHT = 7;

	private Main game;

	private Texture gamefield;

	public GameboardScreen(Main game) {
		this.game = game;

		this.field = new gameStone[FIELD_LENGHT * FIELD_LENGHT];

		this.gamefield = new Texture("muehle_board.png");

		this.initField();

		this.printField();
	}

	/**
	 * Diese Methode Initialisiert das Spielfeld von innen nach auﬂen.
	 * 
	 */
	private void initField() {
		int decency = 1;
		int posX = 2;
		int posY = 2;

		for (int loop = 0; loop < 3; loop++) {
			for (int height = 0; height < 3; height++) {
				for (int width = 0; width < 3; width++) {

					setFieldElement(posX, posY);

					posX += decency;
				}

				posX -= (decency * 3);

				posY += decency;
			}

			posX -= 1;

			posY = posY - (decency * 3) - 1;

			decency++;
		}
		this.getFieldElement(3, 3).setSide(StoneSide.MIDDLE);

	}

	private void setFieldElement(int x, int y) {
		this.field[(y * this.FIELD_LENGHT) + x] = new gameStone(gameStone.StoneSide.WITHOUT_STONE);
	}

	private gameStone getFieldElement(int x, int y) {

		return this.field[x + y * this.FIELD_LENGHT];
	}

	private void printField() {

		for (int height = 0; height < FIELD_LENGHT; height++) {
			for (int width = 0; width < FIELD_LENGHT; width++) {
				gameStone stein = this.getFieldElement(width, height);
				if (stein == null) {
					System.out.print("O ");
				} else if (stein.getSide() == gameStone.StoneSide.PLAYER1) {
					System.out.print("1 ");
				} else if (stein.getSide() == gameStone.StoneSide.PLAYER2) {
					System.out.print("2 ");
				} else if (stein.getSide() == gameStone.StoneSide.WITHOUT_STONE) {
					System.out.print("X ");
				} else {
					System.out.print("H ");
				}

			}
			System.out.println();
		}

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	public void InputHandler() {

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			this.game.setScreen(new StartScreen(game));
		}
	}

	private void printHeadline() {

		String title = (this.game.getMode() == Select.VS_PLAYER)?"Player Vs Player":"Player vs CPU";
		this.game.getFont().draw(this.game.getBatch(),title , Main.WINDOW_WIDTH/2 - 50, Main.WINDOW_HEIGHT - 50);
	}

	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		InputHandler();

		game.getBatch().begin();
		game.getBatch().draw(gamefield, (Main.WINDOW_WIDTH / 2) - (this.gamefield.getWidth() / 2), 0, 400, 400);

		this.printHeadline();
		game.getBatch().end();
	}

	@Override
	public void dispose() {
		this.gamefield.dispose();
		for (int i = 0; i < field.length; i++) {
			this.field[i].dispose();
		}

	}
}