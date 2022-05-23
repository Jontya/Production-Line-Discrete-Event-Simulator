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

    public static void main (String args[]){
        if(args.length == 3){
            ProductionLine productionLine = new ProductionLine(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            productionLine.beginProduction();
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Invalid Paramaters");
        }
    }
}