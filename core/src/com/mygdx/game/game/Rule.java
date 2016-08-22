package com.mygdx.game.game;

import java.util.ArrayList;
import java.util.Arrays;
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

	private Gameboard gameboard;
	private Player currentPlayer;
	private final int FIELD_LENGHT = 7;
	private final int MILL = 3;
	
	public Rule(Gameboard gameboard, Player player){
		this.gameboard = gameboard;
		this.currentPlayer = player;
	}
	
	public Gameboard getGameboard() {
		return gameboard;
	}

	public void setGameboard(Gameboard gameboard) {
		this.gameboard = gameboard;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
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
	 * Sucht die Punkte, die sich in diesem Moment in einer Muehle befinden.
	 * @param point Ausgangspunkt von dem gesucht wird.
	 * @return {@link List} die die {@link GameBoardPoint}s enthaelt.
	 */
	public List<GameBoardPoint> isMillLower(GameBoardPoint point){
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		if( point.getNumber() % 2 == 0){
			gameBoardPointList.addAll(searchLowerHigher(point));
			gameBoardPointList.addAll(searchInnerOuter(point));
		}else{
			
		}
		return gameBoardPointList;
	}
	
	private List<GameBoardPoint> searchInnerOuter(GameBoardPoint point){
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		
		return gameBoardPointList;
	}
	
	/**
	 * 
	 * @param point
	 * @return
	 */
	private List<GameBoardPoint> searchLowerHigher(GameBoardPoint point){
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		if(pointsHaveSamePlayer(point.getLower(),point.getLower())){
			gameBoardPointList.add(point.getLower());
			gameBoardPointList.add(point.getHighter());
		}
		return gameBoardPointList;
	}
	
	private boolean pointsHaveSamePlayer(GameBoardPoint point,GameBoardPoint point2){
		return pointsHaveSamePlayer(point) && pointsHaveSamePlayer(point2);
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
