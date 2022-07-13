public class TimeEvent implements Comparable<TimeEvent>{

    private double time;
    private Stage stageRef;


    // Constructor
    public TimeEvent(double _time, Stage _stageRef){
        time = _time;
        stageRef = _stageRef;
    }

    // Sets the time
    public void setTime(double _time){
        time = _time;
    }

    // Gets the time
    public double getTime(){
        return time;
    }

    // Gets the stage reference
    public Stage getStageRef(){
        return stageRef;
    }

    // Sets the stage reference
    public void setStageRef(Stage _stageRef){
        stageRef = _stageRef;
    }

    // Comparable method (used to sort the priority queue by time)
    @Override
    public int compareTo(TimeEvent timeEvent){
        if(time > timeEvent.getTime()){
            return 1;
        }
        return -1;
    }
}
