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
