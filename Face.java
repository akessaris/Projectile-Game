package edu.nyu.cs.ank352;

import processing.core.PApplet;
import processing.core.PImage;

public class Face {

	/**
	 * Variable for which image to use
	 */
	private final static String FACE = "myFace.png";
	
	/**
	 * Object to hold an image
	 */
	private PImage img;
	
	/**
	 * Reference to PApplet class
	 */
	PApplet processing;
	
	/**
	 * Variables for x,y coordinates
	 */
	public int x,y;
	
	/**
	 * Variable for speed (only needed in y direction)
	 */
	private int speedY = 10;
	
	/**
	 * Constructor
	 */
	public Face(PApplet processing) {
		/**
		 * Initial properties
		 */
		this.processing = processing;
		
		/**
		 * Set position
		 */
		this.x = processing.width - TestGame.MARGINS*2;
		this.y = processing.height/2;
		
		/**
		 * Load the face image
		 */
		this.img = processing.loadImage(Face.FACE);
	}
	
	/**
	 * Get width of face
	 */
	public int getWidth() {
		return this.img.width;
	}
	
	/**
	 * Get height of face
	 */
	public int getHeight() {
		return this.img.height;
	}
	
	/**
	 * Draw face
	 */
	public void draw() {
		processing.image(this.img, this.x, this.y, TestGame.MARGINS*2, TestGame.MARGINS*2);
	}
	
	/**
	 * Slide the face further up or down, depending upon the current direction it's moving.
	 */
	public void move() {
		
		/**
		 * Calculate movement
		 */
		int newY = this.y + this.speedY; 
		
		/**
		 * Check bounds
		 */
		boolean outOfBoundsUpwards = newY <  0;
		boolean outOfBoundsDownwards = newY > processing.height - TestGame.MARGINS*2;
		
		/**
		 * If out of bounds, reverse direction
		 */
		if (outOfBoundsDownwards ||  outOfBoundsUpwards) {
			this.toggleDirection();
			newY = this.y + this.speedY;
		}
		/**
		 * Update position
		 */
		this.y = newY;
	}
	
	/**
	 * Set speed in y direction
	 */
	public void setSpeed(int speedY) {
		this.speedY = speedY;
	}
	
	public void toggleDirection() {
		this.speedY = -this.speedY;
	}
	
	/**
	 * Set speed so face goes up
	 */
	public void goDown() {
		/**
		 * Set speed to positive value
		 */
		this.speedY = Math.abs(this.speedY);
	}

	/**
	 * Set speed so face goes down
	 */
	public void goUp() {
		/**
		 * Set speed to negative value
		 */
		this.speedY = -Math.abs(this.speedY);
	}	
}
