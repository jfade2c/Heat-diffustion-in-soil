package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import metier.Depth;
import metier.U;

import ihm.TwoDimPanel;
import java.util.Timer;
import java.util.TimerTask;
import metier.Temperature;
import metier.Time;


 public class DepthPanel extends JPanel {
    private int centerWidth;
    private int centerHeight;

    private Color myBlue = new Color(220,227,242);
    private JPanel resultPanel1 = new JPanel();
    private JPanel resultPanel2 = new JPanel();
    private JComboBox  matChoiceSelector = new JComboBox();
    Depth Depthcur = new Depth();
    Time Timecur = new Time();
    
    U ucur = new U();

    private double T;
    private double t;
    private double T0;
    private double Ti;
    private double alpha;
    private static final DecimalFormat df = new DecimalFormat("0.000");
    private JFrame frame ;

    public DepthPanel(int centerPaneWidth, int centerPaneHeight,Window fen) {
        this.centerHeight = centerPaneHeight;
        this.centerWidth = centerPaneWidth;

        this.setLayout(new BorderLayout());
        this.setBackground(myBlue);

         //Creates 2 régions in TempPanel
        
        //WEST
        JPanel westPanel = new JPanel();
        JPanel dataPanel = new JPanel();//data imput field
        westPanel.setBackground(myBlue);
        QuestionsResults(dataPanel);
        westPanel.add(dataPanel);
        this.add(westPanel, BorderLayout.WEST);
        
        //NORTH
        JPanel northPanel = new JPanel();
        northPanel.setBackground(myBlue);
        JLabel bigtitleLabel = new JLabel("Calculation of corresponding depth after cooling/heating:");
        bigtitleLabel.setFont(new Font( "Futura", Font.BOLD,20));   //Sets font
        bigtitleLabel.setForeground(Color.BLACK);    //Sets font color
        northPanel.add(bigtitleLabel);
        this.add(northPanel, BorderLayout.NORTH);

    }   //End of the constructor


    private void QuestionsResults(JPanel QApan) {//computes, doesn't outputs
        // QApan = Question + Answer Panel
        QApan.setLayout(null);//doesn't format panQR
        QApan.setPreferredSize(new Dimension(250, 800));
        QApan.setBackground(myBlue);
        QApan.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        JLabel qrtitleLabel1 = new JLabel("Temperature and");
        qrtitleLabel1.setFont(new Font( "Futura", Font.ROMAN_BASELINE,20));
        qrtitleLabel1.setForeground(Color.BLACK);    //Sets font color
        qrtitleLabel1.setBounds(10, 25, 225, 35);
        qrtitleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        qrtitleLabel1.setBorder(BorderFactory.createLineBorder(myBlue));
        QApan.add(qrtitleLabel1);
        
        JLabel qrtitleLabel2 = new JLabel("duration imput :");
        qrtitleLabel2.setFont(new Font( "Futura", Font.ROMAN_BASELINE,20));
        qrtitleLabel2.setForeground(Color.BLACK);    //Sets font color
        qrtitleLabel2.setBounds(10, 55, 225, 35);
        qrtitleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        qrtitleLabel2.setBorder(BorderFactory.createLineBorder(myBlue));
        QApan.add(qrtitleLabel2);


        // New combobox
        JPanel MatChoicePan = new JPanel();
        String[] material = {"Concrete (default)", "Asphalt", "Cobblestone",
            "Dry sandy soil", "Humid sandy soil"};
        JComboBox MatChoice = new JComboBox(material);
        MatChoicePan.add(MatChoice);
        MatChoicePan.setBounds(10, 220, 225, 40);
        MatChoicePan.setBackground(myBlue);
        QApan.add(MatChoicePan);
        
        MatChoice.addActionListener((ActionEvent evt) -> {
            System.out.println("Combobox clicked");
            if(MatChoice.getSelectedItem()==material[0]){
            alpha=7.0E-7;
            }
            if(MatChoice.getSelectedItem()==material[1]){
                alpha=3.6E-7;
            }
            if(MatChoice.getSelectedItem()==material[2]){
                alpha=11.8E-7;
            }
            if(MatChoice.getSelectedItem()==material[3]){
                alpha=2.0E-7;
            }       
            if(MatChoice.getSelectedItem()==material[4]){
                alpha=3.3E-7;
            }
        }); //End of action listener JCombobox



        //Construction of a soil and water temperature panel
        JPanel waterTempPanel = new JPanel();
        waterTempPanel.setLayout(new BoxLayout(waterTempPanel, BoxLayout.LINE_AXIS)); //Disposition du texte 
        waterTempPanel.setBackground(myBlue);

        JLabel waterTempReadingLabel = new JLabel(" Water temperature (°C): ");
        waterTempReadingLabel.setFont(new Font( "Futura", Font.ROMAN_BASELINE,12));
        waterTempReadingLabel.setForeground(Color.BLACK);    
        waterTempPanel.add(waterTempReadingLabel);

        //JFormattedTextField allows to imput text
        JFormattedTextField waterTempInput = new JFormattedTextField(18);
        waterTempInput.setBackground(Color.WHITE);
        waterTempPanel.add(waterTempInput);
        waterTempPanel.setBounds(10,100,225,20);
        QApan.add(waterTempPanel);


        JPanel floorTempPanel = new JPanel();
        floorTempPanel.setLayout(new BoxLayout(floorTempPanel, BoxLayout.LINE_AXIS));
        floorTempPanel.setBackground(myBlue);

        JLabel floorTempLabel = new JLabel(" Ground temperature (°C): ");
        floorTempLabel.setFont(new Font( "Futura", Font.ROMAN_BASELINE,12));
        floorTempLabel.setForeground(Color.BLACK);    
        floorTempPanel.add(floorTempLabel);

        JFormattedTextField floorTempInput = new JFormattedTextField(40);
        floorTempInput.setBackground(Color.WHITE);
        floorTempPanel.add(floorTempInput);
        floorTempPanel.setBounds(10,120,225,20);
        QApan.add(floorTempPanel);


        //Construction of a panel for depth x
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.LINE_AXIS));
        timePanel.setBackground(myBlue);

        JLabel timeReadingLabel = new JLabel(" Temps (min): ");
        timeReadingLabel.setFont(new Font( "Futura", Font.ROMAN_BASELINE,12));
        timeReadingLabel.setForeground(Color.BLACK);    
        timePanel.add(timeReadingLabel);

        JFormattedTextField timeInput = new JFormattedTextField(0.0);
        timeInput.setBackground(Color.WHITE);
        timePanel.add(timeInput);
        timePanel.setBounds(10,160,225,20);
        QApan.add(timePanel);


        // Panel for Time t
        JPanel tempPanel = new JPanel();   
        tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.LINE_AXIS));
        tempPanel.setBackground(myBlue);

        JLabel tempLabel = new JLabel(" Température à x et t (°C): ");
        tempLabel.setFont(new Font( "Futura", Font.ROMAN_BASELINE,12));
        tempLabel.setForeground(Color.BLACK);   
        tempPanel.add(tempLabel);

        JFormattedTextField tempInput = new JFormattedTextField(0.0); //zone permettant de rentrer la température 
        tempInput.setBackground(Color.WHITE);
        tempPanel.add(tempInput);
        tempPanel.setBounds(10,180,225,20);
        QApan.add(tempPanel);


        // Construction of ok, reset, graph2D, graph3D buttons
        JPanel buttonPanel = new JPanel();  // Panel for 2 JButtons
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(myBlue);
        
        JPanel buttonPanel2D = new JPanel();  // Panel for 2 JButtons
        buttonPanel2D.setBackground(myBlue);
        
        JPanel buttonPanel3D = new JPanel();  // Panel for 2 JButtons
        buttonPanel3D.setBackground(myBlue);

        buttonPanel.add(new JLabel(" "));   // Blank space
        
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font( "Futura", Font.ROMAN_BASELINE,12));
        okButton.setForeground(Color.BLACK);  
        buttonPanel.add(okButton);
        
        buttonPanel.add(new JLabel("                "));   // Blank space
        
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font( "Futura", Font.ROMAN_BASELINE,12));
        resetButton.setForeground(Color.BLACK);    
        resetButton.setEnabled(false);
        buttonPanel.add(resetButton);
        

        buttonPanel.add(new JLabel("    "));   // Blank space
        
        JButton graph2DButton = new JButton("Diplay 2D modeling");
        graph2DButton.setFont(new Font( "Futura", Font.ROMAN_BASELINE,12));
        graph2DButton.setForeground(Color.BLACK);    
        graph2DButton.setEnabled(false);
        buttonPanel2D.add(graph2DButton);

        JButton graph3DButton = new JButton("Diplay 3D modeling");
        graph3DButton.setFont(new Font( "Futura", Font.ROMAN_BASELINE,12));
        graph3DButton.setForeground(Color.BLACK);    
        graph3DButton.setEnabled(false);
        buttonPanel3D.add(graph3DButton);

        buttonPanel.setBounds(15,270,225,30);
        QApan.add(buttonPanel);
        
        buttonPanel2D.setBounds(15,320,225,35);
        QApan.add(buttonPanel2D);
        
        buttonPanel3D.setBounds(15,370,225,35);
        QApan.add(buttonPanel3D);


        // Construction of the results display
        resultPanel1.setBackground(Color.WHITE);
        resultPanel1.setBounds(10, 600, 225, 60);
        resultPanel2.setBackground(Color.WHITE);
        resultPanel2.setBounds(10, 650, 225, 70);
        JLabel resultLabel1 = new JLabel();
        JLabel resultLabel2 = new JLabel();

        resultLabel1.setText("Depth is : ");
        resultLabel1.setFont(new Font( "Futura", Font.ROMAN_BASELINE,19));
        resultLabel1.setForeground(Color.BLACK);    
        resultLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel2.setFont(new Font( "Futura", Font.BOLD,35));
        resultLabel2.setForeground(Color.BLACK);    
        resultLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        resultPanel1.add(resultLabel1);
        resultPanel2.add(resultLabel2);



        // ActionListener on OK button
        okButton.addActionListener((ActionEvent evt) -> {
           
            System.out.println("OK button activated");

            t = ((Number)timeInput.getValue()).doubleValue();
            T = ((Number) tempInput.getValue()).doubleValue();
            T0 = ((Number) waterTempInput.getValue()).doubleValue();
            Ti = ((Number) floorTempInput.getValue()).doubleValue();


            if(T0>Ti){//heating, T0>Tis
                if(T>T0||T<Ti||t < 0.0||T<-273.15||Ti<-273.15||T0<-273.15){
                    System.out.println("Error frame displayed");
                JOptionPane jop = new JOptionPane();
                JOptionPane.showMessageDialog(frame, "Please enter only positive values for x and t to calculate the temperature. Absolute temperatures are positive."
                        + "\nIt is necessary to press reset before modifying your values.");
                }
                else{
                    resultLabel2.setText(df.format(Depthcur.getx(T, Ti, T0,t*60, alpha))+ "m"); //Depthcur.get permet d'appeler la commande et df permet de réduire les décimales du résultats
                    QApan.add(resultPanel1);
                    QApan.add(resultPanel2);
                }
            }
            else{//coooling, T0<Ti
                if(T<T0||T>Ti||t < 0.0||T<-273.15||Ti<-273.15||T0<-273.15){
                    System.out.println("Error frame displayed");
                JOptionPane jop = new JOptionPane();
                JOptionPane.showMessageDialog(frame, "Please enter only positive values for x and t to calculate the temperature. Absolute temperatures are positive."
                        + "\nIt is necessary to press reset before modifying your values.");
                }
                else{
                    resultLabel2.setText(df.format(Depthcur.getx(T, Ti, T0,t*60, alpha))+ "m"); //Depthcur.get permet d'appeler la commande et df permet de réduire les décimales du résultats
                    QApan.add(resultPanel1);
                    QApan.add(resultPanel2);
                }
            }

            QApan.updateUI();//update panQR to display result panel
            
          
            graph2DButton.setEnabled(true);
            graph3DButton.setEnabled(true);
            resetButton.setEnabled(true);
            okButton.setEnabled(false);
        }); //End of action listener 

       
        // Configuration of an ActionListener on reset button
        resetButton.addActionListener((ActionEvent evt) -> {
            System.out.println("Bouton reset activé");
            // Resetting the value of the text fields to 0.0
            tempInput.setValue(0);
            timeInput.setValue(0);
            waterTempInput.setValue(18);
            floorTempInput.setValue(40);

            resetButton.setEnabled(false);
            okButton.setEnabled(true);
        }); // End of reset action listener 
       

        // Configuration of an ActionListener on graph2D button
        graph2DButton.addActionListener((ActionEvent evt) -> {
            System.out.println("graph2D button activated");
            JFrame frame2D = new JFrame();
            frame2D.setSize(new Dimension(1400, 1000));
            TwoDimPanel twoDimcur = new TwoDimPanel(T, T0, Ti, Depthcur.getx(T, Ti, T0, t*60, alpha), t*60,alpha, (int)(800), (int)(600));
            frame2D.add(twoDimcur, BorderLayout.CENTER);
            frame2D.setLocation(430, 110);
            frame2D.setVisible(true);
        }); // End of graph2D action listener
        
        // Configuration of an ActionListener on graph3D button
        graph3DButton.addActionListener((ActionEvent evt) -> {
            System.out.println("graph3D button activated");
            Temperature Tempcur = new Temperature();

            // Video made of threeDimPanels frames
            
            JFrame frame3D = new JFrame();
            frame3D.setSize(new Dimension(1300, 900));

            Timer timer = new Timer();

            TimerTask task = new TimerTask() {
                private int fr = 0;
                @Override
                public void run() {
                    ThreeDimPanel threedimcur = new ThreeDimPanel(Tempcur.getTK(T), Tempcur.getTK(T0), Tempcur.getTK(Ti),Depthcur.getx(T, Ti, T0,t*60, alpha),t*60,alpha,1000,900,fr);
                    frame3D.add(threedimcur,BorderLayout.CENTER);
                    threedimcur.updateUI();
                    fr++;
                    if (fr==650){
                        fr=0;
                    }
                }
            };
            timer.schedule(task,0, 7);

            frame3D.setLocation(430, 110);
            frame3D.setVisible(true);
            }); // End of graph3D action listener 
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
 }

