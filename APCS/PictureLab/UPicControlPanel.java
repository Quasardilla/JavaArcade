//THIS IS THE CLASS FOR CREATING BUTTONS THAT CALL THE PICTURE METHODS
//Changes for 2020:
//  Added an "Open" button for opening an image using a JOptionPane with a dropdown
package PictureLab;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

// import Intro.Exploring2DArrays.Exploring2DArrays;

//Mr. Uhl
//Program description: For use with the Picture Lab
//Jan 13, 2021

public class UPicControlPanel extends JPanel
{
   private static final long serialVersionUID = 1L;
   private Font font1 = new Font("Cooper Black", Font.PLAIN, 20);
   private Font buttonFont = new Font("Lucida Grande", Font.BOLD, 14);
   public static final int PANEL_WIDTH = 250;

   private JPanel pixelInfoPanel, buttonPanel, savePanel, restoreSaveOpenPanel;
   private JTextArea pixelColor, rgbText;
   //If you need more buttons, add them to this list
   private JButton negative, zeroRed, zeroGreen, zeroBlue, grayscale,
                   flipHorizontal, flipVertical, mirrorLeftToRight, mirrorRightToLeft,
                   mirrorTopToBottom, mirrorBottomToTop, restore, save, open,
                   edger, decodeRed, decodeGreen, decodeBlue, decodeShades, blur,
                   button1, encodeRed, chromakeyButton, pixelateButton, zoomButton;
   private Picture upic;
   private UPicGUI gui;
   
   public UPicControlPanel(Picture upic, UPicGUI gui)
   {
      this.setBackground(Color.LIGHT_GRAY);
      this.upic = upic;
      this.gui = gui;
      
      this.setLayout(new BorderLayout());
      
      pixelInfoPanel = new JPanel();
      Border titled = BorderFactory.createTitledBorder("Selected pixel");
      pixelInfoPanel.setBorder(titled);

      pixelInfoPanel.setPreferredSize(new Dimension(PANEL_WIDTH, 145));
      pixelInfoPanel.setLayout(new GridLayout(0, 2, 2, 2));
      addPixelColorBox();
      addRGBtextPanel();
      this.add(pixelInfoPanel, BorderLayout.NORTH);
      
      restoreSaveOpenPanel = new JPanel();
      restoreSaveOpenPanel.setLayout(new GridLayout(2, 1));
      restoreSaveOpenPanel.setPreferredSize(new Dimension(PANEL_WIDTH, 100));
//      restoreSaveOpenPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
//      restoreSaveOpenPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
      restoreSaveOpenPanel.setBorder(new TitledBorder("Picture Options"));

      savePanel = new JPanel();
      savePanel.setPreferredSize(new Dimension(PANEL_WIDTH, 100));
//      savePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      savePanel.setLayout(new GridLayout(1, 0));
      addRestoreButton();
      addOpenButton();
      addSaveButton();
      restoreSaveOpenPanel.add(savePanel);
      this.add(restoreSaveOpenPanel, BorderLayout.SOUTH);

      buttonPanel = new JPanel();
      buttonPanel.setPreferredSize(new Dimension(PANEL_WIDTH, 0));
      buttonPanel.setLayout(new GridLayout(0, 2));
      
      addPhotoNegativeButton();
      addZeroRedButton();
      addGrayscaleButton();
      addZeroGreenButton();
      addEmptyButton1();
      addZeroBlueButton();
      addMirrorRightToLeftButton();
      addMirrorLeftToRightButton();
      addMirrorBottomToTopButton();
      addMirrorTopToBottomButton();
      addFlipHorizontalButton();
      addFlipVerticalButton();
      addEdgeDetectButton();
      addEncodeRedButton();
      addBlurButton();
      addDecodeRedButton();
      addPixelateButton();
      addDecodeGreenButton();
      addChromakeyButton();
      addDecodeBlueButton();
      addZoomButton();
      addDecodeShadesButton();
      //additional buttons need additional methods to add them to the panel

      this.add(buttonPanel, BorderLayout.CENTER);
   }
   
   public void addPixelColorBox()
   {
      pixelColor = new JTextArea();
      pixelColor.setEditable(false);
      pixelColor.setFocusable(false);
      pixelColor.setPreferredSize(new Dimension(100, 100));
      pixelInfoPanel.add(pixelColor);
   }
   
   public void setPixelBoxColor(Color color)
   {
      pixelColor.setBackground(color);
   }
   
   public void addRGBtextPanel()
   {
      rgbText = new JTextArea();
      rgbText.setEditable(false);
      rgbText.setFocusable(false);
      rgbText.setFont(font1);
      rgbText.setPreferredSize(new Dimension(PANEL_WIDTH-20, 120));
      setRGBtext(getSelectedPixel().getColor());
      pixelInfoPanel.add(rgbText);
   }
   
