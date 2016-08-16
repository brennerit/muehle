package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Diese Klasse repräsentiert die stelle wo der Spielstein platziert werden kann. 
 * Sie kann entweder ein Stein eines Spielers darstellen, oder aber eine stelle, wo kein Stein liegt.
 * Sie kann auch eine Stelle sein, wo kein Spielstein liegt und auch keines liegen darf.
 * @author Ahmed
 *
 */
public class GameboardPoint {

	/**
	 * PLAYER1 und PLAYER2 sind selbsterklärend.
	 * WITHOUT_STONE 	- Hier liegt kein Stein
	 * MIDDLE 			- Hier liegt kein Stein und darf auch keines! (wichtig)
	 *
	 */
	public enum StoneSide {
		PLAYER1, PLAYER2, WITHOUT_PLAYER, MIDDLE;
	}

	StoneSide side;
	
	Sprite tex;
	
	public GameboardPoint( StoneSide s){
		
		
		this.setSide(s);
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
}
