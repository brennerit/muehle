package com.mygdx.game.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mygdx.game.game.GameBoardPoint.StoneSide;
import com.mygdx.game.player.Player;

/**
 * <p>
 * Diese Klasse repraesentiert die Regeln für das Muehlespiel. Es ist
 * ausschliesslich moeglich zu pruefen, ob die Regeln eingehalten werden. Die
 * Klasse nimmt keine Aenderungen an dem Spielfeld vor.
 * </p>
 * <p>
 * Die Regen koennen folgende Optionen pruefen:
 * <ol>
 * <li>ob es moeglich ist eine Stein zu setzen/bewegen</li>
 * <li>welcher Spieler gewonnen hat</li>
 * <li>ob das Spiel unentschieden ist</li>
 * <li>ob an einer best. Stelle des Spielfeldes eine Muehle vorhanden ist</li>
 * </ol>
 * </p>
 * 
 * @author Jonathan
 *
 */
public class Rule {

	private GameBoardLogic gameBoardLogic;

	private int stonesPlayer1;
	private int stonesPlayer2;

	private final int FIELD_LENGHT = 7;
	private final int MILL = 3;

	public Rule(GameBoardLogic gameboard) {
		this.gameBoardLogic = gameboard;
		calculateStonesOnBoard();
	}

	/**
	 * Gibt das {@link Gameboard} zurueck.
	 * 
	 * @return Das {@link Gameboard} in seinem aktuellen Zustand.
	 */
	public GameBoardLogic getGameBoardLogic() {
		return gameBoardLogic;
	}

	/**
	 * Setzt ein neues {@link Gameboard} und die berechnet die Anzahl der
	 * Spielstein für jeden Spieler, die sich auf dem Spielbrett befinden.
	 * 
	 * @param gameboard
	 *            Das neue {@link Gameboard}
	 */
	public void setGameBoardLogic(GameBoardLogic gameboard) {
		this.gameBoardLogic = gameboard;
		calculateStonesOnBoard();
	}

	/**
	 * Prueft ob es moeglich ist einen Stein an diesem Punkt des Spielfeldes zu
	 * setzen.
	 * 
	 * @return true wenn der Stein gesetzt werden kann. false wenn der Stein
	 *         nicht gesetzt werden kann.
	 */
	public boolean setStonePossible(GameBoardPoint point) {
		boolean setStonePossible;
		if (point.getSide() == StoneSide.WITHOUT_PLAYER) {
			setStonePossible = true;
			calculateStonesOnBoard();
		} else {
			setStonePossible = false;
		}
		return setStonePossible;
	}

	/**
	 * /** Berechnet die moeglichen Positionen an die ein {@link GameBoardPoint}
	 * gesetzt werden kann.
	 * @param point 
	 * @return possiblePositions eine {@link List}e, die die Positionen enthaelt an die der Stein
	 *         gesetzt werden kann.
	 */

	public List<GameBoardPoint> getPossibleStonePositions(GameBoardPoint point) {
		List<GameBoardPoint> possiblePositions = new ArrayList<>();
		if(point.getHighter() != null && point.getHighter().getSide() == StoneSide.WITHOUT_PLAYER)
			possiblePositions.add(point.getHighter());
		if(point.getInner() != null && point.getInner().getSide() == StoneSide.WITHOUT_PLAYER)
			possiblePositions.add(point.getInner());
		if(point.getLower() != null && point.getLower().getSide() == StoneSide.WITHOUT_PLAYER) 
			possiblePositions.add(point.getLower());
		if(point.getOuter() != null && point.getOuter().getSide() == StoneSide.WITHOUT_PLAYER)  
			possiblePositions.add(point.getOuter());
		return possiblePositions;
	}

	/**
	 * Gibt die {@link StoneSide} des Gewinners zurück.
	 * @return die {@link StoneSide} des Gewinners.
	 */
	public StoneSide won() {
		StoneSide winner = null;
		if (this.stonesPlayer1 < 3) {
			winner = StoneSide.PLAYER2;
		} else if (this.stonesPlayer2 < 3) {
			winner = StoneSide.PLAYER1;
		} else {
			winner = movePlayerPossible(StoneSide.PLAYER1) ? StoneSide.PLAYER2 : null;
			winner = movePlayerPossible(StoneSide.PLAYER2) ? StoneSide.PLAYER1 : null;
		}
		return winner;
	}

