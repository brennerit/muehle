package com.mygdx.game.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.game.GameBoardPoint.StoneSide;

/**
 * Diese Klasse Repr�sentiert das Spielfeld. Das Spielfeld besteht aus
 * Spielsteine und ist 9 * 9 gro� Dabei sind die viele Felder NULL, da sie nicht
 * gebraucht werden.
 * 
 * @author Ahmed
 *
 **/
public class Gameboard {

	private List<GameBoardPoint> gbpList;

	private final int MAX_GAMEBOARDPOINT = 24;

	private Texture gamefieldTex;

	// private GameboardPointConnecter connecter;

	private Rule rule;

	public Gameboard() {

		this.gbpList = new ArrayList<GameBoardPoint>();

		this.gamefieldTex = new Texture("muehle_board.png");

		this.initField();

		this.rule = new Rule();

		this.printField();

		// this.connecter = new GameboardPointConnecter(0, 0);
	}

	/**
	 * Mit dieser Methode erh�lt man die Texture vom Spielfeld
	 * 
	 * @return
	 */
	public Texture getGamefieldTexture() {
		return this.gamefieldTex;
	}

	public Rule getRule() {
		return this.rule;
	}

	public void update() {

	}

	/**
	 * Diese Methode Initialisiert das Spielfeld Array, bedinnend von innen nach
	 * au�en.
	 * 
	 */
	private void initField() {

		for (int gameboardpointnumber = 0; gameboardpointnumber < this.MAX_GAMEBOARDPOINT; gameboardpointnumber++) {
			
			GameBoardPoint tmp = new GameBoardPoint(StoneSide.WITHOUT_PLAYER);
			
			this.initNeighbours(tmp);
			
			this.gbpList.add(tmp);
			
		}

	}
	
	
	/**
	 * Sucht für das Übergebene GameboardPoint Objekt alle verfügbaren Nachbarn auf dem Spielfeld
	 * @param gbp Das GameboardPoint Objekt, dass ein Punkt auf dem Spielfeld repräsentiert
	 */
	private void initNeighbours(GameBoardPoint gbp ){		
		
		gbp.setHighter(this.gbpList.get(gbp.getNumber() + 1));
		gbp.setLower(this.gbpList.get(gbp.getNumber() - 1));
		
		if(gbp.getNumber() % 2 != 0){
			gbp.setInner(this.gbpList.get(gbp.getNumber() - 1));
				
		}
		
	}
	
	

	private void printField() {
		// TODO Auto-generated method stub

	}

	public void dispose() {

	}

}
