package edu.nyu.cs.ank352;

import processing.core.PImage;

public class Projectiles {
	/**
	 * Reference for PApplet class
	 */
	TestGame processing;
	
	/**
	 * Variable for which image to use
	 */
	private final static String PROJECTILE= "taco.png";

	/**
	 * Object to hold an image for projectiles
	 */
	private PImage img;
	
	/**
	 * Speed of projectiles (only travel along x axis)
	 */
	private final static int XSpeed = 10;
	
	/**
	 * Position of projectiles
	 */
	public int x,y;
	
	/**
	 * Constructor
	 * @param x, y, processing
	 */
	public Projectiles (int x, int y, TestGame processing) {
		/**
		 * Set up properties of projectile
		 */
		this.processing = processing;
		
		/**
		 * Set position
		 */
		this.x = x;
		this.y = y;
		
		/**
		 * Load the projectile image
		 */
		this.img = processing.loadImage(Projectiles.PROJECTILE);
	}
	
	/**
	 * Overloaded constructor 1
	 * @param x, processing
	 */
	public Projectiles (int x, TestGame processing) {
		/**
		 * Set up properties of projectile
		 */
		this.processing = processing;
		
		/**
		 * Set position
		 */
		this.x = x;
		y = (int)(Math.random() * TestGame.HEIGHT/2) + TestGame.MARGINS;
		
		/**
		 * Load the projectile image
		 */
		this.img = processing.loadImage(Projectiles.PROJECTILE);
	}
	
	/**
	 * Overloaded constructor 2
	 * @param y, processing
	 */
	public Projectiles (TestGame processing, int y) {
		/**
		 * Set up properties of projectile
		 */
		this.processing = processing;
		
		/**
		 * Set position
		 */
		this.y = y;
		x = 0;
		
		/**
		 * Load the projectile image
		 */
		this.img = processing.loadImage(Projectiles.PROJECTILE);
	}
	
	/**
	 * Get width of projectile
	 */
	public int getWidth() {
		return this.img.width;
	}
	
	/**
	 * Get height of projectile
	 */
	public int getHeight() {
		return this.img.height;
	}
	
	/**
	 * Move the projectile across the screen
	 */
	public void move() {
		/**
		 * Calculate new coordinates
		 */
		int newX = this.x + XSpeed;
		
		/**
		 * Check if projectile goes out of bounds
		 * If so, then it is removed from the projectile array to prevent slowing down program
		 */
		boolean outOfBoundsToTheRight = newX < 0;
		if (outOfBoundsToTheRight) {
			kill();
		}
		this.x = newX;
	}
	
	/**
	 * Remove projectile from projectile array
	 */
	public void kill() {
		processing.projectiles.remove(this);
	}
	
	
	/**
	 * Draw the projectile
	 */
	public void draw() {
		processing.image(this.img, this.x, this.y, TestGame.MARGINS, TestGame.MARGINS);
	}
	
	/**
	 * Check for collision
	 */
	public static boolean isCollision(Projectiles projectile, Face face) {
		/**
		 * Flag to test if collision has occurred
		 */
		boolean collision = false;
		
		/**
		 * check whether projectile is within the box representing the face
		 */
		if (projectile.y >= face.y && projectile.y  + projectile.getHeight() <= face.y +  face.getHeight()) {
			if (projectile.x >= face.x && projectile.x +  projectile.getWidth() <= face.x + face.getWidth()) {
				collision = true;
			}
		}
		return collision;
	}
	
	
	
	
	
	
	
	
	
	
}