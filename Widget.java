public class Widget {
    
    private String widgetID;

    public Widget(){
        widgetID = IDGenerator.getInstance().getID();
    }

    public String getWidgetID(){
        return widgetID;
    }
}
