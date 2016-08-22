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
public class GameBoardPoint {

	/**
	 * PLAYER1 und PLAYER2 sind selbsterkl�rend.
	 * WITHOUT_STONE 	- Hier liegt kein Stein
	 *
	 */
	private GameBoardPoint neighboursBottom;
	private GameBoardPoint neighboursRight;
	private GameBoardPoint neighboursLeft;
	private GameBoardPoint neighboursUp;
	private int number;
	private static int counter = 0;
	
	public enum StoneSide {
		PLAYER1, PLAYER2, WITHOUT_PLAYER;
	}

	StoneSide side;
	
	Sprite tex;
	
	public GameBoardPoint( StoneSide s){
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
	
	public void setNeighboursBottomSide(GameBoardPoint neighboursBottom) {
		this.neighboursBottom = neighboursBottom;
	}

	public void setNeighboursRightSide(GameBoardPoint neighboursRight) {
		this.neighboursRight = neighboursRight;
	}

	public void setNeighboursLeftSide(GameBoardPoint neighboursLeft) {
		this.neighboursLeft = neighboursLeft;
	}

	public void setNeighboursUpSide(GameBoardPoint neighboursDown) {
		this.neighboursUp = neighboursDown;
	}

	public GameBoardPoint getNeighboursBottom() {
		return neighboursBottom;
	}

	public GameBoardPoint getNeighboursRight() {
		return neighboursRight;
	}

	public GameBoardPoint getNeighboursLeft() {
		return neighboursLeft;
	}

	public GameBoardPoint getNeighboursUp() {
		return neighboursUp;
	}

	public int getNumber() {
		return number;
	}

	
}
