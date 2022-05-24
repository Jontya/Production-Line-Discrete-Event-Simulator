import java.util.*;

public class Widget {
    
    private String widgetID;
    private double startTime;
    private double endTime;

    public Widget(double _startTime, double _endTime){
        widgetID = IDGenerator.getInstance().getID();

        startTime = _startTime;
        endTime = _endTime;
    }

    public String getWidgetID(){
        return widgetID;
    }

    public double getStartTime(){
        return startTime;
    }

    public double getEndTime(){
        return endTime;
    }
}
