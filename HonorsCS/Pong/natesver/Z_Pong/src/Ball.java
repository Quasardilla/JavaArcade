import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.sound.sampled.Clip;

public class Ball {
	public int x,y,w,h;
	public double dx,dy;

	private int angle = 45;
	private int speed = 7;

	public Ball(int x, int y, int angle, int w, int h) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.dx = 5;
		this.dy = 5;
		this.w = w;
		this.h = h;
	}

	public void drawMe(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.drawImage(Game.ballImage, x - w, y - h, w * 4, h * 4, null);
	}
	
	public /*synchronized*/ void playSound(Clip c) {
		c.stop();
		c.setFramePosition(0);
		c.start();
	}

	public void updt() {
		this.x += this.dx;
		this.y += this.dy;
	}
	
	
	
	int r(int min, int max) {
		return (int)Math.floor(Math.random()*(max-min+1)+min);
	}
	void rst() {
		playSound(Game.lose);
		Game.hank = Game.hankSad;
		Game.opacity = 1;
		Game.player1.y = Game.PREF_H / 2;
		Game.player2.y = Game.PREF_H / 2;
		Game.playState = "Waiting";
		this.x = Game.PREF_W / 2;
		this.y = r(10, Game.PREF_H - 10);
		
		if(dx < 0) 
			dx = 5;
		else
			dx = -5;
		
		if(dy < 0) 
			dy = 5;
		else
			dy = -5;
	}

	public void physics() {

		if (y < 0 || y + h > Game.PREF_H) {
			dy = -dy;
			playSound(Game.collide);
		}


		if (x < 0) {	
			rst();
			Game.player2.score++;
			Game.whoScored = "P2";
		} else if (x + w > Game.PREF_W) {
			rst();
			Game.player1.score++;
			Game.whoScored = "P1";
		}
		
		Game.testScores(Game.player1.score, Game.player2.score);

		if(this.checkAndReactToCollisionWith(Game.player1.getHitbox())
		||this.checkAndReactToCollisionWith(Game.player2.getHitbox())) {
			
			Game.hank = Game.hankSmiles;
			Game.opacity = 1;
			
			playSound(Game.collide);
			if(dx < 0) {
				dx -= 0.2;
			}
			else {
				dx += 0.2;
			}
			
			if(dy < 0) 
				dy -= 0.2;
			else
				dy += 0.2;;
		
		}

		updt();

	}

	/**
	 * Determines the intersecting side for the brick in relation to another brick
	 * return true for a collision and false otherwise
	 */
	public boolean checkAndReactToCollisionWith(Rectangle r) {

		switch (getSideForIntersection(r, x + w / 2, y + h / 2)) {

			case 0:
				return checkCollisionTopOfRectangle(r);
			case 1:
				return checkCollisionRightSideOfRectangle(r);
			case 2:
				return checkCollisionBottomOfRectangle(r);
			case 3:
				return checkCollisionLeftSideOfRectangle(r);
			default:
				return false;

		}

	}

	/**
	 * Returns the side where a collision would occur if possible 0 = top 1 = right
	 * 2 = bottom 3 = left
	 */
	private int getSideForIntersection(Rectangle r, int x1, int y1) {
		double slopeMajor = (double) r.height / r.width; // major diagonal slope
		double slopeMinor = (double) -r.height / r.width; // minor diagonal slope
		double bMajor = r.y - slopeMajor * r.x; // major diagonal y-intercept
		double bMinor = r.y - slopeMinor * (r.x + r.width); // minor diagonal y-intercept

		boolean aboveMajor = y1 < slopeMajor * x1 + bMajor; // Is the given point above the major diagonal
		boolean aboveMinor = y1 < slopeMinor * x1 + bMinor; // Is the given point above the minor diagonal

		if (aboveMajor && aboveMinor)
			return 0; // The point is above the other brick
		if (aboveMajor && !aboveMinor)
			return 1; // The point is to the right of the other brick
		if (!aboveMajor && !aboveMinor)
			return 2; // The point is below the other brick
		if (!aboveMajor && aboveMinor)
			return 3; // The point is to the left of the other brick

		return -1; // Should never get here since "not above" is below OR ON a diagonal
	}

	private boolean checkCollisionLeftSideOfRectangle(Rectangle r) {
		boolean collision = false;

		if (y + h > r.y && y < r.y + r.height) {
			if (x + w + 5 > r.x) {
				dx = -Math.abs(dx);
				x = r.x - w;
				if (x <= 0) { // don't let the brick get bumped off the panel
					x = 0;
					r.x = x + w; // in case the colliding brick is moving, stop it from overlapping this brick
									// the the edges of the panel
				}
				collision = true;
			}
		}
		return collision;
	}

	private boolean checkCollisionRightSideOfRectangle(Rectangle r) {
		boolean collision = false;

		if (y + h > r.y && y < r.y + r.height) {
			if (x < r.x + r.width) {
				dx = Math.abs(dx);
				x = r.x + r.width;
				if (x + w >= Game.PREF_W) { // don't let the brick get bumped off the panel
					x = Game.PREF_W - w;
					r.x = x - r.width; // in case the colliding brick is moving, stop it from overlapping this brick
										// the the edges of the panel
				}
				collision = true;
			}
		}
		return collision;
	}

	private boolean checkCollisionBottomOfRectangle(Rectangle r) {
		boolean collision = false;

		if (x + w > r.x && x < r.x + r.width) {
			if (y < r.y + r.height) {
				dy = Math.abs(dy);
				dx = Math.abs(dx);
				y = r.y + r.height;
				if (y + h >= Game.PREF_H) { // don't let the brick get bumped off the panel
					y = Game.PREF_H - h;
					r.y = y - r.height; // in case the colliding brick is moving, stop it from overlapping this brick
					// the the edges of the panel
				}
				collision = true;
			}
		}
		return collision;
	}

	private boolean checkCollisionTopOfRectangle(Rectangle r) {
		boolean collision = false;

		if (x + w > r.x && x < r.x + r.width) {
			if (y + h > r.y) {
				dy = -Math.abs(dy);
				dx = -Math.abs(dx);
				y = r.y - h;
				if (y <= 0) { // don't let the brick get bumped off the panel
					y = 0;
					r.y = y + h; // in case the colliding brick is moving, stop it from overlapping this brick
									// the the edges of the panel
				}
				collision = true;
			}
		}
		return collision;
	}

}
