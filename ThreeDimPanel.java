package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import metier.Temperature;
import metier.Time;

public class ThreeDimPanel extends JPanel {
    private final double T;
    private final double T0;
    private final double Ti;
    private final double x;
    private final double t;
    private final double alpha;
    private final double width;
    private final double height;
    
    Temperature Tempcur = new Temperature();
    Time timecur = new Time();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    int marg = 60;//Margins for readability
    int frm; //index of the frame
    
    //Teintes de gris
    final Color myGrey = new Color(254,255,248);
    final Color myGreyline = new Color(166,166,166);
    final Color myGreyLab = new Color(118,113,113);
    
    //Couleurs "thermiques"
    final Color myBlack = new Color(0,0,0);
    final Color myBlue = new Color(29,0,120);
    final Color myPurple = new Color(110,21,161);
    final Color myPink = new Color(185,43,142);
    final Color myOrange = new Color(239,156,56);
    final Color myYellow = new Color(248,231,91);
    final Color myWhite = new Color(255,255,255);
    final Color[] coltherm = new Color[607];
    
    {//Smooth gradient of 606 colors
        this.coltherm[0] = myBlack;
        for(int bla=1; bla<101;bla++) {
            this.coltherm[bla] = new Color(Math.round(bla*29/100),0,Math.round(bla*120/100));
        }
        this.coltherm[101] = myBlue;
        for(int blu=1; blu<101;blu++) {
            this.coltherm[blu+101] = new Color(Math.round(29+blu*81/100),Math.round(blu*21/100),Math.round(120+blu*41/100));
        }
        this.coltherm[202] = myPurple;
        for(int pur=1; pur<101;pur++) {
            this.coltherm[pur+202] = new Color(Math.round(110+pur*75/100),Math.round(21+pur*22/100),Math.round(161-pur*19/100));
        }
        this.coltherm[303] = myPink;
        for(int pin=1; pin<101;pin++) {
            this.coltherm[pin+303] = new Color(Math.round(185+pin*54/100),Math.round(43+pin*113/100),Math.round(142-pin*86/100));
        }
        this.coltherm[404] = myOrange;
        for(int ora=1; ora<101;ora++) {
            this.coltherm[ora+404] = new Color(Math.round(239+ora*9/100),Math.round(156+ora*75/100),Math.round(54+ora*35/100));
        }
        this.coltherm[505] = myYellow;
        for(int yel=1; yel<101;yel++) {
            this.coltherm[yel+505] = new Color(Math.round(248+yel*7/100),Math.round(231+yel*24/100),Math.round(91+yel*164/100));
        }
        this.coltherm[606] = myWhite;
    }
    
    public ThreeDimPanel (double T, double T0, double Ti, double x, double t, double alpha, int Width, int Height, int fr){
        this.T = T;
        this.T0 = T0;
        this.Ti = Ti;
        this.x = x;
        this.t = t;
        this.frm = fr;
        this.alpha = alpha;
        this.width = Width;
        this.height = Height;
        
        
        this.setLayout(new BorderLayout()); 
        this.setBackground(new Color(243,241,229));
        this.setMinimumSize(new Dimension((int)(width),(int)(height)));
        
        
        
        JLabel titleLabel = new JLabel();        
        if(T0>Ti){//chauffage
            titleLabel.setText("Profil de température 3D après "+df.format(timecur.gettshou(this.t))+" h de chauffage, jusqu'à "+df.format(x)+"m");
        }
        else{//Refroidissement, T0<Ti
            titleLabel.setText("Profil de température 3D après "+df.format(timecur.gettshou(this.t))+" h de refroidissement, jusqu'à "+df.format(x)+"m");
        }
        
        titleLabel.setBackground(myGrey); //titre
        titleLabel.setFont(new Font( "FUTURA", Font.ITALIC,22));//defines font
        titleLabel.setForeground(Color.BLACK);
        this.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVisible(true);
        titleLabel.repaint();
        titleLabel.revalidate();
    }
    
