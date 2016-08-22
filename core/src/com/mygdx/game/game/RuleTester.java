package com.mygdx.game.game;

import com.mygdx.game.game.GameBoardPoint.StoneSide;

public class RuleTester {
	
	public static void main(String [] args){
		RuleTester ruletestBoard = new RuleTester();
		System.out.println("<----START---->");
		ruletestBoard.printField();
		System.out.println("<------------->");
		ruletestBoard.setStoneSideAtPosition(new GameBoardPoint(StoneSide.PLAYER1), 2, 3);
		ruletestBoard.printField();
		System.out.println("<------------->");
		ruletestBoard.setStoneSideAtPosition(new GameBoardPoint(StoneSide.PLAYER1), 2, 1);
		ruletestBoard.printField();
		System.out.println("<-----ENDE---->");
	}
	
	/**
	 * Testklasse zum testen der Regeln für das Spielfeld.
	 */
	
	
	private GameBoardPoint[] field;
	private final int FIELD_LENGTH = 7;
	private Rule rule;
	
	public RuleTester(){
		this.field = new GameBoardPoint[FIELD_LENGTH * FIELD_LENGTH];
		this.initField();
		this.rule = new Rule();
		rule.setGameboard(this.field);
	}
	
	public GameBoardPoint[] getField(){
		return this.field;
	}
	
	public void setField(GameBoardPoint[] field){
		this.field = field;
	}
	
	/**
	 * Diese Methode Initialisiert das Spielfeld Array, bedinnend von innen nach au�en.
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
		getFieldElement(3, 3).setSide(StoneSide.MIDDLE);

	}

	/**
	 * Setzt die elemente im array auf ein Leeres Feld vom Typ Select.WITHOUT_PLAYER
	 * 
	 * @param x
	 * @param y
	 */
	private void setFieldElementToEmpty(int x, int y) {
		field[(y * this.FIELD_LENGTH) + x] = new GameBoardPoint(GameBoardPoint.StoneSide.WITHOUT_PLAYER);
	}
	
	/**
	 * Gibt den GameboardPoint anhand der �bergebenen Koordinaten zur�ck.
	 * @param x
	 * @param y
	 * @return
	 */
	private GameBoardPoint getFieldElement(int x, int y) {
		return this.field[x + y * this.FIELD_LENGTH];
	}
	
	/**
	 * Gibt das Array auf der Konsole aus in einem Schachbrett Muster
	 */
	private void printField() {

		for (int height = 0; height < FIELD_LENGTH; height++) {
			for (int width = 0; width < FIELD_LENGTH; width++) {
				GameBoardPoint stein = this.getFieldElement(width, height);
				if (stein == null) {
					System.out.print("O ");
				} else if (stein.getSide() == GameBoardPoint.StoneSide.PLAYER1) {
					System.out.print("1 ");
				} else if (stein.getSide() == GameBoardPoint.StoneSide.PLAYER2) {
					System.out.print("2 ");
				} else if (stein.getSide() == GameBoardPoint.StoneSide.WITHOUT_PLAYER) {
					System.out.print("X ");
				} else {
					System.out.print("H ");
				}

			}
			System.out.println();
		}
	}
	
	public void setStoneSideAtPosition(GameBoardPoint point, int x, int y){
		if(rule.setStonePossible(x, y)){
			setFieldElement(point,x,y);
		}
		
	}
	
	private void setFieldElement(GameBoardPoint point,int x, int y){
		this.field[x + (y * this.FIELD_LENGTH)] = point;
	}
}