	/**
	 * ToDo noch nicht implementiert - gibt nur false zurueeck - Prueft ob das
	 * Spiel unentschieden ist.
	 * 
	 * @return true falls ja, sonst false.
	 */
	public boolean remi() {
		/**
		 * unentschieden, wenn: - beide Spieler zustimmen - innerhalb 50 Zuegen
		 * keine Muehle geschlossen wird - 3 mal die selbe Stellung erreicht
		 * wird
		 */
		return false;
	}

	/**
	 * Prueft ob es moeglich ist den Stein zu entfernen. Wenn der Stein entfernt
	 * werden kann wird in den Regeln die Anzahl der Spielstein für den
	 * zugehoerigen Spieler reduziert.
	 * 
	 * @param point
	 *            {@link GameBoardPoint} der entfernt werden soll.
	 * @return true wenn er entfernt werden kann, sonst false.
	 */
	public boolean removeStonePossible(GameBoardPoint point) {
		boolean removeIsPossible = (isMill(point).isEmpty()) ? true : false;
		if (removeIsPossible) {
			decreasePlayerStoneNumber(point);
		}
		return removeIsPossible;
	}

	public int getStonesPlayer1() {
		return stonesPlayer1;
	}

	public int getStonesPlayer2() {
		return stonesPlayer2;
	}

	/**
	 * Sucht die Punkte, die sich in diesem Moment in einer Muehle befinden.
	 * 
	 * @param point
	 *            {@link GameBoardPoint} von dem aus gesucht wird.
	 * @return {@link List} die die {@link GameBoardPoint}s enthaelt, die zu der
	 *         Muehle gehoeren.
	 */
	public List<GameBoardPoint> isMill(GameBoardPoint point) {
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		if (point.getNumber() % 2 != 0) {
			gameBoardPointList.addAll(searchLowerHigher(point));
			gameBoardPointList.addAll(searchInnerOuter(point));
			gameBoardPointList.add(point);
		} else {
			gameBoardPointList.addAll(searchLower(point));
			gameBoardPointList.addAll(searchHigher(point));
			gameBoardPointList.add(point);
		}
		if (gameBoardPointList.size() < 2)
			gameBoardPointList.clear();
		return gameBoardPointList;
	}

	/**
	 * Prueft ob die direkten Nachbarn Inner und Outer vom gleichen Spieler
	 * besetzt sind und gibt diese als Liste zurück.
	 * 
	 * @param point
	 *            {@link GameBoardPoint} der den Ausgangspunkt darstellt, von
	 *            dem aus gesucht wird.
	 * @return {@link List} mit {@link GameBoardPoint} falls eine Muehle
	 *         vorhanden ist, sonst eine leere {@link List}e
	 */
	private List<GameBoardPoint> searchInnerOuter(GameBoardPoint point) {
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		GameBoardPoint inner = point.getInner();
		GameBoardPoint outer = point.getOuter();
		while (inner != null) {
			if (pointsHaveSamePlayer(point, inner)) {
				gameBoardPointList.add(inner);
			}
			inner = inner.getInner();
		}
		while (outer != null) {
			if (pointsHaveSamePlayer(point, outer)) {
				gameBoardPointList.add(outer);
			}
			outer = outer.getOuter();
		}
		if (gameBoardPointList.size() < 2)
			gameBoardPointList.clear();
		return gameBoardPointList;
	}

	/**
	 * Prueft ob die 2 folgenden Punkte den gleichen Spieler haben und gibt
	 * diese, falls eine Muehle existiert zurueck.
	 * 
	 * @param point
	 *            {@link GameBoardPoint} der den Ausgangspunkt darstellt, von
	 *            dem aus gesucht wird.
	 * @return {@link List} mit {@link GameBoardPoint} falls eine Muehle
	 *         vorhanden ist, sonst eine leere {@link List}e
	 */
	private List<GameBoardPoint> searchLower(GameBoardPoint point) {
		GameBoardPoint lower = point.getLower();
		GameBoardPoint lowerLower = lower.getLower();
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		if (pointsHaveSamePlayer(point, lowerLower) && pointsHaveSamePlayer(lower, point)) {
			gameBoardPointList.add(lower);
			gameBoardPointList.add(lowerLower);
		}
		return gameBoardPointList;
	}

