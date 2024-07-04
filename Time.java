package metier;

/*
This class computes the duration corresponding to the imputed depth and
temperature at said depth
*/

public class Time {
    double tsec;
    double thou;
    double tdays;
    double tmin;
    U ucur = new U();
    public double gettsec(double T,double Ti, double T0, double x, double alpha) {
        tsec = (x*x)/(4*alpha*(ucur.getufromT(T,Ti,T0)*ucur.getufromT(T,Ti,T0)));
        return tsec;
    }
    public double getthous (double thours){
        tsec = thours*3600;
        return tsec;
    }
    public double gettshou (double tsec){
        thou = tsec/3600;
        return thou;
    }
    public Time(){
        
    }
}