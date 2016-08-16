package com.mygdx.game.icons;

import com.badlogic.gdx.graphics.Texture;


public class Cursor {

	private final Texture tex;

	private int posX, posY;

	public Cursor() {
		tex = new Texture("cursor.png");
		
	}

	public Cursor(int posX, int posY) {
		this();
		this.setPos(posX, posY);
		
	}
	
	public void setPos(int x, int y) {

		this.posX = x;
		
		this.posY = y;
	}

	public Texture getTex(){
		return tex;
	}
	
	public int getPosX(){
		return this.posX;
	}
	public int getPosY(){
		return this.posY;
	}
	public void dispose(){
		this.tex.dispose();
	}
}
