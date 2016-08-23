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

	private GameBoardLogic gameboard;
	private Player currentPlayer;
	private final int FIELD_LENGHT = 7;
	private final int MILL = 3;
	
	public Rule(GameBoardLogic gameboard, Player player){
		this.gameboard = gameboard;
		this.currentPlayer = player;
	}
	
	/**
	 * Gibt das {@link Gameboard} zurueck.
	 * @return Das {@link Gameboard} in seinem aktuellen Zustand.
	 */
	public GameBoardLogic getGameBoardLogic() {
		return gameboard;
	}
	
	/**
	 * Setzt ein neues {@link Gameboard}.
	 * @param gameboard Das neue {@link Gameboard}
	 */
	public void setGameBoardLogic(GameBoardLogic gameboard) {
		this.gameboard = gameboard;
	}
	/**
	 * 
	 * @return den aktuellen {@link Player}
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	/**
	 * 
	 * @param currentPlayer setzt einen neuen aktuellen {@link Player}
	 */
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
	 * @param point {@link GameBoardPoint} von dem aus gesucht wird.
	 * @return {@link List} die die {@link GameBoardPoint}s enthaelt, die zu der Muehle gehoeren.
	 */
	public List<GameBoardPoint> isMill(GameBoardPoint point){
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		if( point.getNumber() % 2 == 0){
			gameBoardPointList.addAll(searchLowerHigher(point));
			gameBoardPointList.addAll(searchInnerOuter(point));
			gameBoardPointList.add(point);
		}else{
			gameBoardPointList.addAll(searchLower(point));
			gameBoardPointList.addAll(searchHigher(point));
			gameBoardPointList.add(point);
		}
		return gameBoardPointList;
	}
	
	/**
	 * ToDo nicht fertig!
	 * Prueft ob die direkten Nachbarn Inner und Outer vom gleichen Spieler besetzt sind und gibt diese als Liste zurück.
	 * @param point {@link GameBoardPoint} der den Ausgangspunkt darstellt, von dem aus gesucht wird.
	 * @return {@link List} mit {@link GameBoardPoint} falls eine Muehle vorhanden ist, sonst eine leere {@link List}e
	 */
	private List<GameBoardPoint> searchInnerOuter(GameBoardPoint point){
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		
		return gameBoardPointList;
	}
	
	/**
	 * Prueft ob die 2 folgenden Punkte den gleichen Spieler haben und gibt diese, falls eine Muehle existiert zurueck.
	 * @param point {@link GameBoardPoint} der den Ausgangspunkt darstellt, von dem aus gesucht wird.
	 * @return {@link List} mit {@link GameBoardPoint} falls eine Muehle vorhanden ist, sonst eine leere {@link List}e
	 */
	private List<GameBoardPoint> searchLower(GameBoardPoint point){
		GameBoardPoint tmp = point.getLower();
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		int count = 0;
		while(pointsHaveSamePlayer(tmp,point) && count < 2){
			count++;
			gameBoardPointList.add(tmp);
			tmp = tmp.getLower();
		}
		if(count < 2){
			gameBoardPointList.clear();
		}
		return gameBoardPointList;
	}
	
	/**
	 * Prueft ob die 2 vorherigen Punkte den gleichen Spieler haben und gibt diese, falls eine Muehle existiert zurueck.
	 * @param point {@link GameBoardPoint} der den Ausgangspunkt darstellt, von dem aus gesucht wird.
	 * @return {@link List} mit {@link GameBoardPoint} falls eine Muehle vorhanden ist, sonst eine leere {@link List}e
	 */
	private List<GameBoardPoint> searchHigher(GameBoardPoint point){
		GameBoardPoint tmp = point.getHighter();
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		int count = 0;
		while(pointsHaveSamePlayer(tmp,point) && count < 2){
			count++;
			gameBoardPointList.add(tmp);
			tmp = tmp.getHighter();
		}
		if(count < 2){
			gameBoardPointList.clear();
		}
		return gameBoardPointList;
	}
	
	
	/**
	 * ToDo nicht fertig!
	 * Prueft ob die direkten Nachbarn Higher und Lower vom gleichen Spieler besetzt sind und gibt diese als Liste zurück.
	 * @param point {@link GameBoardPoint} der den Ausgangspunkt darstellt, von dem aus gesucht wird.
	 * @return {@link List} mit {@link GameBoardPoint} falls eine Muehle vorhanden ist, sonst eine leere {@link List}e
	 */
	private List<GameBoardPoint> searchLowerHigher(GameBoardPoint point){
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		
		return gameBoardPointList;
	}
	
	/**
	 * prueft ob die {@link GameBoardPoint}s dem aktuellen Spieler zugeteilt sind.
	 * @param point {@link GameBoardPoint}
	 * @param point2 {@link GameBoardPoint}
	 * @return true wenn sie dem Spieler zugeteilt sind, sonst false;
	 */
	private boolean pointsHaveSamePlayer(GameBoardPoint point,GameBoardPoint point2){
		return pointsHaveSamePlayer(point) && pointsHaveSamePlayer(point2);
	}
	
	/**
	 * prueft ob der {@link GameBoardPoint} dem aktuellen Spieler zugeteilt ist.
	 * @param point 
	 * @return true wenn sie dem Spieler zugeteilt sind, sonst false;
	 */
	private boolean pointsHaveSamePlayer(GameBoardPoint point){
		if(point == null) return false;
		return (point.getSide()==this.currentPlayer.getStoneSide())? true : false;
	}
		
}
