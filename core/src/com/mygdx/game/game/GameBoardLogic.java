package com.mygdx.game.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mygdx.game.game.GameBoardPoint.StoneSide;
import com.mygdx.game.observer.Event.Event_Message;
import com.mygdx.game.observer.Observer;
import com.mygdx.game.observer.Subject;

/**
 * Die Logik vom Spielfeld.
 * 
 * @author ahmed
 *
 */
public class GameBoardLogic extends Subject implements Observer {

	private List<GameBoardPoint> gbpList;

	private final int MAX_GAMEBOARDPOINT = 24;

	private Event_Message action;

	public GameBoardLogic() {

		this.gbpList = new ArrayList<GameBoardPoint>();

		this.initField();

		this.registry(this);
	}

	/**
	 * Diese Methode Initialisiert das Spielfeld , bedinnend von innen nach
	 * au�en.
	 * 
	 */
	private void initField() {

		for (int gameboardpointnumber = 0; gameboardpointnumber < this.MAX_GAMEBOARDPOINT; gameboardpointnumber++) {

			GameBoardPoint tmp = new GameBoardPoint(StoneSide.WITHOUT_PLAYER, gameboardpointnumber);

			this.gbpList.add(tmp);

			this.initNeighbours(tmp);

		}

	}

	/**
	 * Sucht für das Übergebene GameboardPoint Objekt alle verfügbaren Nachbarn
	 * auf dem Spielfeld
	 * 
	 * @param gbp
	 *            Das GameboardPoint Objekt, dass ein Punkt auf dem Spielfeld
	 *            repräsentiert
	 */
	private void initNeighbours(GameBoardPoint gbp) {
		gbp.setLower(this.searchLowerNeighbours(gbp));
		gbp.setHighter(this.searchHighterNeighbours(gbp));
		gbp.setInner(this.searchInnerNeighbours(gbp));
		gbp.setOuter(this.searchOuterNeighbours(gbp));

	}

	/**
	 * Sucht für den übergebenen GameboardPoint den passenden Inneren Nachbarn.
	 * Dabei wird beim Nachbarn die Verbindung zum Übergebenen GameboardPoint
	 * gesichert.
	 * 
	 * @param gbp
	 * @return
	 */
	private GameBoardPoint searchInnerNeighbours(GameBoardPoint gbp) {

		if (gbp.getNumber() % 2 != 0) {

			try {
				GameBoardPoint tmp = this.gbpList.get(gbp.getNumber() + 8);
				tmp.setOuter(gbp);
				return tmp;
			} catch (IndexOutOfBoundsException e) {
				// TODO: handle exception
			}
		}
		return null;

	}

