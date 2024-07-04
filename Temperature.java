package metier;

/*
This class computes the temperature corresponding to the imputed duration and
depth
*/

public class Temperature {
    double TC;
    U uT = new U();
    public double getT(double x, double Ti, double T0, double alpha, double t) {
        TC = Ti - ((Ti-T0)*uT.getErfc((x)/(2*Math.sqrt(alpha*t))));
        return TC;
    }
    double TK;
    public double getTK(double TC) {
        TK= TC+273.15;
        return TK;
    }//Notes sur K/°C: user enters temperature in °C (user-friendly values),
    // and temperature is each time computed in K (scientific values)
    public double getTC(double TK) {
        TC= TK-273.15;
        return TC;
    }
    public Temperature(){
        
    }
}


