package com.mygdx.game.player;

import com.mygdx.game.game.GameBoardPoint.StoneSide;
/**
 * KI die gegen einen Menschlichen Spieler antritt.
 * @author jonathan
 *
 */
public class CPU extends Player {
	
	
	
	public CPU(StoneSide stoneSide) {
		super(stoneSide);
	}
	
	public void pullStone(){
		
	}
	
	private void calculate(){
		
	}
	/*
	 * Zu Beginn ist Beweglichkeit wichtiger wie Mühlen. Die inneren Ecken der Felder sind als erstes zu besetzen.
	 * 
	 * Es ist besser eine eigene Mühle zu öffnen anstatt eine gegnerische zu verhinden.
	 * 
	 * Zwickmühlen bilden ist erstrebenswert
	 */
}
