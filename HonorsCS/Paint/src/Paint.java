import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//Honors Computer Science - Mr. Uhl
//Program description: A template class for creating graphical applications

public class Paint extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	
	static JFrame frame = new JFrame("My First Panel!");
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice device = env.getDefaultScreenDevice();
	
	// Variables for the class
	private static final long serialVersionUID = 1L;
	public static final int PREF_W = 800;
	public static final int PREF_H = 600;
	
	public static int pointerX;
	public static int pointerY;
	
	public static boolean fullscreen = false;
	public static boolean showMenu = true;
	
	public static Brush brush = new Brush(5, Color.BLACK);
	public ArrayList<DrawnPoint> points = new ArrayList<DrawnPoint>();
	
	public RedSlider redSlider = new RedSlider();
	public GreenSlider greenSlider = new GreenSlider();
	public BlueSlider blueSlider = new BlueSlider();
	public StrokeSlider strokeSlider = new StrokeSlider();
	
	// Class constructor
	public Paint() {
		this.setFocusable(true);
		this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
	}

	// The method used to add graphical images to the panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		
		brush.color = new Color(redSlider.val, greenSlider.val, blueSlider.val);
		brush.size = strokeSlider.val;
		

		for(int i = 0; i < points.size() - 1; i++) {
			DrawnPoint pt = points.get(i);
			DrawnPoint next = points.get(i + 1);
			
			g2.setColor(next.getBrush().getColor());
			g2.setStroke(next.getBrush().getStroke());
			
			if(i < points.size() - 1 && !points.get(i).last)
				g2.drawLine(pt.x, pt.y, next.x, next.y);
		}
		
		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.BLACK);
		g2.drawOval(pointerX - brush.size / 2, pointerY - brush.size / 2, brush.size, brush.size);
		
		if(showMenu) {
			
			g2.setColor(Color.GRAY);
			g2.fillRect(0, 0, 280, 150);
			g2.setColor(Color.BLACK);
			g2.drawRect(0, 0, 280, 150);

			redSlider.draw(g2);
			greenSlider.draw(g2);
			blueSlider.draw(g2);
			strokeSlider.draw(g2);
		}
	}

	/** ******* METHODS FOR INITIALLY CREATING THE JFRAME AND JPANEL *********/

	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}

	public static void createAndShowGUI() {
		JPanel gamePanel = new Paint();

		frame.getContentPane().add(gamePanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	boolean draggingPointer = false;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(redSlider.beingDragged(e)) {
			redSlider.pointerX = e.getX();
			redSlider.draggingPointer = true;
			redSlider.checkBounds();
		} else if(greenSlider.beingDragged(e)) {
			greenSlider.pointerX = e.getX();
			greenSlider.draggingPointer = true;
			greenSlider.checkBounds();
		} else if(blueSlider.beingDragged(e)) {
			blueSlider.pointerX = e.getX();
			blueSlider.draggingPointer = true;
			blueSlider.checkBounds();
		} else if(strokeSlider.beingDragged(e)) {
			strokeSlider.pointerX = e.getX();
			strokeSlider.draggingPointer = true;
			strokeSlider.checkBounds();
		} else {
			points.add(new DrawnPoint(new Point(e.getX(), e.getY()), brush.clone()));
		}
		pointerX = e.getX();
		pointerY = e.getY();
		repaint();
	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
		points.add(new DrawnPoint(new Point(e.getX(), e.getY()), brush.clone(), true));
		repaint();
		redSlider.draggingPointer = false;
		greenSlider.draggingPointer = false;
		blueSlider.draggingPointer = false;
		strokeSlider.draggingPointer = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		pointerX = e.getX();
		pointerY = e.getY();
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		repaint();
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			showMenu = !showMenu;
		
		else if(e.getKeyCode() == KeyEvent.VK_UP) {
			
			brush.size += 3;
			
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN && brush.size >= 3) {
			
			brush.size -= 3;
			
		} else if(e.getKeyCode() == KeyEvent.VK_C && !e.isMetaDown()) {
			
			Color c = JColorChooser.showDialog(null, "Pick a color", brush.color);
			if(c != null) 
				brush.color = c;
			
			redSlider.val = brush.color.getRed();
			greenSlider.val = brush.color.getGreen();
			blueSlider.val = brush.color.getBlue();
			
			redSlider.pointerX = 13 + redSlider.val;
			greenSlider.pointerX = 13 + greenSlider.val; 
			blueSlider.pointerX = 13 + blueSlider.val;
			
			repaint();
			
		} else if(e.getKeyCode() == KeyEvent.VK_F) {
			
			if(!fullscreen)
				device.setFullScreenWindow(frame);
			else
				device.setFullScreenWindow(null);
			
			fullscreen = !fullscreen;
			frame.setVisible(true);
		
		} else if(e.isMetaDown()) {
			
			if(e.getKeyCode() == KeyEvent.VK_Q) {
				System.exit(0);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_C) {
				points.clear();
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}