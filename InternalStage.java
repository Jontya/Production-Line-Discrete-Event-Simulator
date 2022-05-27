//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 25/05/22
***
*** InternalStage:
*** Extending from its parent class Stage, this class provides functionality for any internal stage
*** in the production Line. It is able to process widgets stored in the stage, pull in new widgets
*** and push widgets into the next inter-stage storage queue.
**/
//---------------------------------------------------------------------------------------------------

import java.util.ArrayList;

public class InternalStage extends Stage{

    // Constructor
    public InternalStage(InterStageQueue<Widget> _prevQueue, InterStageQueue<Widget> _nextQueue, int _parallelCoefficient, String _stageID){
        prevQueue = _prevQueue;
        nextQueue = _nextQueue;

        widget = null;

        stageID = _stageID;

        prevTime = 0;
        timeBlocked = 0;
        timeStarved = 0;

        parallelCoefficient = _parallelCoefficient;
    }

    // Pulls widget from the previous inter-stage storage queue
    @Override
    public TimeEvent widgetIn(double currentTime){
        widget = prevQueue.pop(currentTime); // pops from previous queue
        widget.addEvent(currentTime, getProcessingTime() + currentTime, stageID); // widgets end time is updated
        return new TimeEvent(widget.getLastEndTime(), this); // new time event is created
 
    }

    // Pushes widget into the next queue
    @Override
    public void widgetOut(){
        nextQueue.push(widget);
        widget = null;
    }

    // Processes the current stage
    @Override
    public ArrayList<TimeEvent> processStage(double currentTime){
        ArrayList<TimeEvent> newEvents = new ArrayList<>();
        if(prevQueue.getSize() > 0 && !nextQueue.isFull()){ // if stage is not starved or blocked
            widgetOut(); // pushes widget into the next queue
            newEvents.add(widgetIn(currentTime)); // pulls the next widget in and creates a new time event

            for(int i = 0; i < nextStage.size(); i++){
                if(nextStage.get(i).widget == null && nextQueue.getSize() > 0){
                    newEvents.add(nextStage.get(i).widgetIn(currentTime)); // Next stage pulls in a new widget and creates a new time event
                }
            }
        }
        else{
            if(nextQueue.isFull()){ // if stage is blocked
                timeBlocked += currentTime - prevTime; // updates the time blocked
                for(int i = 0; i < nextStage.size(); i++){
                    if(nextStage.get(i).widget == null){
                        newEvents.add(nextStage.get(i).widgetIn(currentTime)); // Next stage pulls in a new widget and creates a new time event
                    }
                }
                if(prevQueue.getSize() > 0){
                    widgetOut(); // Pushes widget onto the next queue
                    newEvents.add(widgetIn(currentTime)); // pulls in a new widget and creates a new time event
                }
                else{
                    newEvents.add(new TimeEvent(-1, this)); // will be re-inserted into the PQ with the time being peek().getTime()
                }
            }
            else{ // if stage is starved
                timeStarved += currentTime - prevTime; // updates the time starved
                newEvents.add(new TimeEvent(-1, this)); // will be re-inserted into the PQ with the time being peek().getTime()
            }
        }
        prevTime = currentTime; // updates previous time
        return newEvents;
    }
}
