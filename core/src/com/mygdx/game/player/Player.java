package com.mygdx.game.player;

import com.mygdx.game.game.GameboardScreen.PlayerId;

public abstract class Player {

	PlayerId id;
	
	public Player(PlayerId id){
		
		this.id = id;
	}
	
}
