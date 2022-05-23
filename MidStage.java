public class MidStage extends Stage {

    public MidStage(InterStageStorage<Widget> _prevQueue, InterStageStorage<Widget> _nextQueue, String _ID){
        prevQueue = _prevQueue;
        nextQueue = _nextQueue;

        parallelStage = 1;

        ID = _ID;
    }

    public MidStage(InterStageStorage<Widget> _prevQueue, InterStageStorage<Widget> _nextQueue, String _ID, int _parallelStage){
        this(_prevQueue, _nextQueue, _ID);

        parallelStage = _parallelStage;
    }
    
}
