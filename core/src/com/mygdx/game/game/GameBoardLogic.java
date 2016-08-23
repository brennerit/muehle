package com.mygdx.game.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.game.GameBoardPoint.StoneSide;

/**
 * Die Logik vom Spielfeld
 * @author ahmed
 *
 */
public class GameBoardLogic {

	private List<GameBoardPoint> gbpList;

	private final int MAX_GAMEBOARDPOINT = 24;

	public GameBoardLogic() {

		this.gbpList = new ArrayList<GameBoardPoint>();

		this.initField();

		// this.rule = new Rule();

		this.printField();

		// this.connecter = new GameboardPointConnecter(0, 0);
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

	public void update() {

	}

	public List<GameBoardPoint> getgbpList() {
		return this.gbpList;
	}

}
