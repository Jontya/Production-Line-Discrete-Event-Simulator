import java.util.Random;

public class InitStage extends Stage {

    public InitStage(InterStageStorage<Widget> _nextQueue, int _M, int _N, String _ID, Random _randomNum){
        nextQueue = _nextQueue;
        randomNum = _randomNum;

        setProductionParameters(_M, _N);
        parallelStage = 1;

        currentStatus = stageStatus.WAITING;

        ID = _ID;
    }

    public InitStage(InterStageStorage<Widget> _nextQueue, int _M, int _N, String _ID, Random _randomNum, int _parallelStage){
        this(_nextQueue, _M, _N, _ID, _randomNum);

        parallelStage = _parallelStage;
    }

    public TimeEvent waiting(double currentTime){
        widget = new Widget();
        double processTime = getProcessingTime();
        return new TimeEvent(processTime + currentTime);
    }
    
    public void blocked(double currentTime){

    }

    public void starved(double currentTime){

    }
}
