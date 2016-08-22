package com.mygdx.game.game;

import com.mygdx.game.game.GameBoardPoint.StoneSide;
import com.mygdx.game.player.Player;

/**
 * Diese Klasse repraesentiert die Regeln für das Muehlespiel. Es ist ausschliesslich moeglich zu pruefen, ob die Regeln
 * eingehalten werden. Die Klasse nimmt keine Aenderungen an dem Spielfeld vor.
 * @author Jonathan
 *
 */
public class Rule {

	private GameBoardJonbert gameboard;
	private StoneSide stoneSide;
	private Player currentPlayer;
	private final int FIELD_LENGHT = 7;
	private final int MILL = 3;
	
	public Rule(){
		
	}

	public GameBoardJonbert getGameboard(GameBoardJonbert gameboard, Player player) {
		this.gameboard = gameboard;
		this.currentPlayer = player;
		
		return this.gameboard;
	}
	
	/**
	 * Prueft ob es moeglich ist einen Stein an diesem Punkt des Spielfeldes zu setzen.
	 * @return true wenn der Stein gesetzt werden kann.
	 *		   false wenn der Stein nicht gesetzt werden kann.
	 */
	public boolean setStonePossible(GameBoardPoint point){
		boolean setStonePossible;
		if(point != null
				&& point.getSide() ==StoneSide.WITHOUT_PLAYER){
			setStonePossible = true;
		}else{
			setStonePossible = false;
		}
		return setStonePossible;
	}
	
	/**
	 * Prüft ob es moeglich ist den Stein auf den {@link GameBoardPoint} zu setzen.
	 * @param point Der Ziel-{@link GameBoardPoint}
	 * @return true falls moeglich, sonst false
	 */
	public boolean moveStonePossible(GameBoardPoint point, Player player){
		boolean movePossible = false;
		if(this.currentPlayer.equals(player) ){
			if(point.getSide()!=null && 
					point.getSide() == StoneSide.WITHOUT_PLAYER){
				movePossible = true;
			}
		}
		return movePossible;
	}
	
	public boolean isMillXAxis(GameBoardPoint point){
		boolean millXAxis = false;
		int mill = 0;
		while(point.getNeighboursLeft()!=null){
			
		}
		while(point.getNeighboursRight() != null){
			
		}
		return millXAxis;
	}
		
	public boolean isMillYAxis(GameBoardPoint point){
		boolean millYAxis = false;
		
		return millYAxis;
	}
	
}
