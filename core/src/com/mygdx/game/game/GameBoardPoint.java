package com.mygdx.game.game;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Diese Klasse repr�sentiert die stelle wo der Spielstein platziert werden
 * kann. Sie kann entweder ein Stein eines Spielers darstellen, oder aber eine
 * stelle, wo kein Stein liegt. Sie kann auch eine Stelle sein, wo kein
 * Spielstein liegt und auch keines liegen darf.
 * 
 * @author Ahmed, Jonathan
 *
 */
public class GameBoardPoint {

	/**
	 * PLAYER1 und PLAYER2 sind selbsterkl�rend. WITHOUT_STONE - Hier liegt kein
	 * Stein
	 *
	 */
	private GameBoardPoint inner;
	private GameBoardPoint highter;
	private GameBoardPoint lower;
	private GameBoardPoint outer;
	private int number;

	private StoneSide side;

	private Sprite tex;

	public GameBoardPoint(StoneSide s, int number) {
		this.number = number;
		this.setSide(s);
		
	}

	public GameBoardPoint getInner() {
		return inner;
	}

	public void setInner(GameBoardPoint inner) {
		this.inner = inner;
	}

	public GameBoardPoint getHighter() {
		return highter;
	}

	public void setHighter(GameBoardPoint highter) {
		this.highter = highter;
	}

	public GameBoardPoint getLower() {
		return lower;
	}

	public void setLower(GameBoardPoint lower) {
		this.lower = lower;
	}

	public GameBoardPoint getOuter() {
		return outer;
	}

	public void setOuter(GameBoardPoint outer) {
		this.outer = outer;
	}

	public enum StoneSide {
		PLAYER1, PLAYER2, WITHOUT_PLAYER;
	}

	public void setSide(StoneSide s) {
		this.side = s;
	}

	public StoneSide getSide() {
		return side;
	}

	public void dispose() {
		this.tex.getTexture().dispose();
	}

	public int getNumber() {
		return number;
	}
	
	@Override
	public String toString(){
		return Integer.toString(this.number);
	}
}
