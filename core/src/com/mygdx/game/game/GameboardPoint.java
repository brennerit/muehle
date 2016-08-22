package com.mygdx.game.game;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Diese Klasse repr�sentiert die stelle wo der Spielstein platziert werden kann. 
 * Sie kann entweder ein Stein eines Spielers darstellen, oder aber eine stelle, wo kein Stein liegt.
 * Sie kann auch eine Stelle sein, wo kein Spielstein liegt und auch keines liegen darf.
 * @author Ahmed, Jonathan
 *
 */
public class GameboardPoint {

	/**
	 * PLAYER1 und PLAYER2 sind selbsterkl�rend.
	 * WITHOUT_STONE 	- Hier liegt kein Stein
	 * MIDDLE 			- Hier liegt kein Stein und darf auch keines! (wichtig)
	 *
	 */
	private GameboardPoint neighboursBottomSide;
	private GameboardPoint neighboursRightSide;
	private GameboardPoint neighboursLeftSide;
	private GameboardPoint neighboursUpSide;
	private int number;
	private static int counter = 0;
	
	public enum StoneSide {
		PLAYER1, PLAYER2, WITHOUT_PLAYER, MIDDLE;
	}

	StoneSide side;
	
	Sprite tex;
	
	public GameboardPoint( StoneSide s){
		this.number = counter;
		counter++;
		this.setSide(s);
		String side;
	}
	
	public void setSide(StoneSide s){
		this.side= s;
	}
	public StoneSide getSide(){
		return side;
	}
	public void dispose(){
		this.tex.getTexture().dispose();	
	}
	
	public void setNeighboursBottomSide(GameboardPoint neighboursBottomSide) {
		this.neighboursBottomSide = neighboursBottomSide;
	}

	public void setNeighboursRightSide(GameboardPoint neighboursRightSide) {
		this.neighboursRightSide = neighboursRightSide;
	}

	public void setNeighboursLeftSide(GameboardPoint neighboursLeftSide) {
		this.neighboursLeftSide = neighboursLeftSide;
	}

	public void setNeighboursUpSide(GameboardPoint neighboursDownSide) {
		this.neighboursUpSide = neighboursDownSide;
	}

	public GameboardPoint getNeighboursBottomSide() {
		return neighboursBottomSide;
	}

	public GameboardPoint getNeighboursRightSide() {
		return neighboursRightSide;
	}

	public GameboardPoint getNeighboursLeftSide() {
		return neighboursLeftSide;
	}

	public GameboardPoint getNeighboursUpSide() {
		return neighboursUpSide;
	}

	public int getNumber() {
		return number;
	}

	
}
