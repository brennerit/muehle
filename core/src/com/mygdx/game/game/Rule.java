package com.mygdx.game.game;

import java.util.List;

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
		if(point.getSide() ==StoneSide.WITHOUT_PLAYER){
			setStonePossible = true;
		}else{
			setStonePossible = false;
		}
		return setStonePossible;
	}
	
	/**
	 * Prüfen ob eine Mühle vorhanden ist und die Nummer der Steine zurückgeben, die sich darin befinden.
	 */
	
	/**
	 * Prfueft von einem Punkt ausgehend, ob die Nachbarn
	 * @param point
	 * @return
	 */
	public List<GameBoardPoint> isMillLower(GameBoardPoint point){
		boolean millLower;
		
		return null;
	}
	
	/**
	 * 
	 * @param point
	 * @return
	 */
	private boolean pointsHaveSamePlayer(GameBoardPoint point){
		if(point == null) return false;
		return (point.getSide()==this.currentPlayer.getStoneSide())? true : false;
	}
		
}
