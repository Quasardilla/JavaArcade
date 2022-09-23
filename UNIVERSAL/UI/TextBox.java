package UNIVERSAL.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextBox extends UIElement implements KeyListener
{
    public double height, textToPromptOffset, promptToBackgroundOffset;
    public int promptFontSize, textFontSize, edgeCurve;
    public String prompt, text;
    public Color promptColor, textColor, backgroundcColor;
    public Font promptFont, textFont;
    
    public TextBox(double x, double y, double height, String prompt, Color promptColor, Font promptFont, int promptFontSize, String text, Color textColor, Font textFont, int textFontSize, double textToPromptOffset, double promptToBackgroundOffset, Color backgroundColor, int edgeCurve)
    {
        this.x = x;
        this.y = y;
        this.height = height;
        this.textToPromptOffset = textToPromptOffset;
        this.promptToBackgroundOffset = promptToBackgroundOffset;
        this.promptFontSize = promptFontSize;
        this.textFontSize = textFontSize;
        this.edgeCurve = edgeCurve;
        this.prompt = prompt;
        this.text = text;
        this.promptColor = promptColor;
        this.textColor = textColor;
        this.backgroundcColor = backgroundColor;
        this.promptFont = promptFont;
        this.promptFont = new Font(this.promptFont.getFontName(), Font.PLAIN, promptFontSize);
        this.textFont = textFont;
        this.textFont = new Font(this.textFont.getFontName(), Font.PLAIN, textFontSize);
    }

    @Override
    public void drawElement() 
    {
        //draw background
        g2.setColor(backgroundcColor);
        
        promptFont = new Font(promptFont.getFontName(), Font.PLAIN, (int) promptFontSize);
        g2.setFont(promptFont);
        int pw = g2.getFontMetrics().stringWidth(prompt);
        
        textFont = new Font(textFont.getFontName(), Font.PLAIN, (int) textFontSize);
        g2.setFont(textFont);
        int tw = g2.getFontMetrics().stringWidth(text);
                
        int w = (int) (promptToBackgroundOffset + textToPromptOffset + promptToBackgroundOffset + pw + tw);

        g2.fillRoundRect((int) x, (int) y, w, (int) height, edgeCurve, edgeCurve);

        //draw prompt
        g2.setColor(promptColor);
        promptFont = new Font(promptFont.getFontName(), Font.PLAIN, (int) promptFontSize);
        g2.setFont(promptFont);
        g2.drawString(prompt, (int) (x + promptToBackgroundOffset), (int) (y + (height / 2) + (promptFontSize / 3)));

        //draw text
        g2.setColor(textColor);
        textFont = new Font(textFont.getFontName(), Font.PLAIN, (int) textFontSize);
        g2.setFont(textFont);
        g2.drawString(text, (int) (x + promptToBackgroundOffset + g2.getFontMetrics().stringWidth(prompt) + textToPromptOffset), (int) (y + (height / 2) + (textFontSize / 3)));
        
    }
    

    @Override
    public void keyTyped(KeyEvent e) 
    {
        
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
