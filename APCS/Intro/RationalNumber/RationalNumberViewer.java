package Intro.RationalNumber;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

//AP Computer Science
//Program description:  An interactive program for using the RationalNumber class.


public class RationalNumberViewer extends JPanel
{
   private static final long serialVersionUID = 1L;
   private static final int PREF_W = 800;
   private static final int PREF_H = 600;
   
   private JTextField n1, d1, n2, d2, nResult1, dResult1;
   private JTextField n3, d3, nResult2, dResult2;
   private JComboBox<String> opBox, functionBox;
   private JButton calcButtonTop, calcButtonBottom;
   private int leftMargin, topMargin;
   private boolean showVinculum;
   private String answer; //used only for values that are NOT in fraction form
   private int lastIndexForFractionForm;
   
   public RationalNumberViewer()
   {
      initializeAllValuesAndComponents();
   }
   
   /**
    *    TASK #1
    *    Complete the method below that uses the values in the text fields (n1, d1, n2, d2) as rational number values
    *    and performs the selected operation and displays the result in the result text fields (nResult1, dResult1)
    */
   public void performAndDisplayTopCalculations()
   {
      System.out.println("Perform the top operation and update the result.");
      //WRITE THE NECESSARY CODE TO DISPLAY THE RESULT OF THE CURRENT OPERATION
      //Use opBox.getSelectedItem() to get the String value for the operation
      //Use nResult1.setText and dResult1.setText to set the proper values for the result
      
      RationalNumber r1 = new RationalNumber(Integer.parseInt(n1.getText()), Integer.parseInt(d1.getText()));
      RationalNumber r2 = new RationalNumber(Integer.parseInt(n2.getText()), Integer.parseInt(d2.getText()));
      
      if(opBox.getSelectedItem().equals("+"))
      {
         r1.add(r2);
         nResult1.setText("" + r1.getA());
         dResult1.setText("" + r1.getB());
      }
      if(opBox.getSelectedItem().equals("–"))
      {
         r1.subtract(r2);
         nResult1.setText("" + r1.getA());
         dResult1.setText("" + r1.getB());
      }
      if(opBox.getSelectedItem().equals("x"))
      {
         r1.multiply(r2);
         nResult1.setText("" + r1.getA());
         dResult1.setText("" + r1.getB());
      }
      if(opBox.getSelectedItem().equals("÷"))
      {
         r1.divide(r2);
         nResult1.setText("" + r1.getA());
         dResult1.setText("" + r1.getB());
      }
   }
   
   /**
    *    TASK #2
    *    Complete the method below that uses the values in the text fields (n3, d3) as rational number values
    *    and performs the selected operation and displays the result in the result text fields (nResult2, dResult2)
    */
   public void performAndDisplayBottomCalculations()
   {
      System.out.println("Perform the bottom function and update the result.");
      //WRITE THE NECESSARY CODE TO DISPLAY THE RESULT OF THE CURRENT OPERATION
      //Use functionBox.getSelectedItem() to get the String value for the operation
      //Use nResult2.setText and dResult2.setText to set the proper values for the result
      //Assign a value to the answer variable if the value is not a fraction
      
      RationalNumber r3 = new RationalNumber(Integer.parseInt(n3.getText()), Integer.parseInt(d3.getText()));
      
      if(showVinculum) {
         if(functionBox.getSelectedItem().equals("simplified"))
            r3.simplify();
            nResult2.setText("" + r3.getA());
            dResult2.setText("" + r3.getB());
      }
      if (functionBox.getSelectedItem().equals("as a decimal"))
      {
         answer = "" + ((double) r3.getA() / r3.getB());
         repaint();
      }
      if (functionBox.getSelectedItem().equals("reciprocal"))
      {
         nResult2.setText("" + r3.getReciprocal().getA());
         dResult2.setText("" + r3.getReciprocal().getB());
         repaint();
      }
      if (functionBox.getSelectedItem().equals("squared"))
      {
         r3.multiply(r3);
         nResult2.setText("" + r3.getA());
         dResult2.setText("" + r3.getB());
         repaint();
      }
   }
   
