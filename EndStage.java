//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 25/05/22
***
*** EndStage:
*** Extending from its parent class Stage, this class provides functionality for the final stage in
*** the production Line. It is able to process widgets stored in the stage, pull in new widgets and
*** move finished widgets into the finishedWidgets list.
**/
//---------------------------------------------------------------------------------------------------

import java.util.ArrayList;

public class EndStage extends Stage{

    private ArrayList<Widget> finishedWidgets;
    
    // Constructor
    public EndStage(InterStageQueue<Widget> _prevQueue, int _parallelCoefficient, String _stageID){
        prevQueue = _prevQueue;
        nextQueue = null;

        widget = null;

        finishedWidgets = new ArrayList<>();

        stageID = _stageID;

        prevTime = 0;
        timeBlocked = 0;
        timeStarved = 0;

        parallelCoefficient = _parallelCoefficient;
    }

    // Returns the list of finished widgets
    public ArrayList<Widget> getFinishedWidgets(){
        return finishedWidgets;
    }

    // Pulls widget from the previous inter stage storage queue and stores it in the current stage
    @Override
    public TimeEvent widgetIn(double currentTime){
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
    public ArrayList<TimeEvent> processStage(double currentTime){
        ArrayList<TimeEvent> newEvents = new ArrayList<>();
        if(prevQueue.getSize() > 0){ // if end stage is not starved
            widgetOut(); // pushes the widget out of the final stage
            newEvents.add(widgetIn(currentTime)); // creates a new time event
        }
        else{ // if end stage is starved
            timeStarved += currentTime - prevTime; // updates total time starved
            newEvents.add(new TimeEvent(-1, this)); // re-inserts into the PQ
        }
        prevTime = currentTime;
        return newEvents;
    }
}
