package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.mygdx.game.Main;
import com.mygdx.game.game.GameBoardPoint.StoneSide;

/**
 * Diese Klasse repr�sentiert die Schnittstelle zwischen den Menschen und den
 * Punkten auf dem Spielbrett. Es erm�glichst die Interaktion.
 * 
 * @author Ahmed
 *
 */
public class GameBoardPointConnecter {

	private Circle hitbox;

	private Texture tex;

	private GameBoardPoint gbp;

	private boolean istouched;
	private StoneSide side;

	public GameBoardPointConnecter(GameBoardPoint gbp, int posX, int posY) {

		this.gbp = gbp;

		this.hitbox = new Circle(posX, posY, 25);

		chanceStonesideTex(StoneSide.WITHOUT_PLAYER);
		this.side = StoneSide.WITHOUT_PLAYER;

	}

	public GameBoardPoint getGameBoardPoint() {
		return gbp;
	}

	public void setGameBoardPoint(GameBoardPoint gbp) {
		this.gbp = gbp;
	}

	public void chanceStonesideTex(StoneSide s) {

		if (s == StoneSide.PLAYER1) {
			this.tex = new Texture("Stone_Player1.bmp");

		}
		if (s == StoneSide.PLAYER2) {

			this.tex = new Texture("Stone_Player2.bmp");
		}
		if (s == StoneSide.WITHOUT_PLAYER) {

			this.tex = new Texture("Stone_Without.bmp");
		}

	}

	/**
	 * Sagt aus, ob es von der Maus berührt wurde
	 * 
	 * @return
	 */
	public boolean isTouched() {
		return this.istouched;
	}

	/**
	 * Überprüft ob ein connector von der Maus berührt wird und färbt es
	 * entsprechend ein
	 * 
	 * @param c
	 */
	public void update() {
		if (hitbox.contains(Gdx.input.getX(), Main.WINDOW_HEIGHT - Gdx.input.getY())) {

			if (Gdx.input.justTouched()) {

				this.istouched = true;

			}else{
				this.istouched = false;

			}
		}

		if(this.side != this.getGameBoardPoint().getSide()){
			this.chanceStonesideTex(this.getGameBoardPoint().getSide());
		}
	}

	public Texture getTexture() {
		return this.tex;
	}

	public void render(SpriteBatch batch) {
		this.update();

		float posX = (this.hitbox.x - this.tex.getWidth() / 2);
		float posY = (this.hitbox.y - this.tex.getHeight() / 2);

		batch.draw(this.tex, posX, posY);

	}

	public void dispose() {
		this.tex.dispose();
	}
}
