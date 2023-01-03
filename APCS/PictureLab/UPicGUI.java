//RUN THIS CLASS TO RUN THE PROJECT!
//DO NOT EDIT ANY CODE HERE!
//Changes for 2020:
//  JOptionPane used to select an image file from the src folder
//  The initial image is the first one in the src folder
//  This means the GUI class never needs to be touched by the students
//  There should be some consideration for if there are no images in the src folder
//  2022 Update - A default message is displayed if no images are present.
package PictureLab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//Mr. Uhl
//Program description: A runner class for the Picture Lab
//Uses: UControlPanel & Picture (which uses Pixel)
//Be sure to have your image saved in the src folder
//Dec 12, 2022

public class UPicGUI extends JPanel implements KeyListener, MouseListener
{
   private static final long serialVersionUID = 1L;
   private RenderingHints hints = new RenderingHints(
           RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//   public static final String FILE_PATH = "/Users/"+ System.getProperty("user.name") +"/Desktop/AP-PicLabImages/";
   public static final String FILE_PATH = "APCS/PictureLab/";
   private Font font = new Font("Cooper Black", Font.PLAIN, 15);
   private Picture upic;
   private UPicControlPanel controlPanel;
   private int selRow, selCol;
   private String currentFileName;
   
   
   public UPicGUI()
   {
      this.setLayout(new BorderLayout());
      this.addMouseListener(this);
      this.addKeyListener(this);
      setFocusable(true);
      requestFocus();
      
      selRow = 0; 
      selCol = 0;
      
      //locate the images in the src folder and set the pic as the first image
      if(imageFiles().length != 0)
      {
         currentFileName = FILE_PATH + imageFiles()[0];
         upic = new Picture(currentFileName);
      }
      else {
         upic = new Picture();
      }
      
      controlPanel = new UPicControlPanel(upic, this);
      this.setPreferredSize(new Dimension(upic.getWidth()+UPicControlPanel.PANEL_WIDTH, upic.getHeight()));
      setPixelBoxColor();
      this.add(controlPanel, BorderLayout.EAST);
   }
   
   public void openPic()
   {
      String[] allFiles = imageFiles();
      String filename = (String) JOptionPane.showInputDialog(null,
             "Select an image to open:",
             "Open An Image",
             JOptionPane.INFORMATION_MESSAGE,
             null,
             allFiles,
             allFiles[0]);
      
      if(filename != null) {
         currentFileName = FILE_PATH + filename;
         upic = new Picture(FILE_PATH + filename);
         resetPanelForNewImage();
      }
   }
   
   public void resetPanelForNewImage()
   {
      this.removeAll();
      
      JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
      topFrame.setTitle(currentFileName);
      topFrame.setSize(new Dimension(upic.getWidth()+UPicControlPanel.PANEL_WIDTH, upic.getHeight()+22));
      topFrame.setLocationRelativeTo(null);
      
      controlPanel = new UPicControlPanel(upic, this);
      setPixelBoxColor();      
      this.add(controlPanel, BorderLayout.EAST);
   }
   
   public void setTitle()
   {
      JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
      topFrame.setTitle(currentFileName);
   }
   
   public String getCurrentImageFileName()
   {
      return currentFileName;
   }
   
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHints(hints);
      g2.setFont(font);
      
      upic.drawPixels(g2, 0, 0);
      
      //OUTINE THE SELECTED PIXEL ON THE IMAGE
      int yiq = ((upic.getPixel(selRow, selCol).getRed()*299) + (upic.getPixel(selRow, selCol).getGreen()*587)
            + (upic.getPixel(selRow, selCol).getBlue()*114))/1000;
      g2.setColor(yiq >= 140 ? Color.BLACK : Color.LIGHT_GRAY);
      g2.drawLine(selCol-1, selRow-1, selCol+1, selRow-1);
      g2.drawLine(selCol-1, selRow+1, selCol+1, selRow+1);
      g2.drawLine(selCol-1, selRow+1, selCol-1, selRow-1);
      g2.drawLine(selCol+1, selRow+1, selCol+1, selRow-1);
      g2.drawOval(selCol-5, selRow-5, 10, 10);
      g2.drawLine(selCol, selRow-1, selCol, selRow-10);
      g2.drawLine(selCol, selRow+1, selCol, selRow+10);
      g2.drawLine(selCol-1, selRow, selCol-10, selRow);
      g2.drawLine(selCol+1, selRow, selCol+10, selRow);
   }
   
