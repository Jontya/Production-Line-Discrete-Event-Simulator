//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 23/05/22
***
*** SimulationEvent:
*** Stored in a list inside of a widget class. Only used to calculate stats in the report
**/
//---------------------------------------------------------------------------------------------------

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
