package PictureLab;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

//Mr. Uhl
//Program description: Starter code for the Picture Lab
//Dec 12, 2022

public class Picture
{
   private BufferedImage pic;
   private Pixel[][] pix;
   
   public Picture(String filename)
   {
      //THE IMAGE FROM THE FILE IS LOADED AS A BUFFERED IMAGE
      try{
         File imageFile = new File(filename);
         pic = ImageIO.read(imageFile);
      } catch (IOException e){e.printStackTrace();}

      pix = getPixels2D(pic);
   }
   
   public Picture()
   {
      pic = createDefaultTitleImage("UPic Picture Lab~~No images found~in src folder.");
      pix = getPixels2D(pic);
   }
   
   public static Pixel[][] getPixels2D(BufferedImage image)
   {
      final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
      final int width = image.getWidth();
      final int height = image.getHeight();
      final boolean hasAlphaChannel = image.getAlphaRaster() != null;

      Pixel[][] result = new Pixel[height][width];
      if (hasAlphaChannel) {
         final int pixelLength = 4;
         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int r = 0; int g = 0; int b = 0; int a = 0;
            a = (((int) pixels[pixel] & 0xff));     // alpha
            b = ((int) pixels[pixel + 1] & 0xff);   // blue
            g = (((int) pixels[pixel + 2] & 0xff)); // green
            r = (((int) pixels[pixel + 3] & 0xff)); // red
            result[row][col] = new Pixel(r, g, b, a);
            col++;
            if (col == width) {
               col = 0;
               row++;
            }
         }
      } else {
         final int pixelLength = 3;
         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int r = 0; int g = 0; int b = 0; int a;
            a = 255;                                // alpha
            b = ((int) pixels[pixel] & 0xff);       // blue
            g = (((int) pixels[pixel + 1] & 0xff)); // green
            r = (((int) pixels[pixel + 2] & 0xff)); // red
            result[row][col] = new Pixel(r, g, b, a);
            col++;
            if (col == width) {
               col = 0;
               row++;
            }
         }
      }
      
      return result;
   }
   
   public void drawPixels(Graphics2D g2, int x, int y)
   {
      for(int r = 0; r < pix.length; r++)
         for(int c = 0; c < pix[r].length; c++)
         {
            g2.setColor(pix[r][c].getColor());
            g2.fillRect(c+x, r+y, 1, 1);
         }
   }
   
   public int getWidth()
   {
      return pix[0].length;
   }
   
   public int getHeight()
   {
      return pix.length;
   }
   
   public void restore()
   {
      pix = getPixels2D(pic);
   }
   
   public Pixel getPixel(int row, int col)
   {
      return pix[row][col];
   }
   
   public BufferedImage createDefaultTitleImage(String message)
   {
      BufferedImage titleImage = null;
      int width = 400;
      int height = 500;
      //Create blank buffered image
      titleImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
      
      //Add the text to the newly created buffered image
      Graphics2D g2 = titleImage.createGraphics();
      g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON));
      g2.setColor(Color.WHITE);
      g2.fillRect(0, 0, width, height);
      g2.setColor(new Color(50, 0, 150));
      int fontSize = 40;
      g2.setFont(new Font("Cooper Black", Font.BOLD, fontSize));
//      int messageWidth = g2.getFontMetrics().stringWidth(message);
//      while(messageWidth > width - 40) {
//         fontSize--;
//         g2.setFont(new Font("Cooper Black", Font.BOLD, fontSize));
//         messageWidth = g2.getFontMetrics().stringWidth(message);
//      }
      String[] messageList = message.split("~");
      int gap = 42;
      int heightOfMessage = messageList.length * gap;
      int startY = height/2 - heightOfMessage/2 + 32;
      for(String s:messageList) {
         g2.drawString(s, 20, startY);
         startY += gap;
      }
      return titleImage;
   }
   
   public void savePic()
   {
      String filename = JOptionPane.showInputDialog("Enter the filename to save as:");
      filename += ".png";
      try{
         int width = pix[0].length;
         int height = pix.length;
         System.out.println(width + ", " + height);
         BufferedImage img = new BufferedImage( 
            width, height, BufferedImage.TYPE_INT_RGB);

         File f = new File(filename);

         for(int x = 0; x < height; x++){
            for(int y = 0; y < width; y++){
               int rgb = pix[x][y].getColor().getRGB();
               img.setRGB(y, x, rgb);
            }
         }
         ImageIO.write(img, "png", f);
      }
      catch(Exception ex){ex.printStackTrace();}
   }
   
   //ALL THE PICTURE LAB METHODS SHOULD BE WRITTEN BELOW

//   public void photoNegative()
//   {
//      
//   }
  
}