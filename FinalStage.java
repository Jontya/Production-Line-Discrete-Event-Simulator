public class FinalStage extends Stage{

    public FinalStage(InterStageStorage<Widget> _prevQueue, String _ID){
        prevQueue = _prevQueue;

        ID = _ID;
    }

    public FinalStage(InterStageStorage<Widget> _prevQueue, String _ID, int _parallelStage){
        this(_prevQueue, _ID);

        parallelStage = _parallelStage;
    }
    
}
