package com.mygdx.game.game;

import java.util.List;

import com.mygdx.game.game.GameboardPoint.StoneSide;

import java.util.ArrayList;

public class GameBoardJonbert {
	
	private List<GameboardPoint> points;
	private static final int LISTSIZE = 24;
	
	public GameBoardJonbert(){
		this.points = new ArrayList<>();
		for(int i = 0;i < LISTSIZE;i++){
			points.add(new GameboardPoint(StoneSide.WITHOUT_PLAYER));
		}
		setNeighbours();
	}
	
	private void setNeighbours(){
		for(int i = 0; i < LISTSIZE; i++){
			// maximal 2 Nachbarn
			if(i%2 == 0){
				
			}
			
		}
	}
}