   public void setRGBtext(Color selColor)
   {
      rgbText.setText("  r = " + selColor.getRed()
                 + "\n  g = "  + selColor.getGreen()
                 + "\n  b = "  + selColor.getBlue()
                 + "\n  row: " + gui.getSelRow() 
                 + "\n  col: " + gui.getSelCol());
   }
   
   public Pixel getSelectedPixel()
   {
      return upic.getPixel(gui.getSelRow(), gui.getSelCol());
   }
   
   public void addPhotoNegativeButton()
   {
      negative = new JButton("Photo Neg");
      negative.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      negative.setFont(buttonFont);
      negative.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
           upic.photoNegative();
            gui.update();
         }
      });
      
      buttonPanel.add(negative);
   }
   
   public void addGrayscaleButton()
   {
      grayscale = new JButton("Grayscale");
      grayscale.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      grayscale.setFont(buttonFont);
      grayscale.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.grayscale();
            gui.update();
         }
      });
      
      buttonPanel.add(grayscale);
   }

   public void addZeroRedButton()
   {
      zeroRed = new JButton("Remove Red");
      zeroRed.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      zeroRed.setFont(buttonFont);
      zeroRed.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
           upic.zeroRed();
            gui.update();
         }
      });
      
      buttonPanel.add(zeroRed);
   }
   
   public void addZeroGreenButton()
   {
      zeroGreen = new JButton("Remove Green");
      zeroGreen.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      zeroGreen.setFont(buttonFont);
      zeroGreen.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
           upic.zeroGreen();
            gui.update();
         }
      });
      
      buttonPanel.add(zeroGreen);
   }
   
   public void addZeroBlueButton()
   {
      zeroBlue = new JButton("Remove Blue");
      zeroBlue.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      zeroBlue.setFont(buttonFont);
      zeroBlue.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
           upic.zeroBlue();
            gui.update();
         }
      });
      
      buttonPanel.add(zeroBlue);
   }
   
   public void addMirrorRightToLeftButton()
   {
      mirrorRightToLeft = new JButton("Right -> Left");
      mirrorRightToLeft.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      mirrorRightToLeft.setFont(buttonFont);
      mirrorRightToLeft.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.mirrorRightToLeft();
            gui.update();
         }
      });
      
      buttonPanel.add(mirrorRightToLeft);
   }
   
   public void addMirrorLeftToRightButton()
   {
      mirrorLeftToRight = new JButton("Left -> Right");
      mirrorLeftToRight.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      mirrorLeftToRight.setFont(buttonFont);
      mirrorLeftToRight.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.mirrorLeftToRight();
            gui.update();
         }
      });
      
      buttonPanel.add(mirrorLeftToRight);
   }
   
   public void addMirrorBottomToTopButton()
   {
      mirrorBottomToTop = new JButton("Bottom -> Top");
      mirrorBottomToTop.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      mirrorBottomToTop.setFont(buttonFont);
      mirrorBottomToTop.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.mirrorBottomToTop();
            gui.update();
         }
      });
      
      buttonPanel.add(mirrorBottomToTop);
   }
   
   public void addMirrorTopToBottomButton()
   {
      mirrorTopToBottom = new JButton("Top -> Bottom");
      mirrorTopToBottom.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      mirrorTopToBottom.setFont(buttonFont);
      mirrorTopToBottom.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.mirrorTopToBottom();
            gui.update();
         }
      });
      
      buttonPanel.add(mirrorTopToBottom);
   }
   
   public void addFlipHorizontalButton()
   {
      flipHorizontal = new JButton("Flip Horizontal");
      flipHorizontal.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      flipHorizontal.setFont(buttonFont);
      flipHorizontal.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.flipHorizontal();
            gui.update();
         }
      });
      
      buttonPanel.add(flipHorizontal);
   }
   
   public void addFlipVerticalButton()
   {
      flipVertical = new JButton("Flip Vertical");
      flipVertical.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      flipVertical.setFont(buttonFont);
      flipVertical.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.flipVertical();
            gui.update();
         }
      });
      
      buttonPanel.add(flipVertical);
   }
   
   public void addEdgeDetectButton()
   {
      edger = new JButton("Edge Detect");
      edger.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      edger.setFont(buttonFont);
      edger.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
         //   upic.edgeDetection(15);
           upic.sobel();
            gui.update();
         }
      });
      
      buttonPanel.add(edger);
   }

   public void addBlurButton()
   {
      blur = new JButton("Blur");
      blur.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      blur.setFont(buttonFont);
      blur.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.blur(1);
            gui.update();
         }
      });
      
      buttonPanel.add(blur);
   }
   
   public void addEncodeRedButton()
   {
      encodeRed = new JButton("Encode Red");
      encodeRed.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      encodeRed.setFont(buttonFont);
      encodeRed.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
         //   upic.encodeUsingRed(new Picture("APCS/PictureLab/cat.png"));
         //   upic.encodeUsingRGB(new Picture("APCS/PictureLab/cat.png"));
         //   upic.encodeUsingRed(getSecondPic());
            upic.encodeUsingRed("I've lost my mind");
            gui.update();
         }
      });
      
      buttonPanel.add(encodeRed);
   }
   
   public void addDecodeRedButton()
   {
      decodeRed = new JButton("Decode Red");
      decodeRed.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      decodeRed.setFont(buttonFont);
      decodeRed.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.decodeUsingRed();
            gui.update();
         }
      });
      
      buttonPanel.add(decodeRed);
   }
   
   public void addDecodeGreenButton()
   {
      decodeGreen = new JButton("Decode Green");
      decodeGreen.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      decodeGreen.setFont(buttonFont);
      decodeGreen.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.decodeUsingGreen();
            gui.update();
         }
      });
      
      buttonPanel.add(decodeGreen);
   }
   
   public void addDecodeBlueButton()
   {
      decodeBlue = new JButton("Decode Blue");
      decodeBlue.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      decodeBlue.setFont(buttonFont);
      decodeBlue.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.decodeUsingBlue();
            gui.update();
         }
      });
      
      buttonPanel.add(decodeBlue);
   }
   
   public void addDecodeShadesButton()
   {
      decodeShades = new JButton("Decode Shades");
      decodeShades.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      decodeShades.setFont(buttonFont);
      decodeShades.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
           upic.decodeUsingShades();
            gui.update();
         }
      });
      
      buttonPanel.add(decodeShades);
   }
   
   public void addChromakeyButton()
   {
      chromakeyButton = new JButton("Chromakey");
      chromakeyButton.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      chromakeyButton.setFont(buttonFont);
      chromakeyButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
           upic.chromakey(getSecondPic(), getSelectedPixel());
            gui.update();
         }
      });
      
      buttonPanel.add(chromakeyButton);
   }
   
   int x = 2;
   public void addPixelateButton()
   {
      pixelateButton = new JButton("Pixelate");
      pixelateButton.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      pixelateButton.setFont(buttonFont);
      pixelateButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
           upic.pixelate(x++);
            gui.update();
         }
      });
      
      buttonPanel.add(pixelateButton);
   }
   
   public void addZoomButton()
   {
      zoomButton = new JButton("Cellify");
      zoomButton.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      zoomButton.setFont(buttonFont);
      zoomButton.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
//            upic.magnify(gui.getSelRow(), gui.getSelCol(), 100, 2);
            upic.cellify(500);
            gui.update();
         }
      });
      
      buttonPanel.add(zoomButton);
   }
   
   public void addEmptyButton1()
   {
      button1 = new JButton("Glitch");
      button1.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      button1.setFont(buttonFont);
      button1.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.glitch();
            gui.update();
         }
      });
      
      buttonPanel.add(button1);
   }
   
