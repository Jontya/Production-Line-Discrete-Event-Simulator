//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 16/05/22
***
*** TimeEvent:
*** This class is used to comapre widget times stored in the priority queue so it
*** can be re added into the queue at its required position.
**/
//---------------------------------------------------------------------------------------------------

public class TimeEvent implements Comparable<TimeEvent>{

    private double time;

    public TimeEvent(double _time){
        time = _time;
    }

    public double getTime(){
        return time;
    }    
    @Override
    public int compareTo(TimeEvent timeEvent){
        if(timeEvent.getTime() < time){
            return 1;
        }
        return -1;
    }
}
