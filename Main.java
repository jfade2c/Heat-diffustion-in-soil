package application;
import ihm.Window;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import metier.*;


public class Main{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window window = new Window();
            }
        });
    }
}