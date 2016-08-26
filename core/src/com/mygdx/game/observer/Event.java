package com.mygdx.game.observer;

public class Event {

	public enum Event_Message{
		
		PLAYER1_TURN, PLAYER2_TURN, PLAYER1_WIN, PLAYER2_WIN, PLAYER_CAN_DELETE;
		
	}
	
	private Event_Message fireEvent;
	
	public void setEvent(Event_Message e){
		this.fireEvent = e;
	}
	public Event_Message getEvent(){
		return fireEvent;
	}
}
