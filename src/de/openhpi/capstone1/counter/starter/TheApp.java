package de.openhpi.capstone1.counter.starter;

import de.openhpi.capstone1.counter.builder.GUIComponent;
import de.openhpi.capstone1.counter.builder.InteractiveComponent;
import de.openhpi.capstone1.counter.builder.InteractiveCounterBuilder;
import processing.core.PApplet;

public class TheApp extends PApplet {

	InteractiveComponent interactiveCounter;

	int hochz, hochz2;
//	float bx;
	int boxH = 20;
	int boxW = 100;
	
	float bx = map(mouseX, 0, width, 0, width - boxW);
	
	boolean overBox = false;
	boolean locked = false;
	float xOffset = (float) 0.0;
	float yOffset = (float) 0.0;

	int rad = 10; // Width of the shape
	float xpos, ypos; // Starting position of shape

	float xspeed = (float) 2.5; // Speed of the shape
//	float yspeed = (float) 2.5; // Speed of the shape

	int xdirection = 1; // Left or Right
	int ydirection = 1; // Top to Bottom

	@Override
	public void settings() {
		size(300, 300);
	}

	@Override
	public void setup() { // setup() runs once
		noStroke();

		frameRate(30);
//		InteractiveCounterBuilder builder = new InteractiveCounterBuilder();
//		GUIComponent.construct(this, builder);
//		interactiveCounter = builder.getComponent();

		bx = width / 2 - boxW /2;

//		rectMode(RADIUS);

		ellipseMode(RADIUS);
		// Set the starting position of the shape
		xpos = width / 2;
		ypos = height / 2;

	}

	@Override
	public void draw() { // draw() loops forever, until stopped
		
		
		
		xspeed =  (float) (Math.round(xspeed * 100) / 100.0);  		
		background(204);
		
		hochz++;
		textSize(15);
		fill(0, 102, 153);
		text("Score: " +hochz2 + " Speed: " + xspeed, 10, 30); 
		
		if (ypos != 350) hochz2 = (int) hochz/10;				
		if (hochz2%2==0 && ypos != 350) xspeed+=0.01;
				
		fill(255);
//		interactiveCounter.update();

		float  x1 = map(mouseX, 0, width, 0, width - boxW);
		rect(x1, 270, boxW, boxH);
	
		// Update the position of the shape
		xpos = xpos + (xspeed * xdirection);
		ypos = ypos + (xspeed * ydirection);
//		ypos = ypos + (yspeed * ydirection);

		if (ypos > 270 - rad && x1 <= xpos && xpos <= x1 + 100) {
			ydirection *= -1;	
		}
		
		if (xpos > 300 - rad || xpos < rad ) {
			xdirection *= -1;
		}
		if (ypos < rad) {
			ydirection *= -1;
		}
		if (ypos > 300 - rad ) {
			xpos = 350;
			ypos = 350;
		}

		ellipse(xpos, ypos, rad, rad);
		

	}

}
