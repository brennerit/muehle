package com.mygdx.game.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subjekt {

	public List<Observer> list;
	public Event event;

	public Subjekt() {

		this.list = new ArrayList<>();

	}

	public void registry(Observer obj) {

		boolean isdouble = false;

		for (int element = 0; element < list.size(); element++) {

			if (this.list.get(element).equals(obj)) {
				isdouble = true;
			}

		}

		if (!isdouble) {
			this.list.add(obj);
		}
	}

	public void notifyAllObserver(Event e) {
		for (int element = 0; element < list.size(); element++) {

			list.get(element).notifyObserver(e);
		}

	}
}
