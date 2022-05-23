//---------------------------------------------------------------------------------------------------
/** SENG2200 A3
*** Jonty Atkinson (C3391110)
*** 16/05/22
***
*** ProductionLine:
**/
//---------------------------------------------------------------------------------------------------

import java.util.*;

public class ProductionLine {

    private static int maxTime = 10000000;
    private int currentTime = 0;

    private int M;
    private int N;
    private int QMax;

    private PriorityQueue<TimeEvent> timeStorage;

    public ProductionLine(int _M, int _N, int _QMax){
        M = _M;
        N = _N;
        QMax = _QMax;

        currentTime = 0;

        timeStorage = new PriorityQueue<>();
    }

    public void beginProduction(){

        InterStageStorage<Widget> Q01 = new InterStageStorage<Widget>(QMax, "Q01");

        InterStageStorage<Widget> Q12 = new InterStageStorage<Widget>(QMax, "Q12");

        InterStageStorage<Widget> Q23 = new InterStageStorage<Widget>(QMax, "Q23");

        InterStageStorage<Widget> Q34 = new InterStageStorage<Widget>(QMax, "Q34");

        InterStageStorage<Widget> Q45 = new InterStageStorage<Widget>(QMax, "Q45");

        InterStageStorage<Widget> Q56 = new InterStageStorage<Widget>(QMax, "Q56");

        InitStage S0a = new InitStage();

        InitStage S0b = new InitStage();

        MidStage S1 = new MidStage();

        MidStage S2 = new MidStage();

        MidStage S3a = new MidStage();

        MidStage S3b = new MidStage();

        MidStage S4 = new MidStage();

        MidStage S5a = new MidStage();

        MidStage S5b = new MidStage();

        FinalStage S6 = new FinalStage();

        while(currentTime <= maxTime){

        }
    }

    public String createReport(){
        String report = "";
        return report;
    }
}
