public class InternalStage extends Stage{

    // Constructor
    public InternalStage(InterStageQueue<Widget> _prevQueue, InterStageQueue<Widget> _nextQueue, int _parallelCoefficient, String _stageID){
        super();
        prevQueue = _prevQueue;
        nextQueue = _nextQueue;
        stageID = _stageID;
        parallelCoefficient = _parallelCoefficient;
    }

    // Pulls widget from the previous inter-stage storage queue
    @Override
    public TimeEvent widgetIn(double currentTime){
        if(timeStarved == 0){
            timeStarved += currentTime;
        }
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
    public TimeEvent processStage(double currentTime){
        if(!nextQueue.isFull() && prevQueue.getSize() > 0){ // If the stage is not starved or blocked
            widgetOut(); // pushes widget to the next queue
            prevTime = currentTime; // updates the time
            return widgetIn(currentTime); // pulls in a widget from the prev queue and creates a new time event
        }
        else{
            if(nextQueue.isFull()){ // Stage is blocked
                timeBlocked += currentTime - prevTime; // Updates the time blocked
            }
            if(prevQueue.getSize() == 0){ // Stage is starved
                timeStarved += currentTime - prevTime; // Updates the time starved
            }
        }
        prevTime = currentTime; // Updates previous time
        return new TimeEvent(-1, this); // Returns a time event that needs to be re inserted at peek().getTime()
    }
}
