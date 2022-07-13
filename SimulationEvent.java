public class SimulationEvent {

    private double startTime;
    private double endTime;
    private String stageID;

    // Constructor
    public SimulationEvent(double _startTime, double _endTime, String _stageID){
        startTime = _startTime;
        endTime = _endTime;
        stageID = _stageID;
    }

    // Gets the start time
    public double getStartTime(){
        return startTime;
    }

    // Gets the end time
    public double getEndTime(){
        return endTime;
    }

    // Gets the stageID
    public String getStageID(){
        return stageID;
    }

}
