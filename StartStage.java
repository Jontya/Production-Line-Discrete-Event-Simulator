import java.util.Random;

public class StartStage extends Stage{

    private IDGenerator IDGen;

    // Constructor
    public StartStage(InterStageQueue<Widget> _nextQueue, int _M, int _N, double _maxTime, int _parallelCoefficient, String _stageID){
        super();

        prevQueue = null;
        nextQueue = _nextQueue;
        stageID = _stageID;
        parallelCoefficient = _parallelCoefficient;

        d = new Random();
        IDGen = IDGenerator.getInstance();
        setProcessingParams(_M, _N, _maxTime);
    }

    // Creates new widgets
    private Widget createNewWidget(double startTime, double endTime){
        String widgetID = IDGen.getID();
        Widget newWidget;

        // Adds the final stage suffix
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
    public TimeEvent processStage(double currentTime){
        if(!nextQueue.isFull()){ // If stage is not blocked
            widgetOut(); // Pushes widget to the next queue
            prevTime = currentTime; // Updates prev time
            return widgetIn(currentTime); // Creates a new widget and returns a new time event
        }
        timeBlocked += currentTime - prevTime; // Else updates time blocked
        prevTime = currentTime; // Updates prev time
        return new TimeEvent(-1, this); // Returns a time event that needs to be re inserted at peek().getTime()
    }
    
}