	/**
	 * Prueft ob die 2 vorherigen Punkte den gleichen Spieler haben und gibt
	 * diese, falls eine Muehle existiert zurueck.
	 * 
	 * @param point
	 *            {@link GameBoardPoint} der den Ausgangspunkt darstellt, von
	 *            dem aus gesucht wird.
	 * @return {@link List} mit {@link GameBoardPoint} falls eine Muehle
	 *         vorhanden ist, sonst eine leere {@link List}e
	 */
	private List<GameBoardPoint> searchHigher(GameBoardPoint point) {
		GameBoardPoint higher = point.getHighter();
		GameBoardPoint higherHigher = higher.getHighter();
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		if (pointsHaveSamePlayer(point, higherHigher) && pointsHaveSamePlayer(point, higher)) {
			gameBoardPointList.add(higher);
			gameBoardPointList.add(higherHigher);
		}
		return gameBoardPointList;
	}

	/**
	 * ToDo nicht fertig! Prueft ob die direkten Nachbarn Higher und Lower vom
	 * gleichen Spieler besetzt sind und gibt diese als Liste zurück.
	 * 
	 * @param point
	 *            {@link GameBoardPoint} der den Ausgangspunkt darstellt, von
	 *            dem aus gesucht wird.
	 * @return {@link List} mit {@link GameBoardPoint} falls eine Muehle
	 *         vorhanden ist, sonst eine leere {@link List}e
	 */
	private List<GameBoardPoint> searchLowerHigher(GameBoardPoint point) {
		List<GameBoardPoint> gameBoardPointList = new ArrayList<>();
		GameBoardPoint lower = point.getLower();
		GameBoardPoint higher = point.getHighter();
		if (pointsHaveSamePlayer(point, higher) && pointsHaveSamePlayer(lower, point)) {
			gameBoardPointList.add(higher);
			gameBoardPointList.add(lower);
		}
		return gameBoardPointList;
	}

	/**
	 * prueft ob die {@link GameBoardPoint}s die gleiche {@link StoneSide}
	 * haben.
	 * 
	 * @param point
	 *            {@link GameBoardPoint}
	 * @param point2
	 *            {@link GameBoardPoint}
	 * @return true wenn sie die gleiche Seite haben, sonst false;
	 */
	private boolean pointsHaveSamePlayer(GameBoardPoint point, GameBoardPoint point2) {
		return (point.getSide() == point2.getSide());
	}

	/**
	 * Verringert die Anzahl der Spielsteine eines Spielers.
	 * 
	 * @param point
	 *            {@link GameBoardPoint} der entfernt wurde.
	 */
	private void decreasePlayerStoneNumber(GameBoardPoint point) {
		if (point.getSide() == StoneSide.PLAYER1) {
			stonesPlayer1--;
		} else {
			stonesPlayer2--;
		}
	}

	/**
	 * Setzt die Anzahl der Spielsteine der Spieler auf 0 und berechnet dann neu
	 * wie viele sich noch auf dem Spielfeld befinden. Diese Methode muss
	 * aufgerufen werden, wenn die Regeln neu erzeugt wird, die Gamelogik neu
	 * gesetzt wird , ein Stein dem Spielfeld hinzugefügt wird oder ein Stein
	 * von dem Spielfeld entfernt wird.
	 */
	private void calculateStonesOnBoard() {
		this.stonesPlayer1 = 0;
		this.stonesPlayer2 = 0;
		for (GameBoardPoint point : this.gameBoardLogic.getgbpList()) {
			StoneSide side = point.getSide();
			if (side == StoneSide.PLAYER1) {
				this.stonesPlayer1++;
			} else if (side == StoneSide.PLAYER2) {
				this.stonesPlayer2++;
			}
		}
	}

	
	/**
	 * Prueft ob der Spieler 2 seine Spielsteine noch bewegen kann.
	 * @return false wenn der Spieler seine Steine nicht bewegen kann oder weniger als 3 Spielsteine auf dem Spielfeld
	 * hat, sonst true.
	 */
	private boolean movePlayerPossible(StoneSide side) {
		boolean isPossible = false;
		if(side == StoneSide.PLAYER1 && this.stonesPlayer1 < 3){
			isPossible = false;
		}else if(side == StoneSide.PLAYER2 && this.stonesPlayer2 < 3) {
			isPossible = false;
		} else {
			for (GameBoardPoint point : this.gameBoardLogic.getgbpList()) {
				if(point.getSide() == side && getPossibleStonePositions(point).size() > 0){
					isPossible = true;
				}
			}
		}
		return isPossible;
	}

}
