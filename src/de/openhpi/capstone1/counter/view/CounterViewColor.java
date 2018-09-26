package de.openhpi.capstone1.counter.view;

import de.openhpi.capstone1.counter.model.Counter;
import processing.core.PApplet;

public class CounterViewColor extends AbstractCounterView {


	public CounterViewColor(PApplet display, Counter counter) {
		super(display, counter);
	}

	public void update() {
		try {
			int x = counter.getCount();
			display.fill(255);
			display.ellipseMode(0);
			display.ellipse(x, 250, 20, 20);



		} catch (ClassCastException e) {
			System.out.println(e.getMessage());
		}
	}
}
