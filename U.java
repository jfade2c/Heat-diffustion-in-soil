package metier;

public class U {
    double u;
    
    public double getufromT (double T, double Ti, double T0){
//      Bisection Method in N Steps for a Result Accurate to 8 Decimal Places
//      Initial Setup: We consider a method that is equal to zero in the interval [0, 4].
//      Function Definition: Let the function be the error function : 
//      erfc(u) - erfc(T - Ti) / (T0 - Ti)
//      Step 1: Calculates the midpoint of the interval [0, 4], denoted as point "c".
//      Step 2: Depending on the value of erfc(c), replace either erfc(0) 
//      or erfc(3) so that there remains a zero in the interval of the functions.
//      Step 3: Repeat the process until the difference between the function 
//      values is less than 1e-7.
//      Step 4: Returns the value of u.

        int j=2;//To begin with intu=intu+-(intu*(1/2))
        double intu = 1;//Initialisation, u is between 0 et (erfc(4))~0
        while(Math.abs(getErfc(intu)-((T-Ti)/(T0-Ti)))>=0.0000001){
            if ((getErfc(intu)-((T-Ti)/(T0-Ti)))>=0){//dans []
                if ((getErfc(intu)==getErfc((T-Ti)/(T0-Ti)))){
                    break;//Not to modify intu
                }
                intu=intu+(intu*Math.pow(2, -0.1*j));
            } 
            else {
                intu=intu-(intu*Math.pow(2, -0.1*j));
            }
            j++;
        }
        return intu;
    }//end of getufromT
    
    public double getErfc (double u) {
        double erfc;
        erfc = 1-getErf(u);
        return erfc;
    }//end of getErfc
    
    public double getErf (double u) {
        double erf;
        double xi; //xi = a+i*((b-a)/n)
        double xip1; //xipl = xi + 1
        double ximid; //middle
        int i;
        
        //Initialization
        erf = 0;
        for (i=0; i<(500);i++){
            xi = (i*u)/500;
            xip1 = xi+u/500;
            ximid = (xi+xip1)/2;
            erf = erf + (Math.exp(-(xi*xi))+(4*Math.exp(-(ximid*ximid)))+Math.exp(-(xip1*xip1)));
        }//end of for loop
        erf = erf*(u/(Math.sqrt(Math.PI)*(3*500)));
        return erf;
    }///end of getErf
    
    public U(){//Constructor
        
    }
}

