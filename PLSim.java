//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 20/05/22
***
*** A3:
*** Entry point for the program, used to check CLI parameters and begin production
**/
//---------------------------------------------------------------------------------------------------

public class A3{
    public static void main(String[] args){
        // Checks for correct number of CLI parameters
        if(args.length == 3){
            ProductionLine productionLine = new ProductionLine(); // Creates a new production line
            productionLine.beginProduction(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])); // Begins production
            System.out.println(productionLine.productionReport());
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Invalid Parameters");
        }
    }
}
