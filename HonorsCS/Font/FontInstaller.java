package Font;

import java.io.File;
import java.io.IOException;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.awt.FontFormatException;

public class FontInstaller {

    private static File file = new File("/");

    public static void installFont() 
    {
        for (int i = 0; i < file.list().length; i++)
        {
            if(file.list()[i].equals("FontInstaller.java"))
            {}
            else
            {
                File fontFile = new File("Font/" + file.list()[i]);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                try {
                    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
                } catch (FontFormatException e) {} catch (IOException e) {}
            }
        }
    }
    public static void installFont(String fontfilename) 
    {
        File fontFile = new File("Font/" + fontfilename);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
        } catch (FontFormatException e) {} catch (IOException e) {}
    }
    
    public static void printAll()
    {
        for(String s : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
        System.out.println(s);
    }
}
