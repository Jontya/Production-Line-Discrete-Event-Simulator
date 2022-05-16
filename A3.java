//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 16/05/22
***
*** A3:
*** 
**/
//---------------------------------------------------------------------------------------------------

import java.util.*;
import java.io.*;

public class A3{

    private static double M;
    private static double N;
    private static int QMax;

    public static void main (String args[]){
        if(args.length == 3){
            M = Double.parseDouble(args[0]);
            N = Double.parseDouble(args[1]);
            QMax = Integer.parseInt(args[2]);
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Invalid Paramaters");
        }
        
    }
}