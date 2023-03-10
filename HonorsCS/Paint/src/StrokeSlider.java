import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class StrokeSlider {
	public int pointerX = 17;
	public int pointerY = 120;
	public int pointerR = 25;
	
	public int minX = 20;
	public int maxX = 120;
	
	int maxVal = 100;
	
	public int val = 10;
	
	public Color dragColor;
	
	public boolean draggingPointer;
	
	public StrokeSlider() {
		
	}
	
	boolean intersectsCircle(int mouseX, int mouseY, int cX, int cY, int cR) {
		return dist(mouseX, mouseY, cX, cY) <= cR;
	}
	double dist(int x, int y, int w, int h) {
		return Math.sqrt(Math.pow(w - x, 2) + Math.pow(h - y, 2));
	}
	
	public void draw(Graphics2D g2) {
		Color brushColor = Paint.brush.color;
		
        g2.setColor(brushColor);
		g2.fillRect(minX, pointerY - pointerR / 2 + 3, maxX - minX, pointerR - 6);
		g2.setColor(Color.BLACK);
		g2.drawRect(minX, pointerY - pointerR / 2 + 3, maxX - minX, pointerR - 6);
		
		g2.setColor(brushColor);
		g2.fillOval(pointerX - pointerR / 2, pointerY - pointerR / 2 - 1, pointerR, pointerR);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(val));
		g2.drawOval(pointerX - pointerR / 2, pointerY - pointerR / 2 - 1, pointerR, pointerR);
	}
	
	public boolean beingDragged(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		return Paint.showMenu && (draggingPointer || intersectsCircle(mouseX, mouseY, pointerX, pointerY, pointerR));
	}
	
	public void checkBounds() {
		val = pointerX - minX;
		if(val < 0) val = 0;
		if(val > maxVal) val = maxVal;
		if(pointerX > maxX)
			pointerX = maxX;
		if(pointerX < minX)
			pointerX = minX;
	}
}
