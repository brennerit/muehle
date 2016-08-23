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

	public GameBoardPointConnecter(GameBoardPoint gbp, int posX, int posY) {

		this.gbp = gbp;

		this.hitbox = new Circle(posX, posY, 25);

		Pixmap pix = new Pixmap(50, 50, Format.RGBA8888);

		pix.setColor(1, 0, 0, 0.5f);

		pix.fillCircle(25, 25, 25);

		tex = (new Texture(pix));

		pix.dispose();

	}

	private Color color;

	public void update(Color c) {
		if (hitbox.contains(Gdx.input.getX(), Main.WINDOW_HEIGHT - Gdx.input.getY())) {

			this.color = new Color(0, 0, 1, 1);
		} else {
			this.color = c;
		}

	}

	public Texture getTexture() {
		return this.tex;
	}

	public void render(SpriteBatch batch) {
		this.update(batch.getColor());

		float posX = (this.hitbox.x - this.tex.getWidth() / 2);
		float posY = (this.hitbox.y - this.tex.getHeight() / 2);

		Color ccc = batch.getColor();

		batch.setColor(color);

		batch.draw(this.tex, posX, posY);

		batch.setColor(ccc);
	}

	public void dispose() {
		this.tex.dispose();
	}
}
