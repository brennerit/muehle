package com.mygdx.game.game;

import java.util.List;

import com.mygdx.game.game.GameboardPoint.StoneSide;

import java.awt.SystemColor;
import java.util.ArrayList;

/**
 * Diese Klasse repraesentiert das Muehle-Spielfeld. 
 * @author jonathan
 *
 */
public class GameBoardJonbert {
	
	public static void main(String [] args){
		GameBoardJonbert board = new GameBoardJonbert();
		board.points.set(0, new GameboardPoint(StoneSide.PLAYER1));
		board.points.set(1, new GameboardPoint(StoneSide.PLAYER1));
		board.points.set(2, new GameboardPoint(StoneSide.PLAYER1));
		
		board.points.set(4, new GameboardPoint(StoneSide.PLAYER2));
		board.points.set(5, new GameboardPoint(StoneSide.PLAYER2));
		board.points.set(6, new GameboardPoint(StoneSide.PLAYER2));
		
		board.printField();
	}
	
	private List<GameboardPoint> points;
	private static final int LISTSIZE = 24;
	
	public GameBoardJonbert(){
		this.points = new ArrayList<>();
		for(int i = 0;i < LISTSIZE;i++){
			points.add(new GameboardPoint(StoneSide.WITHOUT_PLAYER));
		}
		setNeighbours();
	}
	
	/**
	 * Initialisert die Nachbarn der einezelnen Punkte des Spielfeldes.
	 */
	private void setNeighbours(){
		this.points.get(0).setNeighboursLeftSide(null);
		this.points.get(0).setNeighboursUpSide(null);
		this.points.get(0).setNeighboursRightSide(this.points.get(1));
		this.points.get(0).setNeighboursBottomSide(this.points.get(7));
		
		this.points.get(1).setNeighboursLeftSide(this.points.get(1));
		this.points.get(1).setNeighboursUpSide(null);
		this.points.get(1).setNeighboursRightSide(this.points.get(5));
		this.points.get(1).setNeighboursBottomSide(this.points.get(3));
		
		this.points.get(2).setNeighboursLeftSide(null);
		this.points.get(2).setNeighboursUpSide(null);
		this.points.get(2).setNeighboursRightSide(this.points.get(1));
		this.points.get(2).setNeighboursBottomSide(this.points.get(7));
		
		this.points.get(3).setNeighboursLeftSide(null);
		this.points.get(3).setNeighboursUpSide(null);
		this.points.get(3).setNeighboursRightSide(this.points.get(1));
		this.points.get(3).setNeighboursBottomSide(this.points.get(7));
		
		this.points.get(4).setNeighboursLeftSide(null);
		this.points.get(4).setNeighboursUpSide(null);
		this.points.get(4).setNeighboursRightSide(this.points.get(1));
		this.points.get(4).setNeighboursBottomSide(this.points.get(7));
		
		this.points.get(5).setNeighboursLeftSide(null);
		this.points.get(5).setNeighboursUpSide(null);
		this.points.get(5).setNeighboursRightSide(this.points.get(1));
		this.points.get(5).setNeighboursBottomSide(this.points.get(7));
		
		this.points.get(7).setNeighboursLeftSide(null);
		this.points.get(7).setNeighboursUpSide(null);
		this.points.get(7).setNeighboursRightSide(this.points.get(1));
		this.points.get(7).setNeighboursBottomSide(this.points.get(7));
		
		this.points.get(0).setNeighboursLeftSide(null);
		this.points.get(0).setNeighboursUpSide(null);
		this.points.get(0).setNeighboursRightSide(this.points.get(1));
		this.points.get(0).setNeighboursBottomSide(this.points.get(7));
		
		this.points.get(0).setNeighboursLeftSide(null);
		this.points.get(0).setNeighboursUpSide(null);
		this.points.get(0).setNeighboursRightSide(this.points.get(1));
		this.points.get(0).setNeighboursBottomSide(this.points.get(7));
		
	}
	
	/**
	 * ToDo
	 * Prüft ob ein Stein gesetzt werden darf und setzt diesen falls möglich.
	 * @param side
	 * @param number
	 */
	public void setStoneSideAtPosition(StoneSide side, int number){
		this.points.get(number).setSide(side);
	}
	
	/**
	 * WTF DELETE THIS SHIT xD
	 * @param number
	 * @return
	 */
	public void printField(){
		int count = 0;
		int fields = 7;
		for( int i = 0;i < fields; i++){
			for (int j = 0; j < fields; j++) {
				int pos = (i*fields)+ j;
				if(i==0 || i == fields-1){
					if(j==0 || j == fields/2 || j == fields-1){
						System.out.print(getElement(pos));
					}else{
						System.out.print("O");
					}
				}
				if(i==1 || i == fields-2){
					if(j > 0 && j%2 !=0){
						System.out.print(getElement(pos));
					}else{
						System.out.print("O");
					}
				}
				if(i==2 || i == fields-3){
					if(j > 1 && j < 5){
						System.out.print(getElement(pos));
					}else{
						System.out.print("O");
					}
				}
				if(i == fields/2){
					if(j!=fields/2){
						System.out.print(getElement(pos));
					}else{
						System.out.print("O");
					}
				}
			}
			System.out.println("");
		}
	}
	
	/**
	 * WTF DELETE THIS SHIT xD
	 * @param number
	 * @return
	 */
	public String getElement(int number){
		StoneSide side = null;
		switch(number){
		case 0:
			side =  this.points.get(0).getSide();
			break;	
		case 3: 
			side =  this.points.get(1).getSide();
			break;
		case 6:	
			side =  this.points.get(2).getSide();
			break;
		case 8:
			side =  this.points.get(8).getSide();
			break;
		case 10:
			side =  this.points.get(9).getSide();
			break;
		case 12:
			side =  this.points.get(10).getSide();
			break;
		case 16:
			side =  this.points.get(16).getSide();
			break;
		case 17:
			side =  this.points.get(17).getSide();
			break;	
		case 18:
			side =  this.points.get(18).getSide();
			break;
		case 21: 
			side =  this.points.get(7).getSide();
			break;
		case 22: 
			side =  this.points.get(15).getSide();
			break;
		case 23: 
			side =  this.points.get(23).getSide();
			break;
		case 25: 
			side =  this.points.get(19).getSide();
			break;
		case 26: 
			side =  this.points.get(11).getSide();
			break;
		case 27:
			side =  this.points.get(3).getSide();
			break;
		case 30:
			side =  this.points.get(22).getSide();
			break;
		case 31: 
			side =  this.points.get(21).getSide();
			break;
		case 32: 
			side =  this.points.get(20).getSide();
			break;
		case 36: 
			side =  this.points.get(14).getSide();
			break;
		case 38: 
			side =  this.points.get(13).getSide();
			break;
		case 40: 
			side =  this.points.get(12).getSide();
			break;
		case 42: 
			side =  this.points.get(6).getSide();
			break;
		case 45: 
			side =  this.points.get(5).getSide();
			break;
		case 48: 
			side =  this.points.get(4).getSide();
			break;
		}
		String sign;
		if(side == StoneSide.PLAYER1){
			sign = "P";
		}else if(side == StoneSide.PLAYER2){
			sign = "C";
		}else{
			sign =  "X";
		}
		return sign;
	}
	
}
