package metier;

/*
This class computes the depth at which there is the precise temperature after
the precise imputed time
*/

public class Depth {
    
    Temperature Tempcur = new Temperature();
    double xm;
    U uD = new U();
    public double getx (double T,double Ti, double T0, double t, double alpha){
        xm = 2*Math.sqrt(alpha*t)*uD.getufromT(T,Ti,T0);
        return xm;
    }
    public double getxinmeter (double xcm){
        xm=xcm/100;
        return xm;
    }
    public Depth(){
        
    }
}

