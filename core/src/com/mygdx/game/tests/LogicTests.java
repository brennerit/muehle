package com.mygdx.game.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.mygdx.game.game.GameBoardLogic;
import com.mygdx.game.game.GameBoardPoint;
import com.mygdx.game.game.Rule;
import com.mygdx.game.game.GameBoardPoint.StoneSide;

public class LogicTests {

	Rule rule;
	int [] positionsWithMill = { 0, 1, 2, 7, 6, 8, 14, 15, 23 };
	int [] positionsWithoutMill = { 3, 4, 10, 12, 19, 20 };
	int [] positionsWithoutPlayer = { 5 , 9 , 16 , 17 , 18 , 21 , 22 };
	int [] positionsWithPlayer = { 0 , 1 , 2 , 3 , 4 , 6 , 7 , 8 , 10 , 11 , 12 , 13 , 14 , 15 , 19, 20 , 23 };

	/**
	 * Initialisiert das Spielfeld für die Test. Bei den Spielpunkten
	 * 0,1,2,6,7,8,11,13,14,15 und 23 wird die {@link StoneSide} auf PLAYER1
	 * gesetzt. Die Spielpunkte 3,4,10,12,19 und 20 werden auf die
	 * {@link StoneSide} PLAYER2 gesetzt. Der rest bleibt auf {@link StoneSide}
	 * WITHOUT_PLAYER.
	 */
	@Before
	public void initialize() {
		rule = new Rule(new GameBoardLogic());
		rule.getGameBoardLogic().getgbpList().get(0).setSide(StoneSide.PLAYER1);
		rule.getGameBoardLogic().getgbpList().get(1).setSide(StoneSide.PLAYER1);
		rule.getGameBoardLogic().getgbpList().get(2).setSide(StoneSide.PLAYER1);
		rule.getGameBoardLogic().getgbpList().get(6).setSide(StoneSide.PLAYER1);
		rule.getGameBoardLogic().getgbpList().get(7).setSide(StoneSide.PLAYER1);
		rule.getGameBoardLogic().getgbpList().get(8).setSide(StoneSide.PLAYER1);
		rule.getGameBoardLogic().getgbpList().get(11).setSide(StoneSide.PLAYER1);
		rule.getGameBoardLogic().getgbpList().get(13).setSide(StoneSide.PLAYER1);
		rule.getGameBoardLogic().getgbpList().get(14).setSide(StoneSide.PLAYER1);
		rule.getGameBoardLogic().getgbpList().get(15).setSide(StoneSide.PLAYER1);
		rule.getGameBoardLogic().getgbpList().get(23).setSide(StoneSide.PLAYER1);

		rule.getGameBoardLogic().getgbpList().get(3).setSide(StoneSide.PLAYER2);
		rule.getGameBoardLogic().getgbpList().get(4).setSide(StoneSide.PLAYER2);
		rule.getGameBoardLogic().getgbpList().get(10).setSide(StoneSide.PLAYER2);
		rule.getGameBoardLogic().getgbpList().get(12).setSide(StoneSide.PLAYER2);
		rule.getGameBoardLogic().getgbpList().get(19).setSide(StoneSide.PLAYER2);
		rule.getGameBoardLogic().getgbpList().get(20).setSide(StoneSide.PLAYER2);

	}

	/**
	 * Prueft ob an den Punkten eine Muehle vorhanden ist oder nicht, also ob
	 * die Liste nicht leer/leer ist.
	 */
	@Test
	public void muehleVorhanden() {
		for (Integer num : positionsWithMill) {
			assertFalse(rule.isMill(rule.getGameBoardLogic().getgbpList().get(num)).isEmpty());
		}
		for (Integer num : positionsWithoutMill) {
			assertTrue(rule.isMill(rule.getGameBoardLogic().getgbpList().get(num)).isEmpty());
		}
	}

	@Test
	public void moveStone() {

	}

	/**
	 * prueft ob es moeglich ist die Steine an bestimmten Stellen zu löschen
	 * oder nicht.
	 */
	@Test
	public void deleteStone() {
		for (Integer num : positionsWithMill) {
			assertFalse(rule.deleteStone(rule.getGameBoardLogic().getgbpList().get(num)));
		}
		for (Integer num : positionsWithoutMill) {
			assertTrue(rule.deleteStone(rule.getGameBoardLogic().getgbpList().get(num)));
		}
	}

	@Test
	public void setStone() {
		for (Integer num : positionsWithPlayer) {
			assertFalse(rule.setStone(rule.getGameBoardLogic().getgbpList().get(num)));
		}
		for (Integer num : positionsWithoutPlayer) {
			assertTrue(rule.setStone(rule.getGameBoardLogic().getgbpList().get(num)));
		}
	}

}
