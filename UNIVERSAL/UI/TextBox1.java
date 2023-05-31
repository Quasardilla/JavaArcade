package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextBox extends UIElement implements KeyListener
{
    public int width, height;
    public Color backgroundColor, fontColor, defaultTextColor;
    public Font font;
    public String defaultText;
    public boolean isSelected;
    public String text;
    
    public TextBox(double x, double y, int width, int height, Color backgroundColor, Font font, Color fontColor, String defaultText, Color defaultTextColor)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.font = font;
        this.fontColor = fontColor;
        this.defaultText = defaultText;
        this.defaultTextColor = defaultTextColor;
    }

    @Override
    public void drawElement() 
    {
        //draw background
        g2.setColor(backgroundColor);
        g2.fillRect((int) x, (int) y, width, height);
        
        //draw text
        g2.setColor(defaultTextColor);
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics();
        
        if(text == null || text.equals(""))
            g2.drawString(defaultText, (int) x + 5, (int) y + (height / 2) + (metrics.getHeight() / 2) - 5);
        else {
            g2.setColor(fontColor);
            g2.drawString(text, (int) x + 5, (int) y + (height / 2) + (metrics.getHeight() / 2) - 5);
        }
        
    }
    
    public void mouseClick(double mousex, double mousey)
    {
        if (mousex > x && mousex < x + width && mousey > y && mousey < y + height) 
            isSelected = true;
        else 
            isSelected = false;
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        // if(e.getKeyCode() == KeyEvent.VK_LEFT)
        // if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        
        if(e.isActionKey() || e.getKeyChar() == KeyEvent.CHAR_UNDEFINED)
            return;

            
        if(isSelected) {
            if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                //Split the if statement so it would absorb backspace and delete, even if the text is empty
                if(text.length() > 0)
                    text = text.substring(0, text.length() - 1);
            }
            else {
                text += e.getKeyChar();
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        
    }
}
