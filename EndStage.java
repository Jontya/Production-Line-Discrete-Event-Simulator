

import java.util.ArrayList;

public class EndStage extends Stage{

    private ArrayList<Widget> finishedWidgets;
    
    // Constructor
    public EndStage(InterStageQueue<Widget> _prevQueue, int _parallelCoefficient, String _stageID){
        super();
        prevQueue = _prevQueue;
        nextQueue = null;
        finishedWidgets = new ArrayList<>();
        stageID = _stageID;
        parallelCoefficient = _parallelCoefficient;
    }

    // Returns the list of finished widgets
    public ArrayList<Widget> getFinishedWidgets(){
        return finishedWidgets;
    }

    // Pulls widget from the previous inter stage storage queue and stores it in the current stage
    @Override
    public TimeEvent widgetIn(double currentTime){
        if(timeStarved == 0){
            timeStarved += currentTime;
        }
        widget = prevQueue.pop(currentTime);
        widget.addEvent(currentTime, getProcessingTime() + currentTime, stageID);
        return new TimeEvent(widget.getLastEndTime(), this); // creates new time event
    }

    // Moves widget into the completed widgets list
    @Override
    public void widgetOut(){
        finishedWidgets.add(widget); 
        widget = null;
    }

    // Processes the current stage
    @Override
    public TimeEvent processStage(double currentTime){
        if(prevQueue.getSize() > 0){ // If stage is not starved
            widgetOut(); // Pushes widget to the finished widget list
            prevTime = currentTime; // Updates prev time
            return widgetIn(currentTime); // Pulls in a new widget and returns a new time event
        }
        timeStarved += currentTime - prevTime; // Else updates time starved
        prevTime = currentTime; // Updates prev time
        return new TimeEvent(-1, this); // Returns a time event that needs to be re inserted at peek().getTime()
    }
}
