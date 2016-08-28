package com.mygdx.game.player;

import com.mygdx.game.game.GameBoardPoint.StoneSide;

/**
 * Diese Klasse repraentiert einen Spieler
 * @author jonathan
 *
 */
public abstract class Player {

	private StoneSide stoneSide;	
	
	public Player(StoneSide id){
		this.stoneSide = id;
	}
	
	public StoneSide getStoneSide(){
		return this.stoneSide;
	}
	
}
