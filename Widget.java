//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 23/05/22
***
*** Widget:
*** basic widget class that is used to store a list of simulation events (used in the report) and a
*** globally unique widget ID. Widgets are stored in the inter-stage storage queues and also used
*** in each stage.
**/
//---------------------------------------------------------------------------------------------------

import java.util.ArrayList;

public class Widget {

    private ArrayList<SimulationEvent> simulationEvents;
    private String widgetID;

    // Constructor
    public Widget(String _widgetID){
        simulationEvents = new ArrayList<>();
        widgetID = _widgetID;
    }

    // Gets widget ID
    public String getID(){
        return widgetID;
    }

    // Adds new event to the list
    public void addEvent(double _startTime, double _endTime, String _stageID){
        simulationEvents.add(new SimulationEvent(_startTime, _endTime, _stageID));
    }

    // Gets the last end time in the list
    public double getLastEndTime(){
        return simulationEvents.get(simulationEvents.size() - 1).getEndTime();
    }

    public ArrayList<SimulationEvent> getSimEvents(){
        return simulationEvents;
    }
    
}
