//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 24/05/22
***
*** InterStageQueue:
*** This class wraps around a linkedList and acts as a queue, this is used for the inter-stage storage
*** queue between each stage and provides the basic queries required to move items through each stage.
**/
//---------------------------------------------------------------------------------------------------

import java.util.Queue;
import java.util.LinkedList;

public class InterStageQueue<T extends Widget>{

    private static double maxTime;

    private int QMax; // Queue size limit
    private String queueID;
    private Queue<T> queue;

    private double prevTime;

    private int widgetCount;
    private double timeCount;
    private double widgetsInQueue;

    // Constructor
    public InterStageQueue(int _QMax, String _queueID){
        QMax = _QMax;
        queueID = _queueID;

        prevTime = 0;

        widgetCount = 0;
        timeCount = 0;
        widgetsInQueue = 0;

        queue = new LinkedList<>();
    }

    // Second Constructor
    public InterStageQueue(int _QMax, String _queueID, double _maxTime){
        this(_QMax, _queueID);
        maxTime = _maxTime;
    }

    // Attempts to push a new item on the list (returns outcome based on the queue size limit)
    public boolean push(T widget){
        if(queue.size() != QMax){
            double currentTime = widget.getLastEndTime();
            double timeDifference = currentTime - prevTime;
            prevTime = currentTime;

            widgetsInQueue += timeDifference * getSize();

            queue.add(widget);
            return true;
        }
        return false;    
    }

    // Removes from the list
    public T pop(double currentTime){
        if(getSize() != 0){
            double timeDifference = currentTime - prevTime;
            prevTime = currentTime;
            widgetsInQueue += timeDifference * getSize();
            widgetCount++;

            T temp = queue.remove();
            timeCount += currentTime - temp.getLastEndTime();
            return temp;
        }
        return null;
    }

    // Checks if the list is full
    public boolean isFull(){
        if(getSize() == QMax){
            return true;
        }
        return false;
    }

    // Gets list size 
    public int getSize(){
        return queue.size();
    }
    
    public String queueReport(){
        double avgTime = timeCount / widgetCount;
        double avgWidgets = widgetsInQueue / maxTime;
        return queueID + ":     AvgTime[t]: " + String.format("%,.2f", avgTime) + "     AvgWidgets: " + String.format("%,.2f", avgWidgets) + "\n";
    }
}
