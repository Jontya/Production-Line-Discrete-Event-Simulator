public class FinalStage extends Stage{

    public FinalStage(InterStageQueue<Widget> _prevQueue, String _ID){
        prevQueue = _prevQueue;

        parallelStage = 1;

        ID = _ID;
    }

    public FinalStage(InterStageQueue<Widget> _prevQueue, String _ID, int _parallelStage){
        this(_prevQueue, _ID);

        parallelStage = _parallelStage;
    }

    public TimeEvent waiting(double currentTime){
        return new TimeEvent(0);
    }
    
    public void blocked(double currentTime){

    }

    public void starved(double currentTime){
        
    }
    
}
