package DelaliSpaceInvaders;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

//Delali Dzivenu 
//Program description:
//Feb 14, 2022

public class GameObject {

//	Variables for classes object (instance variables) 
	private int x, y, w, h;
	private int dx, dy;
	private int xmin, xmax, ymin, ymax;
	private Color color;
//variables for moving with keys 
	private boolean up, down, left, right;
	private int upKey, downKey, leftKey, rightKey;
	private boolean isWanderer;
	private int totalHits;

// A constructor... it allows us to assign initial values to each brick object 
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public GameObject(int x, int y, int w, int h, Color color) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public GameObject(int x, int y, int w, int h, int dx, int dy, int xmin, int xmax, int ymin, int ymax,
			boolean isWanderer, Color color) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.dx = dx;
		this.dy = dy;
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.isWanderer = isWanderer;
		this.color = color;
	}

	public GameObject(int i) {
		// TODO Auto-generated constructor stub
	}

// METHODS FOR BRICKs 
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.fillRect(x, y, w, h);
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.BLACK);
		g2.drawRect(x, y, w, h);

		if (totalHits > 0) {
			g2.setFont(new Font("Chalkboard", Font.PLAIN, 20));
			g2.drawString(totalHits + "", (int) x + 35, (int) y + 20);
		}
	}

//METHODS FOR BRICKs 
	public void drawAlien(Graphics2D g2) {
		g2.setColor(color);
		g2.fillRect(x, y, w, h);
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.BLACK);
		g2.drawRect((int) x, (int) y, w, h);
	}

	public void setRandomColor() {
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		color = new Color(r, g, b);

//update bricks that move independently 
	}

	public void update() {
//	move the brick
		x += dx;
		y += dy;

//bounce at the edges
		if (x + w > xmax || x < xmin) {
			dx = -dx;
			if (x < xmin)
				x = xmin;
			if (x + w > xmax)
				x = xmax - w;
		}
		if (y + h > ymax || y < ymin) {
			dy = -dy;
			if (y < ymin)
				y = ymin;
			if (y + h > ymax)
				y = ymax - h;
		}

	}

	public void updateAliens() {

		y += dy;
		x += dx;

	}

	public boolean willHitEdge() {

		System.out.println(dx);
		return x+dx < xmin || x+w+dx > xmax; 
	}

	public void updateforBrickBreaker() {
//	move the brick
		x += dx;
		y += dy;

//bounce at the edges
		if (x + w > xmax || x < xmin) {
			dx = -dx;
			if (x < xmin)
				x = xmin;
			if (x + w > xmax)
				x = xmax - w;
		}
		if (y <= ymin) {
			dy = -dy;
			if (y < ymin)
				y = ymin;

		}
	}

	public boolean isOffScreen() {
		return x < -w || x > xmax || y < -h || y > ymax;
	}

	public void drawAsCircle() {

	}

