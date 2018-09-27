package de.openhpi.capstone1.counter.starter;

import de.openhpi.capstone1.counter.builder.GUIComponent;
import de.openhpi.capstone1.counter.builder.InteractiveComponent;
import de.openhpi.capstone1.counter.builder.InteractiveCounterBuilder;
import processing.core.PApplet;

public class TheApp extends PApplet {

	InteractiveComponent interactiveCounter;

	float bx;
	int boxH = 20;
	int boxW = 100;
	boolean overBox = false;
	boolean locked = false;
	float xOffset = (float) 0.0;
	float yOffset = (float) 0.0;

	int rad = 10; // Width of the shape
	float xpos, ypos; // Starting position of shape

	float xspeed = (float) 2.8; // Speed of the shape
	float yspeed = (float) 2.2; // Speed of the shape

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
		InteractiveCounterBuilder builder = new InteractiveCounterBuilder();
		GUIComponent.construct(this, builder);
		interactiveCounter = builder.getComponent();

		bx = width / 2 - boxW /2;

//		rectMode(RADIUS);

		ellipseMode(RADIUS);
		// Set the starting position of the shape
		xpos = width / 2;
		ypos = height / 2;

	}

	@Override
	public void draw() { // draw() loops forever, until stopped
		background(204);
		fill(255);
		// rect(random(100),random(100), 10, 10);
		interactiveCounter.update();

		
		
		
		// Test if the cursor is over the box
		if (mouseX > bx && mouseX < bx + boxW && mouseY > 270 && mouseY < 270 + boxH) {
//		if (mouseX > bx - boxSize && mouseX < bx + boxSize) {
			overBox = true;
			if (!locked) {
				stroke(255);
				fill(153);
			}
		} else {
			stroke(153);
			fill(153);
			overBox = false;
		}

		// Draw the box
		rect(bx, 270, boxW, boxH);

		
		
		
		// Update the position of the shape
		xpos = xpos + (xspeed * xdirection);
		ypos = ypos + (yspeed * ydirection);
		
//		xpos = xpos + (5* xdirection);
//		ypos = ypos + (5* ydirection);

		// Test to see if the shape exceeds the boundaries of the screen
		// If it does, reverse its direction by multiplying by -1
		
		
		if (ypos > 270 - rad && bx <= xpos && xpos <= bx + 100) {
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
//			fill(204);
		}
		
	

		// Draw the shape
		ellipse(xpos, ypos, rad, rad);
		
		
		

	}

	@Override
	public void mouseClicked() {
		interactiveCounter.handleEvent();
	}

	@Override
	public void mouseDragged() {
		if(locked) {
		    bx = mouseX-xOffset; 

		 }
//		if (locked) {
//			if (bx < 200 && bx>0) {
//				bx = mouseX - xOffset;
//			}
//			else if(bx >200) {
//				bx = 200;
//			}
//			else {
//				bx=0;
//			}
//			
//		}
	}

	@Override
	public void mousePressed() {
		if (overBox) {
			locked = true;
			fill(255, 255, 255);
		} else {
			locked = false;
		}
		xOffset = mouseX - bx;

	}

	@Override
	public void mouseReleased() {
		locked = false;
	}
	
	

}
