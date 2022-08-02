package Random;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class multiPanel extends JPanel {

    private JPanel bigPanel;
    private JPanel panelOne;
    private JPanel panelTwo;
    private JPanel panelThree;

    public multiPanel() {

        panelOne = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.black);
                g.drawString("writing something", 10, 10);
                g.drawRect(10, 10, 50, 50);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(100, 100);
            }

        };
        
        panelTwo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.black);
                g.drawString("writing something", 10, 10);
                g.drawRect(10, 10, 50, 50);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(100, 100);
            }

        };
        bigPanel = new JPanel(new GridLayout(2, 1));
        bigPanel.add(panelOne);
        bigPanel.add(panelTwo);
        // this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(bigPanel);
        //other codes...
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                JFrame jf = new JFrame();
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                multiPanel tb = new multiPanel();
                jf.add(tb);
                jf.pack();
                jf.setLocationRelativeTo(null);
                jf.setVisible(true);
            }
        });
    }
}