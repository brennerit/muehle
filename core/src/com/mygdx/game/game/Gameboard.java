package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.game.GameboardPoint.StoneSide;

/**
 * Diese Klasse Repr‰sentiert das Spielfeld. Das Spielfeld besteht aus
 * Spielsteine und ist 9 * 9 groﬂ Dabei sind die viele Felder NULL, da sie nicht
 * gebraucht werden.
 * 
 * @author Ahmed
 *
 **/
public class Gameboard {

	private GameboardPoint[] field;

	private final int FIELD_LENGHT = 7;

	private Texture gamefield;

	Rule rule;
	public Gameboard() {

		this.field = new GameboardPoint[FIELD_LENGHT * FIELD_LENGHT];

		this.gamefield = new Texture("muehle_board.png");

		this.initField();

		this.rule = new Rule();
				
		this.printField();
	}

	public Texture getGamefield() {		
		return this.gamefield;
	}

	/**
	 * Diese Methode Initialisiert das Spielfeld Array, bedinnend von innen nach auﬂen.
	 * 
	 */
	private void initField() {
		int decency = 1;
		int posX = 2;
		int posY = 2;

		for (int loop = 0; loop < 3; loop++) {
			for (int height = 0; height < 3; height++) {
				for (int width = 0; width < 3; width++) {

					setFieldElementToEmpty(posX, posY);

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

	/**
	 * Setzt die elemente im array auf ein Leeres Feld vom Typ Select.WITHOUT_PLAYER
	 * 
	 * @param x
	 * @param y
	 */
	private void setFieldElementToEmpty(int x, int y) {
		this.field[(y * this.FIELD_LENGHT) + x] = new GameboardPoint(GameboardPoint.StoneSide.WITHOUT_PLAYER);
	}

	/**
	 * Gibt den GameboardPoint anhand der ¸bergebenen Koordinaten zur¸ck.
	 * @param x
	 * @param y
	 * @return
	 */
	private GameboardPoint getFieldElement(int x, int y) {

		return this.field[x + y * this.FIELD_LENGHT];
	}

	/**
	 * Gibt das Array auf der Konsole aus in einem Schachbrett Muster
	 */
	private void printField() {

		for (int height = 0; height < FIELD_LENGHT; height++) {
			for (int width = 0; width < FIELD_LENGHT; width++) {
				GameboardPoint stein = this.getFieldElement(width, height);
				if (stein == null) {
					System.out.print("O ");
				} else if (stein.getSide() == GameboardPoint.StoneSide.PLAYER1) {
					System.out.print("1 ");
				} else if (stein.getSide() == GameboardPoint.StoneSide.PLAYER2) {
					System.out.print("2 ");
				} else if (stein.getSide() == GameboardPoint.StoneSide.WITHOUT_PLAYER) {
					System.out.print("X ");
				} else {
					System.out.print("H ");
				}

			}
			System.out.println();
		}
	}

	public void render(float delta) {

		// TODO
	}

	public void dispose() {
		this.gamefield.dispose();
		for (int i = 0; i < field.length; i++) {
			this.field[i].dispose();
		}
	}

}
