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

    public void push(T widget){
        
    }

    public Widget pop(){
        Widget removedHead = queue.peek();
        return removedHead;
        
    }

    public int getSize(){
        return queue.size();
    }



    
}
