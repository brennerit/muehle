package com.mygdx.game.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.game.GameBoardPoint.StoneSide;

/**
 * Diese Klasse Repr�sentiert das Spielfeld. Das Spielfeld besteht aus
 * Spielsteine und ist 9 * 9 gro� Dabei sind die viele Felder NULL, da sie nicht
 * gebraucht werden.
 * 
 * @author Ahmed
 *
 **/
public class Gameboard {

	private Texture gamefieldTex;
	private GameBoardLogic logic;
	private List<GameBoardPointConnecter> connecterlist;



	public Gameboard() {
		this.gamefieldTex = new Texture("muehle_board.png");
		this.logic = new GameBoardLogic();

		this.connecterlist = new ArrayList<GameBoardPointConnecter>();

		// Außen
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(0), 250, 380));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(1), 440, 380));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(2), 630, 380));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(3), 630, 200));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(4), 630, 20));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(5), 440, 20));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(6), 250, 20));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(7), 250, 200));

		// Mitte
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(8), 310, 320));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(9), 440, 320));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(10), 570, 320));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(11), 570, 200));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(12), 570, 80));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(13), 440, 80));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(14), 310, 80));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(15), 310, 200));

		// Innen
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(16), 370, 260));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(17), 440, 260));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(18), 510, 260));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(19), 510, 200));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(20), 510, 140));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(21), 440, 140));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(22), 370, 140));
		connecterlist.add(new GameBoardPointConnecter(logic.getgbpList().get(23), 370, 200));
	}

	/**
	 * Mit dieser Methode erh�lt man die Texture vom Spielfeld
	 * 
	 * @return
	 */
	public Texture getGamefieldTexture() {
		return this.gamefieldTex;
	}

	public void update() {

		this.logic.update();

	}

	public void render(SpriteBatch batch) {

		Iterator<GameBoardPointConnecter> iter = this.connecterlist.iterator();

		while (iter.hasNext()) {
			GameBoardPointConnecter gc = iter.next();
			gc.render(batch);
		}

	}

	public void dispose() {

	}

}
