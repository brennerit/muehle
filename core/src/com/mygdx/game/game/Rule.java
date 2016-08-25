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

	private int StonesPlayer1;
	private int StonesPlayer2;
	
	private final int FIELD_LENGHT = 7;
	private final int MILL = 3;
	
	public Rule(GameBoardLogic gameboard){
		this.gameboard = gameboard;
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
		if( point.getNumber() % 2 != 0){
			gameBoardPointList.addAll(searchLowerHigher(point));
			gameBoardPointList.addAll(searchInnerOuter(point));
			gameBoardPointList.add(point);
		}else{
			gameBoardPointList.addAll(searchLower(point));
			gameBoardPointList.addAll(searchHigher(point));
			gameBoardPointList.add(point);
		}
		if(gameBoardPointList.size() < 2) gameBoardPointList.clear();
		return gameBoardPointList;
	}
	
	/**
	 * Prueft ob die direkten Nachbarn Inner und Outer vom gleichen Spieler besetzt sind und gibt diese als Liste zurück.
	 * @param point {@link GameBoardPoint} der den Ausgangspunkt darstellt, von dem aus gesucht wird.
	 * @return {@link List} mit {@link GameBoardPoint} falls eine Muehle vorhanden ist, sonst eine leere {@link List}e
	 */
	private List<GameBoardPoint> searchInnerOuter(GameBoardPoint point){
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		GameBoardPoint inner = point.getInner();
		GameBoardPoint outer = point.getOuter();
		while(inner!=null){
			if(pointsHaveSamePlayer(point,inner)){
				gameBoardPointList.add(inner);
			}
			inner = inner.getInner();
		}
		while(outer!=null){
			if(pointsHaveSamePlayer(point,outer)){
				gameBoardPointList.add(outer);
			}
			outer = outer.getOuter();
		}
		if(gameBoardPointList.size()< 2) gameBoardPointList.clear();
		return gameBoardPointList;
	}
	
	/**
	 * Prueft ob die 2 folgenden Punkte den gleichen Spieler haben und gibt diese, falls eine Muehle existiert zurueck.
	 * @param point {@link GameBoardPoint} der den Ausgangspunkt darstellt, von dem aus gesucht wird.
	 * @return {@link List} mit {@link GameBoardPoint} falls eine Muehle vorhanden ist, sonst eine leere {@link List}e
	 */
	private List<GameBoardPoint> searchLower(GameBoardPoint point){
		GameBoardPoint lower = point.getLower();
		GameBoardPoint lowerLower = lower.getLower();
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		if(pointsHaveSamePlayer(point, lowerLower)&& pointsHaveSamePlayer(lower, point)){
			gameBoardPointList.add(lower);
			gameBoardPointList.add(lowerLower);
		}
		return gameBoardPointList;
	}
	
	/**
	 * Prueft ob die 2 vorherigen Punkte den gleichen Spieler haben und gibt diese, falls eine Muehle existiert zurueck.
	 * @param point {@link GameBoardPoint} der den Ausgangspunkt darstellt, von dem aus gesucht wird.
	 * @return {@link List} mit {@link GameBoardPoint} falls eine Muehle vorhanden ist, sonst eine leere {@link List}e
	 */
	private List<GameBoardPoint> searchHigher(GameBoardPoint point){
		GameBoardPoint higher = point.getHighter();
		GameBoardPoint higherHigher = higher.getHighter();
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		if(pointsHaveSamePlayer(point, higherHigher)&& pointsHaveSamePlayer(point, higher)){
			gameBoardPointList.add(higher);
			gameBoardPointList.add(higherHigher);
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
		GameBoardPoint lower = point.getLower();
		GameBoardPoint higher = point.getHighter();
		if(pointsHaveSamePlayer(point, higher)&&pointsHaveSamePlayer(lower, point)){
			gameBoardPointList.add(higher);
			gameBoardPointList.add(lower);
		}
		return gameBoardPointList;
	}
	
	/**
	 * prueft ob die {@link GameBoardPoint}s die gleiche {@link StoneSide} haben.
	 * @param point {@link GameBoardPoint}
	 * @param point2 {@link GameBoardPoint}
	 * @return true wenn sie die gleiche Seite haben, sonst false;
	 */
	private boolean pointsHaveSamePlayer(GameBoardPoint point,GameBoardPoint point2){
		return (point.getSide() == point2.getSide());
	}
	
	/**
	 * Gibt den Spieler zurueck der gewonnen hat.
	 * @return den {@link Player} der gewonnen hat.
	 */
	public Player won(){
		int player1 = 0;
		int player2 = 0;
		for(GameBoardPoint point: this.gameboard.getgbpList()){
			if(point.getSide()==StoneSide.PLAYER1){
				player1++;
			}
			if(point.getSide()==StoneSide.PLAYER2){
				player2++;
			}
		}
		return null;
	}
	
	/**
	 * Prueft ob das Spiel unentschieden ist.
	 * @return true falls ja, sonst false.
	 */
	public boolean remi(){
		return false;
	}
		
}
