import java.util.Random;

public class InitStage extends Stage {

    public InitStage(InterStageQueue<Widget> _nextQueue, int _M, int _N, String _ID, Random _randomNum){
        nextQueue = _nextQueue;
        randomNum = _randomNum;

        setProductionParameters(_M, _N);
        parallelStage = 1;

        prevTime = 0;

        currentStatus = stageStatus.IDLE;

        ID = _ID;
    }

    public InitStage(InterStageQueue<Widget> _nextQueue, int _M, int _N, String _ID, Random _randomNum, int _parallelStage){
        this(_nextQueue, _M, _N, _ID, _randomNum);

        parallelStage = _parallelStage;
    }

    @Override
    public TimeEvent waiting(double currentTime){

        prevTime = currentTime;

        double processTime = getProcessingTime();

        widget = new Widget(currentTime, processTime + currentTime);

        currentStatus = stageStatus.OCCUPIED;

        return new TimeEvent(processTime + currentTime);
    }
    
    @Override
    public void blocked(double currentTime){
        if(currentTime != prevTime){
            timeBlocked += currentTime - prevTime;
            prevTime = currentTime;
        }

        if(nextQueue.push(widget)){
            currentStatus = stageStatus.IDLE;
        }

    }

    @Override
    public void starved(double currentTime){
        return;
    }

    @Override
    public void occupied(double currentTime){
        prevTime = currentTime;
        if(currentTime == widget.getEndTime()){
            if(!nextQueue.push(widget)){
                currentStatus = stageStatus.BLOCKED;
            }
            currentStatus = stageStatus.IDLE;
        }
        
        
    }
}
