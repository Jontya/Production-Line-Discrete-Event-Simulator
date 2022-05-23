import java.util.*;

public class InterStageStorage<T extends Widget> {

    private int QMax;
    private String ID;
    private Queue<T> queue;

    public InterStageStorage(int _QMax, String _ID){
        QMax = _QMax;
        ID = _ID;
        queue = new LinkedList<>();
    }

    public boolean push(T widget){
        if(queue.size() != QMax){
            queue.add(widget);
        }
        return false;    
    }

    public Widget pop(){
        return queue.remove();
        
    }

    public int getSize(){
        return queue.size();
    }



    
}
