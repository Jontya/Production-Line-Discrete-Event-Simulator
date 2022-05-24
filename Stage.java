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

    protected InterStageQueue<Widget> nextQueue;
    protected InterStageQueue<Widget> prevQueue;

    protected stageStatus currentStatus;

    protected Widget widget;
    protected String ID;

    protected double prevTime;
    protected double timeBlocked;
    protected double timeStarved;

    protected enum stageStatus{
        IDLE,
        BLOCKED,
        STARVED,
        OCCUPIED
    }

    protected void setProductionParameters(int _M, int _N){
        M = _M;
        N = _N;
    }

    protected double getProcessingTime(){
        randomNum = new Random(100);
        double r = randomNum.nextDouble();
        System.out.println(r);

        randomNum = new Random(100);
        r = randomNum.nextDouble();
        System.out.println(r);


        return (parallelStage * M) + (parallelStage * N) * (r - 0.5);
    }

    public String getID(){
        return ID;
    }

    public TimeEvent procesStage(double currentTime){
        if(currentStatus == stageStatus.IDLE){
            return waiting(currentTime);
        }
        if(currentStatus == stageStatus.BLOCKED){
            blocked(currentTime);
        }
        if(currentStatus == stageStatus.STARVED){
            starved(currentTime);
        }
        if(currentStatus == stageStatus.OCCUPIED){
            occupied(currentTime);
        }
        return null;
    }

    public abstract TimeEvent waiting(double currentTime);

    public abstract void blocked(double currentTime);

    public abstract void starved(double currentTime);

    public abstract void occupied(double currentTime);

}
