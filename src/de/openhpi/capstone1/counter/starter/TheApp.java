package de.openhpi.capstone1.counter.starter;

import de.openhpi.capstone1.counter.builder.InteractiveComponent;
import processing.core.PApplet;

public class TheApp extends PApplet {

	InteractiveComponent interactiveCounter;

	int hochz, hochz2;
	int boxH = 20;
	int boxW = 100;
	int x2;
	boolean overBox = false;
	boolean locked = false;
	float xOffset = (float) 0.0;
	float yOffset = (float) 0.0;
	int rad = 10; // Width of the shape
	float xpos, ypos; // Starting position of shape
	float xspeed = (float) 2.5; // Speed of the shape
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
		ellipseMode(RADIUS);
		// Set the starting position of the shape
		xpos = width / 2;
		ypos = height / 2;
	}

	/**
	 * Tastaturbedienung oberes Paddle
	 * 
	 * */	
	public void keyPressed() {
		if (key == CODED) {
			if (keyCode == LEFT && x2>0) {
				x2 -= 15;
			} else if (keyCode == RIGHT && x2<width-boxW) {
				x2 += 15;
			} 
		} 
	}

	@Override
	public void draw() { // draw() loops forever, until stopped
		background(204);
		
		xspeed =  (float) (Math.round(xspeed * 100) / 100.0);  	
		// Score Anzeige
		hochz++;
		textSize(15);
		fill(0, 102, 153);
		text("Score: " +hochz2 + " Speed: " + xspeed, 10, 15 ); 		
		if (ypos != 350) hochz2 = (int) hochz/10;				
		if (hochz2%2==0 && ypos != 350) xspeed+=0.01;
			
		fill(255);
		float  x1 = map(mouseX, 0, width, 0, width - boxW);
		rect(x2, 30, boxW, boxH);		// Top Paddle
		rect(x1, 270, boxW, boxH); 		// Bottom Paddle
		
		// Update the position of the shape
		xpos = xpos + (xspeed * xdirection);
		ypos = ypos + (xspeed * ydirection);
		if (ypos > 270 - rad && x1 <= xpos && xpos <= x1 + 100) ydirection *= -1;	// untere Seite auf dem unteren Paddle
		if (ypos < 50 + rad && x2 <= xpos && xpos <= x2 + 100) ydirection *= -1;	// Obere Seite auf dem oberen Paddle		
		if (xpos > 300 - rad || xpos < rad ) 	xdirection *= -1;                   // Ränder  
		if (ypos > 300 - rad || ypos < 30 ) {										// Ausserhalb
			xpos = 350;
			ypos = 350;
		}

		ellipse(xpos, ypos, rad, rad);
	}

}