// The methods below are for Picture commands such as Restore, Open, Save and loading a secondary picture for methods that require two images
   
   public Picture getSecondPic()
   {
      String[] allFiles = gui.imageFiles();
      String filename = (String) JOptionPane.showInputDialog(null,
             "Select a secondary image.\nIt must have dimensions equal to or smaller than the current image.",
             "Load a Secondary Image",
             JOptionPane.INFORMATION_MESSAGE,
             null,
             allFiles,
             allFiles[0]);
      
      Picture upic2 = null;
      if(filename != null) {
         upic2 = new Picture(UPicGUI.FILE_PATH + filename);
      }
      
      return upic2;
   }
   
   public void addRestoreButton()
   {
      restore = new JButton("Restore");
      restore.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      restore.setFont(font1);
      restore.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.restore();
            gui.update();
         }
      });
      
      restoreSaveOpenPanel.add(restore);
   }
   
   public void addSaveButton()
   {
      save = new JButton("Save");
      save.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      save.setFont(font1);
      save.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            upic.savePic();
         }
      });
      
      savePanel.add(save);
   }
   
   public void addOpenButton()
   {
      open = new JButton("Open");
      open.setPreferredSize(new Dimension(PANEL_WIDTH, 40));
      open.setFont(font1);
      open.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
            gui.openPic();
            gui.update();
         }
      });
      
      savePanel.add(open);
   }
}
