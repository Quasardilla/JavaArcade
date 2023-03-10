import java.awt.Point;

public class DrawnPoint {
	public Point location;
	public int x;
	public int y;
	public Brush brush;
	public boolean last = false;
	
	DrawnPoint(Point location, Brush brush, boolean last) {
		this.location = location;
		this.x = (int) location.getX();
		this.y = (int) location.getY();
		this.brush = brush;
		this.last = last;
	}
	
	DrawnPoint(Point location, Brush brush) {
		this.location = location;
		this.x = (int) location.getX();
		this.y = (int) location.getY();
		this.brush = brush;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public Brush getBrush() {
		return brush;
	}
}
