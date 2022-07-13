

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class InterStageQueue<T extends Widget>{

    private int QMax; // Queue size limit
    private String queueID;
    private Queue<T> queue;

    private ArrayList<Stage> nextStage;

    private double timeCount;
    private double widgetCount;
    private int widgetSum;
    private int timeSum;

    // Constructor
    public InterStageQueue(int _QMax, String _queueID){
        QMax = _QMax;
        queueID = _queueID;

        widgetCount = 0;
        timeCount = 0;
        widgetSum = 0;
        timeSum = 0;

        queue = new LinkedList<>();
        nextStage = new ArrayList<>();
    }

    // Attempts to push a new item on the list (returns outcome based on the queue size limit)
    public boolean push(T widget){
        if(queue.size() != QMax){
            widgetSum += getSize();
            queue.add(widget);
            widgetCount++;
            return true;
        }
        return false;    
    }

    // Removes from the list
    public T pop(double currentTime){
        if(getSize() != 0){
            widgetSum += getSize();
            T temp = queue.remove();
            timeSum += currentTime - temp.getLastEndTime();
            widgetCount++;
            timeCount++;
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

    // Sets the next stage(s)
    public void setNextStage(Stage stageA, Stage stageB){
        nextStage.add(stageA);
        if(stageB != null){
            nextStage.add(stageB);
        }
    }

    // Gets the next stage
    public ArrayList<Stage> getNextStage(){
        return nextStage;
    }
    
    // Returns a string with all of the queues information (Average widgets at any time and Average time spent in the queue)
    public String queueReport(){
        double avgTime = timeSum / timeCount;
        double avgWidgets = widgetSum / widgetCount;
        return queueID + ":     AvgTime[t]: " + String.format("%,.2f", avgTime) + "     AvgWidgets: " + String.format("%,.2f", avgWidgets) + "\n";
    }
}