	/**
	 * Sucht für den übergebenen GameboardPoint den passenden Outer Nachbarn.
	 * Dabei wird beim Nachbarn die Verbindung zum Übergebenen GameboardPoint
	 * gesichert.
	 * 
	 * @param gbp
	 * @return
	 */
	private GameBoardPoint searchOuterNeighbours(GameBoardPoint gbp) {

		if (gbp.getNumber() % 2 != 0) {

			try {
				GameBoardPoint tmp = this.gbpList.get(gbp.getNumber() - 8);
				tmp.setInner(gbp);
				return tmp;
			} catch (IndexOutOfBoundsException e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	/**
	 * Sucht den Nachfolger des Übergebenen Objektes. Dabei wird beim Nachbarn
	 * die Verbindung zum Übergebenen GameboardPoint gesichert. Einen Nachfolger
	 * kann es nur bei 7, 15 und 23 geben.
	 * 
	 * @param gbp
	 * @return
	 */
	private GameBoardPoint searchHighterNeighbours(GameBoardPoint gbp) {

		GameBoardPoint tmp;

		switch (gbp.getNumber()) {
		case 7:
			tmp = this.gbpList.get(0);
			break;
		case 15:
			tmp = this.gbpList.get(8);
			break;
		case 23:
			tmp = this.gbpList.get(16);
			break;
		default:
			tmp = null;
		}

		if (tmp != null) {
			tmp.setLower(gbp);
		}

		return tmp;
	}

	/**
	 * Sucht den Vorgänger des Übergebenen Objektes. Dabei wird beim Nachbarn
	 * die Verbindung zum Übergebenen GameboardPoint gesichert.
	 * 
	 * @param gbp
	 * @return
	 */
	private GameBoardPoint searchLowerNeighbours(GameBoardPoint gbp) {

		try {
			if (gbp.getNumber() != 8 && gbp.getNumber() != 16) {
				GameBoardPoint tmp = this.gbpList.get(gbp.getNumber() - 1);
				tmp.setHighter(gbp);
				return tmp;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	private void printField() {
		for (int i = 0; i < MAX_GAMEBOARDPOINT; i++) {
			GameBoardPoint tmp = this.gbpList.get(i);

			System.out.print("Nummer: " + i + "-I Lower  " + tmp.getLower().getNumber() + "-I Higher "
					+ tmp.getHighter().getNumber());

			if (tmp.getInner() != null) {
				System.out.print("-I Inner: " + tmp.getInner().getNumber());
			}
			if (tmp.getOuter() != null) {
				System.out.print("-I Outer " + tmp.getOuter().getNumber());
			}
			System.out.println();

		}

	}

	/**
	 * 
	 * @param roundnumber
	 * @param gbp
	 * @return Ob es ein Gültiger zug ist wird ermittelt. Wenn nicht Gültig,
	 *         wird false geliefert
	 */
	public boolean executeHuman(final int roundnumber, GameBoardPoint gbp) {

		boolean turn_next = true;

		this.calcHeadline(roundnumber);
		Rule rule = new Rule(this);

		if (action == Event_Message.PLAYER_SET_STONE && roundnumber < 18 && rule.setStone(gbp)) {

			if (roundnumber % 2 == 0) {
				gbp.setSide(StoneSide.PLAYER1);

			} else {
				gbp.setSide(StoneSide.PLAYER2);

			}
			if (!rule.isMill(gbp).isEmpty()) {
				notifyAllObserver(Event_Message.PLAYER_CAN_DELETE);
				turn_next = false;
			}

		} else if (this.action == Event_Message.PLAYER_CHOOSE_STONE && roundnumber >= 18) {
			
			notifyAllObserver(Event_Message.PLAYER_MOVE_STONE);
			turn_next = false;

		} else {
			turn_next = false;
		}

		// Beendet die Runde und ermittelt wer als nächstes dran is
		if (turn_next) {

			if (roundnumber % 2 == 0) {
				notifyAllObserver(Event_Message.PLAYER2_TURN);

			} else {
				notifyAllObserver(Event_Message.PLAYER1_TURN);

			}

		}
		return turn_next;
	}

	/**
	 * Bewegt einen Stein
	 * 
	 * @param des
	 * @param source
	 */
	public boolean moveStone(GameBoardPoint des, GameBoardPoint source) {

		des.setSide(source.getSide());
		source.setSide(StoneSide.WITHOUT_PLAYER);
		return false;
	}

	public boolean DeleteStone(GameBoardPoint gbp, int roundNumber) {

		boolean turn_ok = false;
		Rule rule = new Rule(this);

		if (rule.deleteStone(gbp)) {

			gbp.setSide(StoneSide.WITHOUT_PLAYER);
			turn_ok = true;

			if (roundNumber < 18) {

				this.notifyAllObserver(Event_Message.PLAYER_SET_STONE);

			} else {

				this.notifyAllObserver(Event_Message.PLAYER_MOVE_STONE);

			}
			if (roundNumber % 2 == 0) {

				notifyAllObserver(Event_Message.PLAYER2_TURN);

			} else {

				notifyAllObserver(Event_Message.PLAYER1_TURN);

			}

		}

		return turn_ok;
	}

	private void calcHeadline(int roundNumber) {

		if (roundNumber < 18) {
			this.notifyAllObserver(Event_Message.PLAYER_SET_STONE);

		} else {
			this.notifyAllObserver(Event_Message.PLAYER_CHOOSE_STONE);
		}
	}

	/**
	 * 
	 * @param roundnumber
	 */
	public void executeCPU(final int roundnumber) {

		calcHeadline(roundnumber);
		Rule r = new Rule(this);

		// Beispiel KI mit zufall
		if (roundnumber < 18) {

			boolean tmp = true;
			while (tmp) {

				GameBoardPoint gg = this.gbpList.get((int) (Math.random() * 100 % 24));
				while (r.setStone(gg)) {
					tmp = false;
					gg.setSide(StoneSide.PLAYER2);
				}

			}

		}
		notifyAllObserver(Event_Message.PLAYER1_TURN);

	}

	@Override
	public void notifyObserver(Event_Message event) {
		if (event != Event_Message.PLAYER1_TURN && event != Event_Message.PLAYER2_TURN) {
			this.action = event;
		}

	}

	public List<GameBoardPoint> getgbpList() {
		return this.gbpList;
	}

	public List<GameBoardPoint> getCopyList() {

		List<GameBoardPoint> copy = new ArrayList<>();

		Collections.copy(copy, this.gbpList);
		return copy;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gbpList == null) ? 0 : gbpList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameBoardLogic other = (GameBoardLogic) obj;
		if (gbpList == null) {
			if (other.gbpList != null)
				return false;
		} else if (!gbpList.equals(other.gbpList))
			return false;
		return true;
	}

}