   public void update()
   {
      setPixelBoxColor();
      setRGBtextValues();
      repaint();
      requestFocus();
   }

   public void setPixelBoxColor()
   {
      Color selPixelColor = upic.getPixel(selRow, selCol).getColor();
      controlPanel.setPixelBoxColor(selPixelColor);
   }

   public void setRGBtextValues()
   {
      Color selPixelColor = upic.getPixel(selRow, selCol).getColor();
      controlPanel.setRGBtext(selPixelColor);
   }

   public int getSelRow()
   {
      return selRow;
   }

   public int getSelCol()
   {
      return selCol;
   }

   @Override
   public void keyPressed(KeyEvent e)
   {
      int key = e.getKeyCode();

      if(key == KeyEvent.VK_UP)
         selRow--;
      if(key == KeyEvent.VK_DOWN)
         selRow++;
      if(key == KeyEvent.VK_LEFT)
         selCol--;
      if(key == KeyEvent.VK_RIGHT)
         selCol++;
      
      checkBounds();
      update();
   }
   
   @Override
   public void keyReleased(KeyEvent e){}

   @Override
   public void keyTyped(KeyEvent e){}

   @Override
   public void mousePressed(MouseEvent e)
   {
      if(onPicPanel(e.getX(), e.getY())){
         selCol = e.getX()-1;
         selRow = e.getY()-3;
         update();
      }
   }
   
   public boolean onPicPanel(int x, int y)
   {
      return (x >= 0 && x < upic.getWidth()) 
              && (y >= 0 && y < upic.getHeight());
   }
   
   public void checkBounds()
   {
      if(selCol < 0) selCol = 0;
      if(selCol >= upic.getWidth()) selCol = upic.getWidth()-1;
      if(selRow < 0) selRow = 0;
      if(selRow >= upic.getHeight()) selRow = upic.getHeight()-1;
   }

   @Override
   public void mouseClicked(MouseEvent e){}

   @Override
   public void mouseReleased(MouseEvent e){}

   @Override
   public void mouseEntered(MouseEvent e){}

   @Override
   public void mouseExited(MouseEvent e){}
   
   public static void createAndShowGUI()
   {
      JFrame frame = new JFrame("UPic v1.19.12.19");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      UPicGUI displayPanel = new UPicGUI();
      frame.getContentPane().add(displayPanel);
      frame.setTitle(displayPanel.getCurrentImageFileName());
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
   
   public static void main(String[] args)
   {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGUI();
         }
      });
   }
   
   
   
   
//List of image file names for loading images from a JOptionPane dropdown
   public String[] imageFiles()
   {
      String[] imageFileList;

      File f = new File(FILE_PATH);
      File[] files = f.listFiles();
//      System.out.println("ALL FILES: " + Arrays.toString(files));
      //count only files that are images (png, jpeg, jpg)
      int counter = 0;
      for(File x : files)
         if(x.getName().length() >= 4 
            && (
                x.getName().substring(x.getName().length()-4).equals(".png")
            ||  x.getName().substring(x.getName().length()-4).equals(".jpg")
            ||  x.getName().substring(x.getName().length()-4).equals(".jpeg")
            ))
            counter++;
      
      imageFileList = new String[counter];
      int index = 0;
      for(File x : files){
         if(x.getName().length() >= 4 
               && (x.getName().substring(x.getName().length()-4).equals(".png")
               ||  x.getName().substring(x.getName().length()-4).equals(".jpg")
               ||  x.getName().substring(x.getName().length()-4).equals(".jpeg")
               ))
         {
            imageFileList[index] = x.getName();
            index++;
         }
      }
      System.out.println("IMAGE FILES: " + Arrays.toString(imageFileList));
      
      return imageFileList;
   }

}
