public abstract class Stage {

    protected int M;
    protected int N;
    protected int parallelStage;

    protected InterStageStorage<Widget> nextQueue;
    protected InterStageStorage<Widget> prevQueue;

    protected String ID;

    
}
