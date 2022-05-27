//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 25/05/22
***
*** IDGenerator:
*** Singleton class used to give each widget a globally unique ID
**/
//---------------------------------------------------------------------------------------------------

import java.util.UUID;

public class IDGenerator{
    
    // Single instance
    private static IDGenerator instance = new IDGenerator();

    // Private constructor
    private IDGenerator(){

    }

    // Gets the single instance
    public static IDGenerator getInstance(){
        return instance;
    }

    // Creates the ID
    public String getID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
