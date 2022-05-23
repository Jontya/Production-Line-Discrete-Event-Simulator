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

    protected String ID;

    protected void setProductionParamaters(int _M, int _N){
        M = _M;
        N = _N;
    }

    protected double getProcessingTime(){
        return (parallelStage * M) + (parallelStage * N) * (randomNum.nextDouble() - 0.5);
    }

    public String getID(){
        return ID;
    }

    
}
