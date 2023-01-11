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

import java.awt.image.Kernel;
import java.awt.image.ConvolveOp;
import java.awt.image.BufferedImageOp;

import java.awt.Point;
import java.util.ArrayList;

// import Intro.Exploring2DArrays.Exploring2DArrays;

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

   public void photoNegative()
   {
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            pix[i][j].setNegative();
         }
      }
   }
   
   public void grayscale() 
   {
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            pix[i][j].setToGray();
         }
      }
      
   }

   public void zeroRed() 
   {
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            pix[i][j].setRed(0);
         }
      }
   }

   public void zeroGreen() 
   {
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            pix[i][j].setGreen(0);
         }
      }
   }

   public void zeroBlue() 
   {
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            pix[i][j].setBlue(0);
         }
      }
   }

   public void mirrorRightToLeft() 
   {
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length/2; j++)
         {
            pix[i][pix[i].length-j-1].setPixel(pix[i][j]);
         }
      }
   }

   public void mirrorLeftToRight() 
   {
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length/2; j++)
         {
            pix[i][j].setPixel(pix[i][pix[i].length-j-1]);
         }
      }
   }
   
   public void mirrorTopToBottom() 
   {
      for (int i = 0; i < pix.length/2; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            pix[pix.length-i-1][j].setPixel(pix[i][j]);
         }
      }
   }

   public void mirrorBottomToTop() 
   {
      for (int i = 0; i < pix.length/2; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            pix[i][j].setPixel(pix[pix.length-i-1][j]);
         }
      }
   }

   public void flipHorizontal() 
   {
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length/2; j++)
         {
            Pixel temp = pix[i][pix[0].length-j-1];
            pix[i][pix[0].length-j-1] = pix[i][j];
            pix[i][j] = temp;
         }
      }
   }

   public void flipVertical() 
   {
      for (int i = 0; i < pix.length/2; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            Pixel temp = pix[pix.length-i-1][j];
            pix[pix.length-i-1][j] = pix[i][j];
            pix[i][j] = temp;
         }
      }
   }

   public void edgeDetection(int tolerance) 
   {
      Pixel[][] temp = new Pixel[pix.length][pix[0].length];

      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            double north_diff = 0;
            double south_diff = 0;
            double east_diff = 0;
            double west_diff = 0;

            if (i > 0)
            {
               north_diff = pix[i][j].colorDistance(pix[i-1][j].getColor());
            }
            if (i < pix.length-1)
            {
               south_diff = pix[i][j].colorDistance(pix[i+1][j].getColor());
            }

            if (j > 0)
            {
               east_diff = pix[i][j].colorDistance(pix[i][j-1].getColor());
            }
            if (j < pix[i].length-1)
            {
               west_diff = pix[i][j].colorDistance(pix[i][j+1].getColor());
            }

            if (north_diff > tolerance || south_diff > tolerance || east_diff > tolerance || west_diff > tolerance)
            {
               temp[i][j] = new Pixel(0, 0, 0);
            }
            else
            {
               temp[i][j] = new Pixel(255, 255, 255);
            }

         }
      }

      pix = temp;
   }

   public void blur(int kernel_size, double standard_deviation) 
   {
      //Converts the magnitude into an odd side length
      int sideLength = 2 * kernel_size + 1;

      Pixel[][] newPix = new Pixel[pix.length][pix[0].length];
      Pixel[][] temp = new Pixel[sideLength][sideLength];

      double[][] weightMap = Pixel.getGaussianMap(sideLength, sideLength, standard_deviation);
      
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            for(int k = 0; k < sideLength; k++)
               for(int l = 0; l < sideLength; l++)
                  if(i + k - sideLength / 2 < 0 || j + l - sideLength / 2 < 0 || i + k - sideLength / 2 >= pix.length || j + l - sideLength / 2 >= pix[i].length)
                     temp[k][l] = null;
                  else
                     temp[k][l] = pix[i + k - sideLength / 2][j + l - sideLength / 2];

            //Weighted average the pixels in the temp array
            newPix[i][j] = Pixel.averageWeightedPixels(temp, pix[i][j], standard_deviation, weightMap);
         }
      }

      pix = newPix;
   }

   public void encodeUsingRed(Picture pic)
   {
      BufferedImage buffImg = pic.pic;
      
      for(int i = 0; i < Math.min(pic.pic.getHeight(), pix.length); i++)
         for(int j = 0; j < Math.min(pic.pic.getWidth(), pix[0].length); j++)
            {
               Color clr = new Color(buffImg.getRGB(j, i));
               if(clr.getRed() < 127 || clr.getGreen() < 127 || clr.getBlue() < 127)
                  pix[i][j].setOdd(Color.RED);
            }
   }
   
   public void encodeUsingRed(String text)
   {
      BufferedImage buffImg = new BufferedImage(pix[0].length, pix.length, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2 = buffImg.createGraphics();
      g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
      g2.drawString(text, 0, 50);

      for(int i = 0; i < buffImg.getHeight(); i++)
         for(int j = 0; j < buffImg.getWidth(); j++)
            {
               Color clr = new Color(buffImg.getRGB(j, i));
               if(clr.equals(Color.BLACK))
                  pix[i][j].setOdd(Color.RED);
            }
   }

   public void encodeUsingGreen(Picture pic)
   {
      BufferedImage buffImg = pic.pic;
      
      for(int i = 0; i < Math.min(pic.pic.getHeight(), pix.length); i++)
         for(int j = 0; j < Math.min(pic.pic.getWidth(), pix[0].length); j++)
            {
               Color clr = new Color(buffImg.getRGB(j, i));
               if(clr.getRed() < 127 || clr.getGreen() < 127 || clr.getBlue() < 127)
                  pix[i][j].setOdd(Color.GREEN);
            }
   }
   
   public void encodeUsingGreen(String text)
   {
      BufferedImage buffImg = new BufferedImage(pix[0].length, pix.length, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2 = buffImg.createGraphics();
      g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
      g2.drawString(text, 0, 150);

      for(int i = 0; i < buffImg.getHeight(); i++)
         for(int j = 0; j < buffImg.getWidth(); j++)
            {
               Color clr = new Color(buffImg.getRGB(j, i));
               if(!clr.equals(Color.BLACK))
                  pix[i][j].setOdd(Color.GREEN);
            }
   }

   public void encodeUsingBlue(Picture pic)
   {
      BufferedImage buffImg = pic.pic;
      
      for(int i = 0; i < Math.min(pic.pic.getHeight(), pix.length); i++)
         for(int j = 0; j < Math.min(pic.pic.getWidth(), pix[0].length); j++)
            {
               Color clr = new Color(buffImg.getRGB(j, i));
               if(!clr.equals(Color.BLACK))
                  pix[i][j].setOdd(Color.BLUE);
            }
   }
   
   public void encodeUsingBlue(String text)
   {
      BufferedImage buffImg = new BufferedImage(pix[0].length, pix.length, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2 = buffImg.createGraphics();
      g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
      g2.drawString(text, 0, 250);

      for(int i = 0; i < buffImg.getHeight(); i++)
         for(int j = 0; j < buffImg.getWidth(); j++)
            {
               Color clr = new Color(buffImg.getRGB(j, i));
               if(!clr.equals(Color.BLACK))
                  pix[i][j].setOdd(Color.BLUE);
            }
   }

   public void encodeUsingRGB(Picture pic)
   {
      for(int i = 0; i < Math.min(pic.pic.getHeight(), pix.length); i++)
         for(int j = 0; j < Math.min(pic.pic.getWidth(), pix[0].length); j++)
            {
               BufferedImage buffImg = pic.pic;
               Pixel clr = new Pixel(new Color(buffImg.getRGB(j, i)));
               clr.setToGray();

               if (clr.getRed() > 200)
                  pix[i][j].setOdd(Color.RED);
               else if (clr.getGreen() > 100)
                  pix[i][j].setOdd(Color.GREEN);
               else
                  pix[i][j].setOdd(Color.BLUE);
            }
   }
   
   public void setAllEven()
   {
      for(int i = 0; i < pix.length; i++)
         for(int j = 0; j < pix[i].length; j++)
            pix[i][j].setEven();
   }

   public Pixel[][] decodeReturnRed() 
   {
      Pixel[][] temp = new Pixel[pix.length][pix[0].length];

      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            if (pix[i][j].getRed() % 2 == 1)
            {
               temp[i][j] = new Pixel(Color.BLACK);
            }
            else
            {
               temp[i][j] = new Pixel(Color.WHITE);
            }

         }
      }

      return temp;
   }

   public Pixel[][] decodeReturnGreen() 
   {
      Pixel[][] temp = new Pixel[pix.length][pix[0].length];

      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            if (pix[i][j].getGreen() % 2 == 1)
            {
               temp[i][j] = new Pixel(Color.BLACK);
            }
            else
            {
               temp[i][j] = new Pixel(Color.WHITE);
            }

         }
      }
      return temp;
   }


   public Pixel[][] decodeReturnBlue() 
   {
      Pixel[][] temp = new Pixel[pix.length][pix[0].length];

      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            if (pix[i][j].getBlue() % 2 == 1)
            {
               temp[i][j] = new Pixel(Color.BLACK);
            }
            else
            {
               temp[i][j] = new Pixel(Color.WHITE);
            }

         }
      }

      return temp;
   }

   public void decodeUsingRed()
   {
      pix = decodeReturnRed();
   }

   public void decodeUsingGreen()
   {
      pix = decodeReturnGreen();
   }

   public void decodeUsingBlue()
   {
      pix = decodeReturnBlue();
   }

   public void decodeUsingShades()
   {
      Pixel[][][] rgbList = {decodeReturnRed(), decodeReturnGreen(), decodeReturnBlue()};
      Pixel[][] returnList = new Pixel[pix.length][pix[0].length];

      for(int i = 0; i < returnList.length; i++)
         for(int j = 0; j < returnList[i].length; j++)
            returnList[i][j] = new Pixel(255, 255, 255);

      for(int h = 0; h < 3; h++)
      {
         for (int i = 0; i < pix.length; i++)
         {
            for (int j = 0; j < pix[i].length; j++)
            {
               if(h == 0)
                  if(rgbList[h][i][j].getColor().equals(Color.BLACK))
                     returnList[i][j].setColor(Color.LIGHT_GRAY);
               if(h == 1)
                  if(rgbList[h][i][j].getColor().equals(Color.BLACK))
                     returnList[i][j].setColor(Color.GRAY);
               if(h == 2)
                  if(rgbList[h][i][j].getColor().equals(Color.BLACK))
                     returnList[i][j].setColor(Color.BLACK);
            }
         }
      }


      pix = returnList;
      
   }

   public void glitch() 
   {
      embossBad();
      // for (int i = 0; i < (int) (Math.random()*5)+1; i++)
         slicer((int) (Math.random()*5)+3, (int) ((Math.random()*20) * (pix.length / 75)), (int) ((Math.random()*20) * (pix[0].length / 75)), 20);
      scanLines((int) (Math.random()*(pix[0].length / 250))+(pix[0].length / 100), Math.random()*0.3+0.1);
      colorSplit((Math.random()*0.5)+0.4, (int) (Math.random()*(pix.length / 16)), (int) (Math.random()*(pix[0].length / 16)));
   }

   private void slicer(int rect_count, int min_size, int max_size, int move) 
   {
      // int opacity = 1;
      
      Pixel[][] offset = pix;
      
      for (int count = 0; count < rect_count; count++)
      {
         int x = (int) (Math.random()*(pix[0].length-1));
         int y = (int) (Math.random()*(pix.length-1));
         int w = (int) (Math.random()*(max_size-min_size) + min_size);
         int h = (int) (Math.random()*(max_size-min_size) + min_size);
         int rise = (int) ((Math.random()-0.5)*(move*2));
         int run = (int) ((Math.random()-0.5)*(move*2));
         // int rise = -20;
         // int run = -20;

         if (x+w > pix[0].length-1)
            w = pix[0].length-1-x;
         if (y+h > pix.length-1)
            h = pix.length-1-y;

         for (int i = y; i < h+y; i++)
         {
            for (int j = x; j < w+x; j++)
            {
               if (i+rise < pix.length-1 && j+run < pix[i].length-1 && i+rise > 0 && j+run > 0)//if its not out of bounds
               {
                  offset[i][j] = new Pixel(pix[i+rise][j+run].getRed(), pix[i+rise][j+run].getGreen(), pix[i+rise][j+run].getBlue());
               }
               // if (i-rise > 0 && j-run > 0)//if its not out of bounds
               // {
               //    offset[i-rise][j-run] = new Pixel(0, pix[i][j].getGreen(), 0);
               // }
               if (i > pix.length-rise && j > pix[i].length-run)//get missed pixels
               {
                  offset[i][j] = new Pixel(pix[pix.length-rise][pix[i].length-run].getRed(), pix[pix.length-rise][pix[i].length-run].getGreen(), pix[pix.length-rise][pix[i].length-run].getBlue());
               }
            }
         }
      }


      pix = offset;
   }

   /**
    * 
    * @param size
    *       - size of the scan lines (positive integer)
    * @param opacity
    *       - opacity of the scan lines (0 to 1)
    */
   private void scanLines(int size, double opacity) 
   {
      if(size < 1)
         throw new IllegalArgumentException("Size must be a positive integer");
  
      if(opacity > 255 || opacity < 0)
         throw new IllegalArgumentException("Opacity must be between 0 and 1");

      size += 1;

      Pixel[][] returnList = new Pixel[pix.length][pix[0].length];

      for(int i = 0; i < returnList.length; i++)
         for(int j = 0; j < returnList[i].length; j++)
               {
                  if(pix[i][j] !=null)
                  {
                     returnList[i][j] = new Pixel();
                     double scanOpacity = ((Math.cos((2.0 * Math.PI* i)/size)*opacity)+opacity)/2.0;
                     Pixel scanPixel = new Pixel(0, 0, 0, (int) (scanOpacity * 255));

                     returnList[i][j].setColor((int) (pix[i][j].getRed() * (1 - scanOpacity) + scanPixel.getRed() * scanOpacity),
                                       (int) (pix[i][j].getGreen() * (1 - scanOpacity) + scanPixel.getGreen() * scanOpacity),
                                       (int) (pix[i][j].getBlue() * (1 - scanOpacity) + scanPixel.getBlue() * scanOpacity));
                  }

               }

      pix = returnList;
   }

   private void colorSplit(double opacity, int rise, int run) 
   {
      Pixel[][] reds = new Pixel[pix.length][pix[0].length];
      Pixel[][] greens = new Pixel[pix.length][pix[0].length];

      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            if (i+rise < pix.length-1 && j+run < pix[i].length-1)//if its not out of bounds
            {
               reds[i+rise][j+run] = new Pixel(pix[i][j].getRed(), 0, 0);
            }
            if (i-rise > 0 && j-run > 0)//if its not out of bounds
            {
               greens[i-rise][j-run] = new Pixel(0, pix[i][j].getGreen(), 0);
            }
            // if (i > pix.length-rise && j > pix[i].length-run)//get missed pixels
            // {
            //    reds[i][j] = new Pixel(pix[pix.length-rise][pix[i].length-run].getRed(), 0, 0);
            //    greens[i][j] = new Pixel(0, pix[pix.length-rise][pix[i].length-run].getGreen(), 0);
            // }
            // if (i < rise)
            // {
            //    reds[i][j] = new Pixel(pix[rise][j].getRed(), 0, 0);
            // }
            // if (i > pix.length-rise)
            // {
            //    greens[i][j] = new Pixel(0, pix[rise][j].getGreen(), 0);
            // }
            // if (j < run)
            // {
            //    reds[i][j] = new Pixel(pix[i][run].getRed(), 0, 0);
            // }
            // if (j > pix[i].length-run)
            // {
            //    greens[i][j] = new Pixel(0, pix[i][run].getGreen(), 0);
         
            // }
      
         }
      }

      //add reds and greens to pix
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            if (reds[i][j] != null)
            {
               pix[i][j].setRed((int)(pix[i][j].getRed() * (1-opacity) + reds[i][j].getRed() * opacity));
            }
            if (greens[i][j] != null)
            {
               pix[i][j].setGreen((int)(pix[i][j].getGreen() * (1-opacity) + greens[i][j].getGreen() * opacity));
            }
         }
      }

   }

   public void pixelate(int size) 
   {
      Pixel[][] temp = pix;
      for (int i = 0; i < pix.length; i+=size)
      {
         for (int j = 0; j < pix[i].length; j+=size)
         {
            int s = size*size;
            Pixel[][] avg = new Pixel[size][size];
            for (int y = i; y < i+size; y++)
            {
               for (int x = j; x < j+size; x++)
               {
                  if (y >= pix.length || x >= pix[y].length)
                  {
                     s--;
                     continue;
                  }
                  avg[y-i][x-j] = pix[y][x];
               }
            }
            for (int y = i; y < i+size; y++)
            {
               for (int x = j; x < j+size; x++)
               {
                  if (y >= pix.length || x >= pix[y].length)
                  {
                     continue;
                  }
                  temp[y][x] = Pixel.averagePixels(avg, s);
               }
            }
         }
      }

      pix = temp;
   }

   public void sobel() 
   {
      //im using buffered image becasue its easier to deal with int rgb color values
      //i got confused again because this takes x, y and i was using y, x T_T
      BufferedImage image = new BufferedImage(pix[0].length, pix.length, BufferedImage.TYPE_INT_RGB);

      //convert pix to image
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            image.setRGB(j, i, pix[i][j].getColor().getRGB());//this uses xy on setrgb, but yx on getrgb FML
         }
      }

      int width = image.getWidth();
      int height = image.getHeight();

      int[][] edgeColors = new int[height][width];
      int maxGradient = -1;

      //dont do edges (i dont wanna make if statements to im just skipping them)
      for (int i = 1; i < height - 1; i++) 
      {
         for (int j = 1; j < width - 1; j++) 
         {
            //get surrounding pixels (nums in var names are relative coords)
            int val00 = getGrayScale(image.getRGB(j - 1, i - 1));
            int val01 = getGrayScale(image.getRGB(j - 1, i));
            int val02 = getGrayScale(image.getRGB(j - 1, i + 1));

            int val10 = getGrayScale(image.getRGB(j, i - 1));
            int val11 = getGrayScale(image.getRGB(j, i));
            int val12 = getGrayScale(image.getRGB(j, i + 1));

            int val20 = getGrayScale(image.getRGB(j + 1, i - 1));
            int val21 = getGrayScale(image.getRGB(j + 1, i));
            int val22 = getGrayScale(image.getRGB(j + 1, i + 1));

            //matrix shenaniganry that idfk how it works
            //yoinked matrixes from https://en.wikipedia.org/wiki/Sobel_operator
            int gx =  ((-1 * val00) + (0 * val01) + (1 * val02)) 
                  + ((-2 * val10) + (0 * val11) + (2 * val12))
                  + ((-1 * val20) + (0 * val21) + (1 * val22));

            int gy =  ((-1 * val00) + (-2 * val01) + (-1 * val02))
                  + ((0 * val10) + (0 * val11) + (0 * val12))
                  + ((1 * val20) + (2 * val21) + (1 * val22));

            double gval = Math.sqrt((gx * gx) + (gy * gy));
            int g = (int) gval;

            //get largest gradient to scale down everything at the end
            if(maxGradient < g) {
               maxGradient = g;
            }

            edgeColors[i][j] = g;
         }
      }

      //scale down all the gradients to fit within 255
      double scale = 255.0 / maxGradient;

      //fix edge colors and draw them in
      for (int i = 0; i < height; i++) 
      {
         for (int j = 0; j < width; j++) 
         {
            int edgeColor = edgeColors[i][j];
            edgeColor = (int)(edgeColor * scale);
            
            //set alpha to opaque i fkin hate int rgb unpacking why is it sometimes ARGB and other RGBA T_T
            edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

            // Pixel p = new Pixel(new Color(edgeColor));
            // p.setNegative();
            // pix[i][j] = p;

            pix[i][j] = new Pixel(new Color(edgeColor));
         }
      }

   }

   public static int  getGrayScale(int rgb) {
      //grayscales an int rgb using pixel method because idgaf
      Pixel p = new Pixel(new Color(rgb));
      p.setToGray();
      return p.getRed();
  }

   public void embossBad() 
   {
      BufferedImage image = new BufferedImage(pix[0].length, pix.length, BufferedImage.TYPE_BYTE_INDEXED);

      //convert pix to image
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            image.setRGB(j, i, pix[i][j].getColor().getRGB());//this uses xy on setrgb, but yx on getrgb FML
         }
      }

      //stuff google gave because tf is a kerne and convolveop

      Kernel kernel = new Kernel(3, 3, new float[] {-2, 0, 0,
                                                                  0, 1, 0,
                                                                  0, 0, 2});
     
      BufferedImageOp op = new ConvolveOp(kernel);
     
      image = op.filter(image, null);
   
      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            pix[i][j] = new Pixel(new Color(image.getRGB(j, i)));
         }
      }
   }

   public void cellify(int cell_count) 
   {
      //1: create {cell_count} points
      //2: for each point, make an arraylist of pixels in it
      //3: average each arraylist
      //3.5: go through all pixels and set color 
      //done

      ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
      Point[] points = new Point[cell_count];
      for (int i = 0; i < cell_count; i++)
      {
         //add {cell_count} arraylists to pixels
         pixels.add(new ArrayList<Pixel>());
         
         //make points full of random points on screen
         points[i] = new Point();
         points[i].x = (int) (Math.random()*pix[0].length);
         points[i].y = (int) (Math.random()*pix.length);
      }

      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            //get the point with the closest distance to the pixel
            int closestDist = Integer.MAX_VALUE;
            int closestPointIndex = 0;
            for (int k = 0; k < points.length; k++)
            {
               //get distance between pixel and point
               double dist = Math.sqrt(Math.pow(points[k].x - j, 2) + Math.pow(points[k].y - i, 2));

               //if the distance is less than the current closest distance, set the current closest distance to the new distance and set the index of the closest point to the current point
               if (dist < closestDist)
               {
                  closestDist = (int) dist;
                  closestPointIndex = k;
               }
            }
            pixels.get(closestPointIndex).add(pix[i][j]);

         }
      }

      //average each arraylist
      Color[] colors = new Color[cell_count];
      for (int i = 0; i < pixels.size(); i++)
      {
         if (pixels.get(i).size() == 0)
         {
            colors[i] = new Color(0, 0, 0);
            continue;
         }
         int r = 0;
         int g = 0;
         int b = 0;

         for (int j = 0; j < pixels.get(i).size(); j++)
         {
            r += pixels.get(i).get(j).getRed();
            g += pixels.get(i).get(j).getGreen();
            b += pixels.get(i).get(j).getBlue();
         }

         r /= pixels.get(i).size();
         g /= pixels.get(i).size();
         b /= pixels.get(i).size();

         colors[i] = new Color(r, g, b);
      }


      for (int i = 0; i < pix.length; i++)
      {
         for (int j = 0; j < pix[i].length; j++)
         {
            //get the point with the closest distance to the pixel
            int closestDist = Integer.MAX_VALUE;
            int closestPointIndex = 0;
            for (int k = 0; k < points.length; k++)
            {
               //get distance between pixel and point
               double dist = Math.sqrt(Math.pow(points[k].x - j, 2) + Math.pow(points[k].y - i, 2));

               //if the distance is less than the current closest distance, set the current closest distance to the new distance and set the index of the closest point to the current point
               if (dist < closestDist)
               {
                  closestDist = (int) dist;
                  closestPointIndex = k;
               }
            }
            
            pix[i][j].setColor(colors[closestPointIndex]);

         }
      }
      
   }

   public void chromakey(Picture secondPic, Pixel selectedPixel) 
   {
      Pixel[][] temp = pix.clone();

      //go through temp and draw secondPic onto it
      for (int i = 0; i < secondPic.getHeight(); i++)
      {
         for (int j = 0; j < secondPic.getWidth(); j++)
         {
            if (temp[i][j].equals(selectedPixel, 10))
            {
               temp[i][j] = secondPic.pix[i][j];
               
            }
         }
      }
      
      pix = temp;

   }


}