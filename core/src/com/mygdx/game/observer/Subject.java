package com.mygdx.game.observer;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.observer.Event.Event_Message;

public abstract class Subject {

	public List<Observer> list;
	public Event event;

	public Subject() {

		this.list = new ArrayList<>();

	}

	public void registry(Observer obj) {

		this.list.add(obj);

	}

	public void notifyAllObserver(Event.Event_Message e) {
		for (int element = 0; element < list.size(); element++) {

			list.get(element).notifyObserver(e);
		}

	}
}
