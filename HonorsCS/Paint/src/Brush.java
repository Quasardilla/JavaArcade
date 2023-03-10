import java.awt.BasicStroke;
import java.awt.Color;

public class Brush {
	public int size;
	public Color color;
	
	Brush(int size, Color color) {
		this.size = size;
		this.color = color;
	}
	
	public Brush getBrush() {
		return this;
	}
	
	public BasicStroke getStroke() {
		return new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	}
	
	public Color getColor() {
		return color;
	}
	
	public Brush clone() {
		return new Brush(size, color);
	}

}
