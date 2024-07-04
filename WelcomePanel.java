package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
    

public class WelcomePanel extends JPanel {
    private final int centerWidth;    // Width of the BorderLayout  
    private final int centerHeight;   // Height of the BorderLayout  
    
    private Window window;

    private Color myYellow = new Color(244,235,179);// Defines a color 
    private Color myOrange = new Color(242,229,222);
    private Color myBlue = new Color(220,227,242);
    private Color myGreen = new Color(242,253,208);
    private TimePanel myTimePanel; //Adds TimePanel to the time button
    private DepthPanel myDepthPanel; //Adds DepthPanel to the depth button
    private TempPanel myTemperaturePanel; //Adds TempPanel to the temp button
    
    public WelcomePanel(int centerPaneWidth, int centerPaneHeight,Window window) {
        super();
        this.window=window;
        
    
        this.myTimePanel = new TimePanel(800,800);//Adds TimePanel to the time button
        this.myDepthPanel = new DepthPanel(800,800,window); //Adds DepthPanel to the depth button
        this.myTemperaturePanel = new TempPanel(800,800); //Adds TempPanel to the temp button
        
        this.centerHeight = centerPaneHeight;
        this.centerWidth = centerPaneWidth; 
        this.setLayout(new BorderLayout()); 
        this.setBackground(myYellow);
        this.setMinimumSize(new Dimension(800,800));
     
        JLabel titleLabel = new JLabel();
        titleLabel.setBackground(myYellow); //title
        
        JLabel presentationLabel = new JLabel();
        presentationLabel.setBackground(myYellow); //Presentation 
        
        
        /* Setting up the title label */
        titleLabel.setText("<html><center><br>About this program:<br>");
        titleLabel.setFont(new Font( "FUTURA", Font.BOLD,40));   //defines font
        titleLabel.setForeground(Color.BLACK);    //defines font color
        
        /* Setting up the presentation label */
        presentationLabel.setText(
            "<html><center><br>This program aims to represent a classic physical problem: heat diffusion in soil.\n" +
            "<br>" +
            "<br>" +
            "The problem is as follows: we model a soil in 2 dimensions (infinite length L and semi-infinite depth x).\n" +
            "<br>" +
            "The surface of the soil is at x=0, and the depth increases downward along the x axis.\n" +
            "<br>" +
            "At t=0, the temperature of the soil is uniform at every point.\n" +
            "<br>" +
            "At the moment t=0, the soil is cooled or heated at its the surface by water. At x=0, a uniform temperature T is thus imposed.\n" +
            "<br>" +
            "<br>" +
            "If you enter two variables among the temperature (in degrees Celsius), the duration of the process (in minutes)<br/> or the depth "
                   + "(in meters) at which we consider the problem, <br/> the software will provide you with the value of the third variable:" +
            "<br>" +
            "This program ultimately makes it possible to visualize the system in 2D and 3D." +
            "<br>" +
            "<br>" + "Please restart the program if you want to calculate a different variable from the one studied," +
            "<br>" + "and note that turning on fullscreen might prevent normal displaying and interaction" +
            "<br>" +
            "<br>" +

            "This program was designed to be lightweight and easy to use, as well as user-friendly.\n" +
            "<br>" +
            "<br>" +
            "<em>Augustin Debacq, all rights reserved.<em></html>"
            + "<br>");
                /* <br> : jump one line
                * <center> aligns text to the center*/
        presentationLabel.setFont(new Font( "FUTURA", Font.ROMAN_BASELINE,20));   //defines font 
        presentationLabel.setForeground(Color.BLACK);    //defines font color
      


        /* Adds to panel */
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        presentationLabel.setVerticalAlignment(JLabel.TOP);
        presentationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(presentationLabel, BorderLayout.CENTER);
        
        
        JButton tempbutton = new JButton("Temperature");
        JButton depthbutton = new JButton("Depth");
        JButton timebutton = new JButton("Duration");
        JPanel choicebutton = new JPanel();//Panel with three choice buttons
        choicebutton.add(tempbutton) ;
        choicebutton.add(depthbutton) ;
        choicebutton.add(timebutton) ;
        
        ActionListener ecouteurDeClicTemp = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                removeAll();
                add(myTemperaturePanel);
                revalidate();
                repaint();
            }
        };
        ActionListener ecouteurDeClicD = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
               
                removeAll();
                add(myDepthPanel);
                revalidate();
                repaint();
            }
        };
        ActionListener ecouteurDeClicTime = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
               
                removeAll();
                add(myTimePanel);
                revalidate();
                repaint();
            }
        };
        tempbutton.addActionListener(ecouteurDeClicTemp);
        depthbutton.addActionListener(ecouteurDeClicD);
        timebutton.addActionListener(ecouteurDeClicTime);
        
        
        tempbutton.setPreferredSize(new Dimension(250, 150));
        depthbutton.setPreferredSize(new Dimension(250, 150));
        timebutton.setPreferredSize(new Dimension(250, 150));
        tempbutton.setFont(new Font("Courier", Font.BOLD, 25));
        depthbutton.setFont(new Font("Courier", Font.BOLD, 25));
        timebutton.setFont(new Font("Courier", Font.BOLD, 25));
        tempbutton.setBackground(myOrange);
        depthbutton.setBackground(myBlue);
        timebutton.setBackground(myGreen);
        choicebutton.setPreferredSize(new Dimension(200, 200));
        this.add(choicebutton,BorderLayout.SOUTH);
        choicebutton.setBackground(myYellow);        
    }
}