   public void initializeAllValuesAndComponents()
   {
      this.setLayout(null);
      this.setBackground(Color.WHITE);
      
      leftMargin = 50;
      topMargin = 80;
      
      answer = "";
      
      n1 = getTextField("1", leftMargin, topMargin);
      add(n1);
      d1 = getTextField("2", leftMargin, topMargin + 100);
      add(d1);
      n2 = getTextField("3", leftMargin + 200, topMargin);
      add(n2);
      d2 = getTextField("4", leftMargin + 200, topMargin + 100);
      add(d2);
      
      nResult1 = getTextField("a", leftMargin + 400, topMargin);
      nResult1.setEditable(false);
      nResult1.setFocusable(false);
      nResult1.setVisible(false);
      add(nResult1);
      
      dResult1 = getTextField("b", leftMargin + 400, topMargin + 100);
      dResult1.setEditable(false);
      dResult1.setFocusable(false);
      dResult1.setVisible(false);
      add(dResult1);
      
      //THE DROPDOWN BOX FOR THE OPERATIONS
      String[] ops = {"+", "–", "x", "÷"};
      opBox = new JComboBox<String>(ops);
      opBox.setLocation(leftMargin + 120, topMargin + 60);
      opBox.setSize(new Dimension(70, 50));
      opBox.setFont(new Font("Arial", Font.PLAIN, 25));
      opBox.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            nResult1.setVisible(false);
            dResult1.setVisible(false);
            calcButtonTop.setForeground(Color.RED);
            calcButtonTop.setVisible(true);
         }
      });
      add(opBox);
      
      n3 = getTextField("5", leftMargin, topMargin + 250);
      add(n3);
      d3 = getTextField("10", leftMargin, topMargin + 350);
      add(d3);
      
      nResult2 = getTextField("c", leftMargin + 400, topMargin + 250);
      nResult2.setEditable(false);
      nResult2.setFocusable(false);
      nResult2.setVisible(false);
      add(nResult2);
      
      dResult2 = getTextField("d", leftMargin + 400, topMargin + 350);
      dResult2.setEditable(false);
      dResult2.setFocusable(false);
      dResult2.setVisible(false);
      add(dResult2);
      
      //THE DROPDOWN BOX FOR THE OTHER FUNCTIONS
      //THE FIRST ITEMS ALWAYS RESULT IN VALUES THAT ARE FRACTIONS SO BE SURE TO SET lastindexForFractionForm PROPERLY
      lastIndexForFractionForm = 2;
      String[] functions = {"simplified", "reciprocal", "squared", "as a decimal"};
      functionBox = new JComboBox<String>(functions);
      functionBox.setLocation(leftMargin + 120, topMargin + 310);
      functionBox.setSize(new Dimension(180, 50));
      functionBox.setFont(new Font("Arial", Font.PLAIN, 22));
      functionBox.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            nResult2.setVisible(false);
            dResult2.setVisible(false);
            showVinculum = false;
            answer = "";
            calcButtonBottom.setForeground(Color.RED);
            calcButtonBottom.setVisible(true);
         }
      });
      add(functionBox);
      
      //THE TOP CALCULATE BUTTON FOR OPERATIONS
      calcButtonTop = new JButton("Calculate");
      calcButtonTop.setLocation(leftMargin+550, topMargin + 60);
      calcButtonTop.setSize(150, 60);
      calcButtonTop.setFont(new Font("Cooper Black", Font.PLAIN, 25));
      calcButtonTop.setForeground(Color.RED);
      calcButtonTop.setBorder(new LineBorder(Color.RED, 2, true));
      
      calcButtonTop.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            n1.setBorder(null); n1.setSelectionColor(Color.WHITE);
            d1.setBorder(null);
            n2.setBorder(null);
            d2.setBorder(null);
            nResult1.setVisible(true);
            dResult1.setVisible(true);
            calcButtonTop.setForeground(Color.BLACK);
            calcButtonTop.setVisible(false);
            performAndDisplayTopCalculations();
         }
      });
      add(calcButtonTop);
      
      //THE BOTTOM CALCULATE BUTTON FOR OTHER FUNCTIONS
      calcButtonBottom = new JButton("Calculate");
      calcButtonBottom.setLocation(leftMargin+550, topMargin + 310);
      calcButtonBottom.setSize(150, 60);
      calcButtonBottom.setFont(new Font("Cooper Black", Font.PLAIN, 25));
      calcButtonBottom.setForeground(Color.RED);
      calcButtonBottom.setBorder(new LineBorder(Color.RED, 2, true));

      calcButtonBottom.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            n3.setBorder(null);
            d3.setBorder(null);
            if(functionBox.getSelectedIndex() <= lastIndexForFractionForm) {
               nResult2.setVisible(true);
               dResult2.setVisible(true);
               showVinculum = true;
            }
            calcButtonBottom.setForeground(Color.BLACK);
            calcButtonBottom.setVisible(false);
            performAndDisplayBottomCalculations();
         }
      });
      add(calcButtonBottom);
      
      //THESE LISTENERS PROVIDE A WAY TO MANAGE THE VISIBILITY OF VARIOUS COMPONENTS
      addTextFieldChangeListener(n1, nResult1, dResult1, calcButtonTop);
      addTextFieldChangeListener(d1, nResult1, dResult1, calcButtonTop);
      addTextFieldChangeListener(n2, nResult1, dResult1, calcButtonTop);
      addTextFieldChangeListener(d2, nResult1, dResult1, calcButtonTop);
      addTextFieldChangeListener(n3, nResult2, dResult2, calcButtonBottom);
      addTextFieldChangeListener(d3, nResult2, dResult2, calcButtonBottom);
   }
   
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      
      g2.setStroke(new BasicStroke(5));
      g2.setColor(new Color(120, 50, 150));
      g2.setFont(new Font("Cooper Black", Font.PLAIN, 50));
      String titleMessage = "Fractions Are Fun!";
      int startX = getWidth() / 2 - g2.getFontMetrics().stringWidth(titleMessage) / 2;
      g2.drawString(titleMessage, startX, 50);
      
      g2.setColor(Color.BLACK);
      
      //Vinculums for top fractions
      g2.drawLine(leftMargin, topMargin + 90, leftMargin + 100, topMargin + 90);
      g2.drawLine(leftMargin + 200, topMargin + 90, leftMargin + 300, topMargin + 90);
      g2.drawLine(leftMargin + 400, topMargin + 90, leftMargin + 500, topMargin + 90);
      g2.setFont(new Font("Cooper Black", Font.PLAIN, 40));
      g2.drawString("=", leftMargin + 335, topMargin + 100);
      
      //Vinculums for bottom fractions
      g2.drawLine(leftMargin, topMargin + 340, leftMargin + 100, topMargin + 340);
      //The answer is not always a fraction, so this line is not always needed
      if(showVinculum)
         g2.drawLine(leftMargin + 400, topMargin + 340, leftMargin + 500, topMargin + 340);
      else {
         g2.setFont(new Font("Cooper Black", Font.PLAIN, 40));
         g2.drawString(answer, leftMargin + 400, topMargin + 350);
      }
      
      g2.setFont(new Font("Cooper Black", Font.PLAIN, 40));
      g2.drawString("is", leftMargin + 335, topMargin + 350);
   }
   
   //returns a text field with sizing and formatting for numerator and denominator areas
   public JTextField getTextField(String text, int x, int y)
   {
      JTextField tf = new JTextField(text);
      tf.setLocation(x, y);
      tf.setSize(100, 80);
      tf.setFont(new Font("Cooper Black", Font.PLAIN, 40));
      tf.setHorizontalAlignment(SwingUtilities.CENTER);     
      tf.setBorder(null);
      tf.setSelectionColor(Color.WHITE);
      tf.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent evt) {
            System.out.println("Pressing the text field...");
            tf.setSelectionColor(new Color(165, 205, 255));
         }
      });
      
      return tf;
   }
   
   //Adds listeners to editable text fields to manage visible components
   //This helps avoid incorrect results to be displayed when the user makes a change to a value
   //In other words, the previous result is removed as a new calculation is being entered
   public void addTextFieldChangeListener(JTextField tf, JTextField tfResult_n, JTextField tfResult_d, JButton button)
   {
      tf.getDocument().addDocumentListener(new DocumentListener() {
         public void changedUpdate(DocumentEvent e) {
            performThisTask();
         }
         public void removeUpdate(DocumentEvent e) {
            performThisTask();
         }
         public void insertUpdate(DocumentEvent e) {
            performThisTask();
         }

         public void performThisTask() {
            System.out.println("Editing textfield...");
            tf.setSelectionColor(new Color(0, 0, 200));
            tfResult_n.setVisible(false);
            tfResult_d.setVisible(false);
            tf.setBorder(new LineBorder(Color.RED, 2, true));
            answer = "";
            showVinculum = false;
            button.setForeground(Color.RED);
            button.setVisible(true);
            revalidate();
            repaint();
         }
      });
   }
   
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }
   
   public static void createAndShowGUI()
   {
      RationalNumberViewer gamePanel = new RationalNumberViewer();
      
      JFrame frame = new JFrame("Operations with Fraction");
      frame.getContentPane().add(gamePanel);
      frame.pack();
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
   
   public static void main(String[] args)
   {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run()
         {
            createAndShowGUI();
         }
      });
   }
}