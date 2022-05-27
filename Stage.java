//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 25/05/22
***
*** Stage:
*** Stage is the abstract parent class for any stage in the production line. it defines the variables
*** needed to function and provides the functionality for some global query methods while defining
*** the abstract methods that require unique functionality.
**/
//---------------------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.Random;

public abstract class Stage {

    private static int M;
    private static int N;
    private static double maxTime;
    protected static Random d;

    protected Widget widget;
    
    protected String stageID;

    protected InterStageQueue<Widget> nextQueue;
    protected InterStageQueue<Widget> prevQueue;

    protected boolean parallel;
    protected ArrayList<Stage> nextStage;

    protected int parallelCoefficient;

    protected double prevTime;
    protected double timeStarved;
    protected double timeBlocked;

    // Sets processing parameters
    protected void setProcessingParams(int _M, int _N, double _maxTime){
        M = _M;
        N = _N;
        maxTime = _maxTime;
    }

    // Gets processing time
    protected double getProcessingTime(){
        return (parallelCoefficient * M) + (parallelCoefficient * N) * (d.nextDouble() - 0.5);
    }

    // Gets the total time blocked
    public double getTimeBlocked(){
        return timeBlocked;
    }

    // Gets the total time starved
    public double getTimeStarved(){
        return timeStarved;
    }

    //
    public String stageReport(){
        double stageWork = (100 - ((timeBlocked + timeStarved) / maxTime) * 100);
        return stageID + ":     Work[%]: "  + String.format("%,.2f", stageWork) + "%     Starve[t]: " + String.format("%,.2f", timeStarved) + "     Block[t]: " + String.format("%,.2f", timeBlocked) + "\n";
    }

    // Creates a new time event
    public TimeEvent getNewTimeEvent(){
        return new TimeEvent(widget.getLastEndTime(), this);
    }

    // Sets the next stage(s)
    public void setNextStage(Stage stage, Stage parallelStage){
        nextStage = new ArrayList<>();
        nextStage.add(stage);
        parallel = false;
        if(parallelStage != null){
            parallel = true;
            nextStage.add(parallelStage);
        }
    }

    // Abstract Methods
    public abstract TimeEvent widgetIn(double currentTime);

    public abstract void widgetOut();

    public abstract ArrayList<TimeEvent> processStage(double currentTime);
}
