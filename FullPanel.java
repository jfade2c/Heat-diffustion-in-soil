package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FullPanel extends JPanel {
    private JFormattedTextField jft;
    private Integer tailleSys = 2;
    
    public FullPanel(){
        super();
        this.jft = new JFormattedTextField(this.tailleSys);
        jft.setColumns(4);
        
        ActionListener modifValeur = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evAction){
                tailleSys = ((Number)jft.getValue()).intValue();
            }
        };
        this.jft.addActionListener(modifValeur);
        
        this.add(new JLabel("Taille Système"));
        this.add(this.jft);
    }
}


