import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {
	public int x;
	public int y;
	public int w;
	public int h;
	public int dy;

	public boolean left;
	public boolean right;
	
	public int score = 0;
	
	public Color insideColor;

	public Player(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void drawMe(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.drawImage(Game.paddle, x, y, w, h, null);
		g2.drawRect(x, y, w, h);
		g2.setColor(insideColor);
		g2.fillRect(x, y, w, h);
	}
	
	public void physics(boolean left, boolean right) {
		
		y += dy;

		int speed = 7;
		
		dy = 0;
		
		if(left)
			dy = -speed;
		else if(right)
			dy = speed;

		
		if(y < 0) 
			y = 0;
		
		if(y + h > Game.PREF_H)
			y = Game.PREF_H - h;
		
	}
	
	public Rectangle getHitbox() {
		return new Rectangle(x,y,w,h);
	}
}
