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

    protected int parallelCoefficient;

    protected double prevTime;
    protected double timeStarved;
    protected double timeBlocked;

    // Super class constructor
    public Stage(){
        prevTime = 0;
        timeStarved = 0;
        timeBlocked = 0;

        widget = null;
    }

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

    // Checks if the stage has a widget
    public boolean isEmpty(){
        if(widget == null){
            return true;
        }
        return false;
    }

    // generate the stage report
    public String stageReport(){
        double stageWork = (100-((timeBlocked + timeStarved) / maxTime)*100);
        return stageID + ":     Work[%]: "  + String.format("%,.2f", stageWork) + "%     Starve[t]: " + String.format("%,.2f", timeStarved) + "     Block[t]: " + String.format("%,.2f", timeBlocked) + "\n";
    }

    // Gets the stages next queue
    public InterStageQueue<Widget> getNextQueue(){
        return nextQueue;
    }

    // Gets the stages prev queue
    public InterStageQueue<Widget> getPrevQueue(){
        return prevQueue;
    }

    // Abstract Methods
    public abstract TimeEvent widgetIn(double currentTime);

    public abstract void widgetOut();

    public abstract TimeEvent processStage(double currentTime);
}