    @Override
    public void paintComponent(Graphics grf){
        //instanciation of Graphics  
        super.paintComponent(grf);
        Graphics2D g = (Graphics2D)grf;
         
        final int widthg = getWidth();
        final int heightg = getHeight();
        final boolean refroi;
        if(T0<Ti){
            refroi=true;
        }else{
            refroi=false;
        }
        //Cooling,T0<Ti
        
        //To get a scalable temperature scale, that can adapt to the frame
        for (int i=0;i<607;i++){
            g.setPaint(this.coltherm[i]);
            g.fillOval((int)(0.95*widthg),(int)(0.93*heightg-(i/606.0)*(0.88*heightg)), 16, 16);
            //To have a temperature scale on the right
        }
        
        //Labels for min et max
        g.setPaint(myGreyLab);
        if(refroi==true){
            g.drawString(String.valueOf(df.format(Tempcur.getTC(Ti)))+"°C",(int)(0.88*widthg), (int)(0.05*heightg+12));//Tmax
            g.drawString(String.valueOf(df.format(Tempcur.getTC(T0)))+"°C",(int)(0.88*widthg), (int)(0.95*heightg));//Tmin
        }
        else{
            g.drawString(String.valueOf(df.format(Tempcur.getTC(Ti)))+"°C",(int)(0.88*widthg), (int)(0.95*heightg));//Tmin
            g.drawString(String.valueOf(df.format(Tempcur.getTC(T0)))+"°C",(int)(0.88*widthg), (int)(0.05*heightg+12));//Tmax
        }
        
        
        //Frame
        // Two rectangles
        g.setColor(myGreyLab);
        g.setStroke(new java.awt.BasicStroke(2));
        g.drawRect(widthg/5, widthg/5, widthg/3, widthg/3);//Big rectangle
        
        g.setColor(myGreyline);
        g.setStroke(new java.awt.BasicStroke(1));
        g.drawRect((int)(widthg/1.75),(int)(widthg/6.5),widthg/9,widthg/9);//Small rectangle
        
        
        //Lines
        g.setPaint(myGreyLab);
        g.draw(new Line2D.Double(widthg/5, widthg/5,(int) ((widthg/1.75)),(int)(widthg/6.5)));//A-> A'
        g.draw(new Line2D.Double(widthg/5+widthg/3,widthg/5,widthg/1.75+widthg/9,widthg/6.5));//B-> B'
        g.draw(new Line2D.Double(widthg/5+widthg/3,widthg/5+widthg/3,widthg/1.75+widthg/9,widthg/6.5+widthg/9));//C-> C'
        g.draw(new Line2D.Double(widthg/5,widthg/5+widthg/3,widthg/1.75,widthg/6.5+widthg/9));//D-> D'
        
        // x Axis
        int h=15;
        g.draw(new Line2D.Double((int)((widthg/5)-(widthg/20)),widthg/5,(int)((widthg/5)-(widthg/20)),(int)((widthg/5)+(widthg/3))));
        g.fillPolygon(new int[] {(int)((widthg/5)-(widthg/20)+h/4),(int)((widthg/5)-(widthg/20)-h/4),(int)((widthg/5)-(widthg/20))}, 
                new int[] {(int)((widthg/5)+(widthg/3)-h/2),(int)((widthg/5)+(widthg/3)-h/2),(int)((widthg/5)+(widthg/3)+h/2)}, 3);
        g.setFont(new Font("FUTURA", Font.ITALIC,16));
        g.drawString("Position verticale x",(int)(widthg/10),(int)((widthg/1.77)));
        g.drawString("en mètres",(int)(widthg/8.0),(int)((widthg/1.7)));
        
        
        
        
//        //Draw the points
        for (int y3D=(int)(widthg/5);y3D<(int)((widthg/5)+(widthg/3))+1;y3D=y3D+(widthg/100)){
            for (int x3D=(int)(widthg/5);x3D<(int)((widthg/5)+(widthg/3))+1;x3D=x3D+(widthg/100)){
                if(refroi==true){//cooling,T0<Ti
                    g.setPaint(coltherm
                            [(int)(((Tempcur.getT(((((double)(y3D)-(widthg/5)))/(widthg/3))*x, Ti, T0, alpha, ((double)(frm)/650)*t)-T0)/(Ti-T0))*606)]);
                    g.fill(new Ellipse2D.Double(x3D,y3D,10,10));
                }
                else{//heating, T0>Ti
                    g.setPaint(coltherm
                            [((int)(((Tempcur.getT(((((double)(y3D)-(widthg/5)))/(widthg/3))*x, Ti, T0, alpha, ((double)(frm)/650)*t)-Ti)/(T0-Ti))*606))]);
                    g.fill(new Ellipse2D.Double(x3D,y3D,10,10));
                }
            }
        }
    }
}
