//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 24/05/22
***
*** ProductionLine:
*** This class creates each stage and inter-stage queue. This class manages the priority queue and
*** is in charge of managing all of the new time events. It creates the report when finished processing.
**/
//---------------------------------------------------------------------------------------------------

import java.util.*;

public class ProductionLine {

    private static int M;
    private static int N;
    private static int QMax;
    private static double maxTime = 10000000;

    private String report;

    private double currentTime;

    private PriorityQueue<TimeEvent> eventQueue;
    
    // Constructor
    public ProductionLine(int _M, int _N, int _QMax){
        M = _M;
        N = _N;
        QMax = _QMax;

        currentTime = 0;

        report = "";

        eventQueue = new PriorityQueue<>();
    }

    public void beginProduction(){
        // Initializes each inter-stage storage queue
        InterStageQueue<Widget> Q01 = new InterStageQueue<>(QMax, "Q01", maxTime);
        InterStageQueue<Widget> Q12 = new InterStageQueue<>(QMax, "Q12");
        InterStageQueue<Widget> Q23 = new InterStageQueue<>(QMax, "Q23");
        InterStageQueue<Widget> Q34 = new InterStageQueue<>(QMax, "Q34");
        InterStageQueue<Widget> Q45 = new InterStageQueue<>(QMax, "Q45");
        InterStageQueue<Widget> Q56 = new InterStageQueue<>(QMax, "Q56");

        // Initializes each stage
        StartStage S0a = new StartStage(Q01, M, N, maxTime, 2, "S0a");
        StartStage S0b = new StartStage(Q01, M, N, maxTime, 1, "S0b");

        InternalStage S1 = new InternalStage(Q01, Q12, 1, "S1");
        InternalStage S2 = new InternalStage(Q12, Q23, 1, "S2");
        InternalStage S3a = new InternalStage(Q23, Q34, 2, "S3a");
        InternalStage S3b = new InternalStage(Q23, Q34, 2 , "S3b");
        InternalStage S4 = new InternalStage(Q34, Q45, 1, "S4");
        InternalStage S5a = new InternalStage(Q45, Q56, 2, "S5a");
        InternalStage S5b = new InternalStage(Q45, Q56, 2, "S5b");

        EndStage S6 = new EndStage(Q56, 1, "S6");

        // Sets each stages "links"
        S0a.setNextStage(S1, null);
        S0b.setNextStage(S1, null);
        S1.setNextStage(S2, null);
        S2.setNextStage(S3a, S3b);
        S3a.setNextStage(S4, null);
        S3b.setNextStage(S4, null);
        S4.setNextStage(S5a, S5b);
        S5a.setNextStage(S6, null);
        S5b.setNextStage(S6, null);

        // Creates two new widgets and adds their time event to the PQ
        eventQueue.add(S0a.widgetIn(currentTime));
        eventQueue.add(S0b.widgetIn(currentTime));

        ArrayList<TimeEvent> newJobs;

        // Loops while the max time limit has not been reached
        while(currentTime < maxTime){
            // sets current time as the next event in the PQ
            currentTime = eventQueue.peek().getTime();

            // Gets all of the new events created after processing the previous stage
            newJobs = eventQueue.remove().getStageRef().processStage(currentTime);
            // Adds all new events into the PQ
            for(int i = 0; i < newJobs.size(); i++){
                if(newJobs.get(i).getTime() == -1){
                    eventQueue.add(new TimeEvent(eventQueue.peek().getTime() + 1, newJobs.get(i).getStageRef()));
                }
                else{
                    eventQueue.add(newJobs.get(i));
                }
            }
        }

        // List of finished widgets
        ArrayList<Widget> finishedWidgets = S6.getFinishedWidgets();

        // Creates the report
        report += "Production Stages:\n------------------------------------------------------------------------\n";
        report += S0a.stageReport();
        report += S0b.stageReport();
        report += S1.stageReport();
        report += S2.stageReport();
        report += S3a.stageReport();
        report += S3b.stageReport();
        report += S4.stageReport();
        report += S5a.stageReport();
        report += S5b.stageReport();
        report +=S6.stageReport();

        report += "\nStorage Queues:\n------------------------------------------------------------------------\n";
        report += Q01.queueReport();
        report += Q12.queueReport();
        report += Q23.queueReport();
        report += Q34.queueReport();
        report += Q45.queueReport();
        report += Q56.queueReport();

        report += "\nProduction Paths:\n------------------------------------------------------------------------\n";
        int pathA= 0;
        int pathB = 0;
        int pathC = 0;
        int pathD = 0;

        // Loops through each widget finding its path
        for(int i = 0; i < finishedWidgets.size(); i++){
            ArrayList<SimulationEvent> listOfSimEvents = finishedWidgets.get(i).getSimEvents();
            if(listOfSimEvents.get(3).getStageID() == "S3a"){
                if(listOfSimEvents.get(5).getStageID() == "S5a"){
                    pathA++;
                }
                else{
                    pathB++;
                }
            }
            else{
                if(listOfSimEvents.get(5).getStageID() == "S5a"){
                    pathC++;
                }
                else{
                    pathD++;
                }
            }
        }

        report += "S3a -> S5a: " + String.format("%,d", pathA) + "\n";
        report += "S3a -> S5b: " + String.format("%,d", pathB) + "\n";
        report += "S3b -> S5a: " + String.format("%,d", pathC) + "\n";
        report += "S3b -> S5b: " + String.format("%,d", pathD) + "\n";

        report += "\nProduction Widgets:\n------------------------------------------------------------------------\n";

        int countA = 0;
        int countB = 0;
        String currID;

        // Loops through all widgets to define what stage each widget was made in
        for(int i = 0; i < finishedWidgets.size(); i++){
            currID = finishedWidgets.get(i).getID();
            if(currID.charAt(currID.length()-1) == 'a'){
                countA++;
            }
            else{
                countB++;
            }
        }

        report += "S0a: " + String.format("%,d", countA) + "\n";
        report += "S0b: " + String.format("%,d", countB) + "\n";
        
    }

    public String getReport(){
        return report;
    }
}
