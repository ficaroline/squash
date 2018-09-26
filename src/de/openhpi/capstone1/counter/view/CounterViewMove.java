package de.openhpi.capstone1.counter.view;

import de.openhpi.capstone1.counter.model.Counter;
import processing.core.PApplet;

public class CounterViewMove extends AbstractCounterView { 
		
	public CounterViewMove(PApplet display, Counter counter) {
		super(display, counter);
	}
	
	public void update() {
		try {
			int x = counter.getCount();
			if (x < 200) {
				display.fill(255);
				display.rect(x, 270, 100, 10);
			}else {
				x = 0;
				display.fill(255);
				display.rect(x, 270, 100, 10);
			}
			
		} catch (ClassCastException e) {
			System.out.println(e.getMessage());
		}
	}
}
