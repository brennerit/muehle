package com.mygdx.game.game;

import com.mygdx.game.game.GameboardPoint.StoneSide;
import com.mygdx.game.player.Player;

/**
 * Diese Klasse repraesentiert die Regeln für das Muehlespiel. Es ist ausschliesslich moeglich zu pruefen, ob die Regeln
 * eingehalten werden. Die Klasse nimmt keine Aenderungen an dem Spielfeld vor.
 * @author Jonathan
 *
 */
public class Rule {

	private GameboardPoint[] gameboard;
	private StoneSide currentPlayer;
	private final int FIELD_LENGHT = 7;
	private final int MILL = 3;
	
	public Rule(){
		
	}

	public GameboardPoint[] getGameboard() {
		return this.gameboard;
	}
	
	/**
	 * Setzt das aktuelle Spielf, das aus einem {@link GameboardPoint[]}-Array besteht.
	 * @param gameboard
	 */
	public void setGameboard(GameboardPoint[] gameboard) {
		this.gameboard = gameboard;
	}

	public StoneSide getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	/**
	 * Setzt den aktuellen Spieler 
	 * @param currentPlayer
	 */
	public void setCurrentPlayer(StoneSide currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	/**
	 * Prueft ob es moeglich ist einen Stein an diesem Punkt des Spielfeldes zu setzen.
	 * @return true wenn der Stein gesetzt werden kann.
	 *		   false wenn der Stein nicht gesetzt werden kann.
	 */
	public boolean setStonePossible(int x , int y){
		boolean setStonePossible;
		if(gameboard[(y * FIELD_LENGHT)+ x] != null
				&& gameboard[(y * FIELD_LENGHT)+ x].getSide()==StoneSide.WITHOUT_PLAYER){
			setStonePossible = true;
		}else{
			setStonePossible = false;
		}
		return setStonePossible;
	}
	
	
	public boolean moveStoneLeftPossible(int x, int y){
		boolean isPossible;
		int newX = x;
		StoneSide side = this.gameboard[(y * FIELD_LENGHT) + newX].getSide();
		if(side!=null && side == StoneSide.MIDDLE){
			isPossible = false;
		}else{
			int border;
			if(x > FIELD_LENGHT){
				border = FIELD_LENGHT/2;
			}else{
				border = 0;
			}
			while((side = this.gameboard[(y * FIELD_LENGHT) + newX].getSide())== null && newX > border){
				newX--;
			}
		}
		isPossible = true;
		return isPossible;
	}
	
	public boolean moveStoneRightPossible(int x, int y){
		return true;
	}
	
	public boolean moveStoneUpPossible(int x, int y){
		return true;
	}
	
	public boolean moveStoneDownPossible(int x, int y){
		return true;
	}
	
	/**
	 * Prueft ob im {@link GameboardPoint[]}-Array an der Stelle Position x,y eine Muehle vorhanden ist.
	 * @param x - Koordinate vom Spielfeld
	 * @param y - y Koordinate vom Spielfeld
	 * @return true falls eine Muehle and er Position vorhanden ist, sonst false.
	 */
	public boolean isMill(int x, int y){
		boolean millXAxis = false;
		boolean millYAxis = false;
		int stonesFollowup = 0;
		for(int x_axis = 0; x_axis < FIELD_LENGHT; x_axis++){
			if(gameboard[y + x_axis].getSide() == StoneSide.MIDDLE){
				stonesFollowup = 0;
			}else if(gameboard[y + x_axis].getSide() == this.currentPlayer){
				stonesFollowup++;
			}
		}
		if(!millXAxis){
			for(int y_axis = 0; y_axis < FIELD_LENGHT; y_axis++){
				if(gameboard[y_axis + x].getSide() == StoneSide.MIDDLE){
					stonesFollowup = 0;
				}else if(gameboard[(y_axis) + x].getSide() == this.currentPlayer){
					stonesFollowup++;
				}
			}
		}
		
		return millXAxis || millYAxis;
	}
	
	
}
