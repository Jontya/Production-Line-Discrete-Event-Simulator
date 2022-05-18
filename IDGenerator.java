import java.util.UUID;

public class IDGenerator{
    
    private static IDGenerator instance = new IDGenerator();

    private IDGenerator(){

    }

    public static IDGenerator getInstance(){
        return instance;
    }

    public String getID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
