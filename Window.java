package ihm;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import static java.awt.Toolkit.getDefaultToolkit;
import javax.swing.JFrame;


public class Window extends JFrame{

  
    private WelcomePanel wP;
    
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    
    public Window() {
        super();
        
        this.setTitle("Modeling the cooling/heating of soil");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(0,0);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.wP = new WelcomePanel(50,50,this);
        
        this.add(wP);
        
        this.pack();
        this.setVisible(true);
    }
    
}