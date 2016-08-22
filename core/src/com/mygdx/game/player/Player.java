package com.mygdx.game.player;

import com.mygdx.game.game.GameBoardPoint.StoneSide;
import com.mygdx.game.game.GameboardScreen.PlayerId;

public abstract class Player {

	private StoneSide stoneSide;	
	
	public Player(StoneSide id){
		this.stoneSide = stoneSide;
	}
	
	public StoneSide getStoneSide(){
		return this.stoneSide;
	}
	
}
