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