//Standard getter and setter methods and others 
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getTotalHits() {
		return totalHits;
	}

	public void setTotalHits(int totalHits) {
		this.totalHits = totalHits;
	}

	public String toString()

	{
		return "x=" + x + ", y=" + y + ", w=" + w + ", h=" + h + ", dx=" + dx + ", dy=" + dy + ", color= " + color + "";
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public int getUpKey() {
		return upKey;
	}

	public void setUpKey(int upKey) {
		this.upKey = upKey;
	}

	public int getDownKey() {
		return downKey;
	}

	public void setDownKey(int downKey) {
		this.downKey = downKey;
	}

	public int getLeftKey() {
		return leftKey;
	}

	public void setLeftKey(int leftKey) {
		this.leftKey = leftKey;
	}

	public int getRightKey() {
		return rightKey;
	}

	public void setRightKey(int rightKey) {
		this.rightKey = rightKey;
	}

	public int getYmin() {
		return ymin;
	}

	public void setYmin(int ymin) {
		this.ymin = ymin;
	}

	public int getYmax() {
		return ymax;
	}

	public void setYmax(int ymax) {
		this.ymax = ymax;
	}

	public int getXmax() {
		return xmax;
	}

	public void setXmax(int xmax) {
		this.xmax = xmax;
	}

	public void setXmin(int i) {
		// TODO Auto-generated method stub

	}

	public void keyWasPressed(int key) {

		{
			if (key == upKey)
				up = true;
			if (key == downKey)
				down = true;
			if (key == rightKey)
				right = true;
			if (key == leftKey)
				left = true;

			System.out.println(up);
		}

	}

	public void keyWasReleased(int key) {

		{
			if (key == upKey)
				up = false;
			if (key == downKey)
				down = false;
			if (key == rightKey)
				right = false;
			if (key == leftKey)
				left = false;
		}
	}

//Method to move bricks with a key press
	public void updateKeyMovement() {
		if (up)
			y -= dy;
		if (down)
			y += dy;
		if (left)
			x -= dx;
		if (right)
			x += dx;

	}

// METHODS FOR COLLISION 
	/**
	 * Determines the intersecting side for the brick in relation to another brick
	 * return true for a collision and false otherwise
	 */
	public boolean checkAndReactToCollisionWith(GameObject r) {
		int xm = x + w / 2; // use the center of the moving brick as a reference
		int ym = y + h / 2; // use the center of the moving brick as a reference

		int side = getSideForIntersection(r, xm, ym); // get the moving brick in relation to the other brick

		if (side == 0) // Is the moving brick above the other brick?
			return checkCollisionTopOfRectangle(r);
		else if (side == 1) // Is the moving brick to the right of the other brick?
			return checkCollisionRightSideOfRectangle(r);
		else if (side == 2) // Is the moving brick below the other brick?
			return checkCollisionBottomOfRectangle(r);
		else if (side == 3) // Is the moving brick to the left of the other brick?
			return checkCollisionLeftSideOfRectangle(r);

		return false;
	}

	/**
	 * Returns the side where a collision would occur if possible 0 = top 1 = right
	 * 2 = bottom 3 = left
	 */
	private int getSideForIntersection(GameObject r, int x1, int y1) {
		double slopeMajor = (double) r.h / r.w; // major diagonal slope
		double slopeMinor = (double) -r.h / r.w; // minor diagonal slope
		double bMajor = r.y - slopeMajor * r.x; // major diagonal y-intercept
		double bMinor = r.y - slopeMinor * (r.x + r.w); // minor diagonal y-intercept

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

	private boolean checkCollisionLeftSideOfRectangle(GameObject r) {
		boolean collision = false;

		if (y + h > r.y && y < r.y + r.h) {
			if (x + w > r.x) {
				dx = -Math.abs(dx);
				x = r.x - w;
				if (r.isWanderer)
					r.dx = -r.dx;
				if (x <= xmin) { // don't let the brick get bumped off the panel
//            x = xmin;
//            r.x = x + w;  //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
				}
				collision = true;
			}
		}
		return collision;
	}

	private boolean checkCollisionRightSideOfRectangle(GameObject r) {
		boolean collision = false;

		if (y + h > r.y && y < r.y + r.h) {
			if (x < r.x + r.w) {
				dx = Math.abs(dx);
				;
				x = r.x + r.w;
				if (r.isWanderer)
					r.dx = -r.dx; // in case the colliding brick is moving, stop it from overlapping this brick
										// the the edges of the panel
				if (x + w >= xmax) { // don't let the brick get bumped off the panel
					x = xmax - w;
//            r.x = x - r.w;  //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
				}
				collision = true;
			}
		}
		return collision;
	}

	private boolean checkCollisionBottomOfRectangle(GameObject r) {
		boolean collision = false;

		if (x + w > r.x && x < r.x + r.w) {
			if (y < r.y + r.h) {
				dy = Math.abs(dy);
				y = r.y + r.h;
				if (r.isWanderer)
					r.dy = -r.dy;
				if (y + h >= ymax) { // don't let the brick get bumped off the panel
					y = ymax - h;
					r.y = y - r.h; // in case the colliding brick is moving, stop it from overlapping this brick
										// the the edges of the panel
				}
				collision = true;
			}
		}
		return collision;
	}

	private boolean checkCollisionTopOfRectangle(GameObject r) {
		boolean collision = false;

		if (x + w > r.x && x < r.x + r.w) {
			if (y + h > r.y) {
				dy = -Math.abs(dy);
				y = r.y - h;
				if (r.isWanderer)
					r.dy = -r.dy;
				if (y <= ymin) { // don't let the brick get bumped off the panel
					y = ymin;
//           r.y = y + h;  //in case the colliding brick is moving, stop it from overlapping this brick the the edges of the panel
				}
				collision = true;
			}
		}
		return collision;
	}
}
