package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Diese Klasse repräsentiert die Schnittstelle zwischen den Menschen und den
 * Punkten auf dem Spielbrett. Es ermöglichst die Interaktion.
 * 
 * @author Ahmed
 *
 */
public class GameboardPointConnecter {

	private int posXofArray;
	private int posYofArray;

	Rectangle rect;

	Sprite tex;

	public GameboardPointConnecter(int posXofArray, int posYofArray) {
		this.posXofArray = posXofArray;
		this.posYofArray = posYofArray;

		this.rect = new Rectangle(30, 30, 30, 30);

		Pixmap pixmap = new Pixmap(30, 30, Format.RGBA8888);

		this.tex = new Sprite(new Texture(pixmap));

		pixmap.dispose();
	}

	public void update() {
		if (rect.contains(Gdx.input.getX(), Gdx.input.getY())) {
			this.tex.setColor(1, 1, 1, 1);
			System.out.println("DDD");
		} else {
			this.tex.setColor(0, 0, 0, 0);
			System.out.println("llllll");
		}	

	}
	public Texture getTexture(){
		return this.tex.getTexture();
	}

	public void dispose() {
		this.tex.getTexture().dispose();
	}
}
