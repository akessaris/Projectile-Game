package edu.nyu.cs.ank352;

/**
 * This game allows the user to control a face to catch projectiles.
 * @author Alexander Kessaris
 * @version 0.1
 */

import java.util.ArrayList;

import edu.nyu.cs.ank352.Face;
import edu.nyu.cs.ank352.Projectiles;
import processing.core.*;

public class TestGame extends PApplet {
	
	/**
	 * Variable to hold number of projectiles
	 */
	public final static int TACOS = 10;
	
	/**
	 * Constants for commonly used colors
	 */
	public final int BLACK = color(0,0,0);
	public final int WHITE = color(255,255,255);
	
	/**
	 * Constants for height and width of screen
	 */
	public final static int HEIGHT = 700;
	public final static int WIDTH = 1400;
	
	/**
	 * Constant for margins
	 */
	public final static int MARGINS = 100;
	
	/**
	 * Variable to hold face
	 */
	public Face face;
	
	/**
	 * Array to hold Projectiles
	 */
	public ArrayList<Projectiles> projectiles = new ArrayList<Projectiles>();
	
	/**
	 * Variable to hold score
	 */
	public int score = 0;
	
	/**
	 * Set up screen
	 */
	public void setup() {
		/**
		 * Set size and background
		 */
		this.size(WIDTH,HEIGHT);
		this.background(BLACK);
		
		/**
		 * Initialize face
		 */
		this.face = new Face(this);
		
		/**
		 * Initialize projectiles
		 */
		int x = 0;
	
		for (int i=0; i<TestGame.TACOS; i++) {
			/**
			 * Make new projectile objects, and add them to array
			 */
			int y = getRandomSpawn();
			Projectiles projectile = new Projectiles(x,y,this);	
			this.projectiles.add(projectile);	
		}
	}
	
	/**
	 * Draw (runs continuously)
	 */
	public void draw() {
		/**
		 * Wipe screen white
		 */
		this.background(BLACK);
		
		/**
		 * Display score
		 */
		String points = "Score: " + Integer.toString(score);
		fill(255);
		text(points, (WIDTH/2.0f), 0, 200, 200);
		
		/**
		 * Draw face
		 */
		face.move();
		face.draw();
		
		/**
		 * Loop through projectiles array to move and draw
		 */
		for (int i=0; i<projectiles.size(); i++) {
			/**
			 * Create projectiles and moves them across screen
			 */
				Projectiles projectile = projectiles.get(i);
				projectile.move(); 
				projectile.draw();
			}
		
		/**
		 * Detect any collisions between projectiles and face
		 */
		ArrayList<Projectiles> projectilesToRemove = new  ArrayList<Projectiles>();
		for (Projectiles projectile: projectiles) {
			if (Projectiles.isCollision(projectile,  face)) {
					/**
					 * If there is a collision, remove the projectile from the screen
					 * Also, add 1 to the score
					 */
					projectilesToRemove.add(projectile);
					score++;
				}
		}	
		/**
		 * Remove all projectiles we earmarked as removable
		 */
		for (Projectiles projectile: projectilesToRemove) {
			projectile.kill();
		}
}

	/**
	 * Called whenever a key is pressed by the  user.
	 * Move the face up or down
	 */
	public void keyPressed() {
		if (key == CODED) {
			switch (keyCode) {
				case UP:
					face.goUp();
					break;
				case DOWN:
					face.goDown(); 
					break;
			}
		}	
	}
	
	/**
	 * Called to generate a random spawn for projectiles
	 * @return spawn
	 */
	public int getRandomSpawn() {
		/**
		 * Generate random int within range of height
		 */
		int spawn = (int)(Math.random() * HEIGHT);
		return spawn;
	}	
}