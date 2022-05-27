//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 25/05/22
***
*** StartStage:
*** Extending from its parent class Stage, this class provides functionality for the start stage in
*** the production Line. It is able to process widgets stored in the stage, create new widgets and
*** push widgets into the next inter-stage storage queue.
**/
//---------------------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.Random;

public class StartStage extends Stage{

    private IDGenerator IDGen;

    // Constructor
    public StartStage(InterStageQueue<Widget> _nextQueue, int _M, int _N, double _maxTime, int _parallelCoefficient, String _stageID){
        prevQueue = null;
        nextQueue = _nextQueue;

        widget = null;

        stageID = _stageID;

        prevTime = 0;
        timeBlocked = 0;
        timeStarved = 0;

        parallelCoefficient = _parallelCoefficient;

        d = new Random();
        IDGen = IDGenerator.getInstance();
        setProcessingParams(_M, _N, _maxTime);
    }

    // Creates new widgets
    private Widget createNewWidget(double startTime, double endTime){
        String widgetID = IDGen.getID();
        Widget newWidget;

        if(stageID.charAt(stageID.length()-1) == 'a' || stageID.charAt(stageID.length()-1) == 'b'){
            newWidget = new Widget(widgetID + "-" + stageID.substring(stageID.length() - 1));
            newWidget.addEvent(startTime, endTime, stageID);
            return newWidget;
        }
        newWidget = new Widget(widgetID);
        newWidget.addEvent(startTime, endTime, stageID);
        return newWidget;
    }

    // Creates a new time event for each new widget
    @Override
    public TimeEvent widgetIn(double currentTime){
        double endTime = getProcessingTime() + currentTime;
        widget = createNewWidget(currentTime, endTime);
        return new TimeEvent(widget.getLastEndTime(), this);
    }

    // Pushes widget into the next inter-stage storage queue
    @Override
    public void widgetOut(){
        nextQueue.push(widget);
        widget = null;
    }

    // Processes the current stage
    @Override
    public ArrayList<TimeEvent> processStage(double currentTime){
        ArrayList<TimeEvent> newEvents = new ArrayList<>();
        if(!nextQueue.isFull()){ // if start stage is not blocked
            widgetOut(); // pushes widget out of the stage
            newEvents.add(widgetIn(currentTime)); // creates a new widget and time event

            for(int i = 0; i < nextStage.size(); i++){ // loops through each stage (2 if running in parallel)
                if(nextStage.get(i).widget == null && nextQueue.getSize() > 0){ // checks if next stage doesn't have a widget and the next queue isn't empty
                    newEvents.add(nextStage.get(i).widgetIn(currentTime)); // next stage pulls in a new widget and a new time event is created
                }
            }
        }
        else{ // if start start stage is blocked
            timeBlocked += currentTime - prevTime; // updates total time blocked
            for(int i = 0; i < nextStage.size(); i++){ // Loops through each sage (2 if running in parallel)
                if(nextStage.get(i).widget == null && nextQueue.getSize() > 0){
                    newEvents.add(nextStage.get(i).widgetIn(currentTime)); // next stage pulls in a new widget and a new time event is created
                }
            }
            if(!nextQueue.isFull()){ // if the next queue is no longer full
                widgetOut(); // widget gets pushed onto the next queue 
                newEvents.add(widgetIn(currentTime)); // new widget is made and a new time event is created
            }
            else{
                newEvents.add(new TimeEvent(-1, this)); // will be re-inserted into the PQ with the time being peek().getTime()
            }
        }
        prevTime = currentTime; // prev time is updated
        return newEvents;
    }
    
}
