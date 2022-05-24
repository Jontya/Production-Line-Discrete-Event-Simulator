//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 23/05/22
***
*** Stage:
*** 
**/
//---------------------------------------------------------------------------------------------------

import java.util.Random;

public abstract class Stage {

    private int M;
    private int N;

    protected Random randomNum;

    protected int parallelStage;

    protected InterStageStorage<Widget> nextQueue;
    protected InterStageStorage<Widget> prevQueue;

    protected stageStatus currentStatus;

    protected Widget widget;

    protected String ID;

    protected enum stageStatus{
        WAITING,
        BLOCKED,
        STARVED
    }

    protected void setProductionParameters(int _M, int _N){
        M = _M;
        N = _N;
    }

    protected double getProcessingTime(){
        return (parallelStage * M) + (parallelStage * N) * ((randomNum.nextDouble() / 100) - 0.5);
    }

    public String getID(){
        return ID;
    }

    public TimeEvent procesStage(double currentTime){
        if(currentStatus == stageStatus.WAITING){
            return waiting(currentTime);
        }
        if(currentStatus == stageStatus.BLOCKED){
            blocked(currentTime);
        }
        if(currentStatus == stageStatus.STARVED){
            starved(currentTime);
        }
        return null;
    }

    public abstract TimeEvent waiting(double currentTime);

    public abstract void blocked(double currentTime);

    public abstract void starved(double currentTime);

}
