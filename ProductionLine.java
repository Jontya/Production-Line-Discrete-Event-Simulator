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

    private Random r;

    private PriorityQueue<TimeEvent> timeStorage;

    public ProductionLine(int _M, int _N, int _QMax){
        M = _M;
        N = _N;
        QMax = _QMax;

        r = new Random(100);

        currentTime = 0;

        timeStorage = new PriorityQueue<>();
    }

    public void beginProduction(){

        InterStageQueue<Widget> Q01 = new InterStageQueue<Widget>(QMax, "Q01");

        InterStageQueue<Widget> Q12 = new InterStageQueue<Widget>(QMax, "Q12");

        InterStageQueue<Widget> Q23 = new InterStageQueue<Widget>(QMax, "Q23");

        InterStageQueue<Widget> Q34 = new InterStageQueue<Widget>(QMax, "Q34");

        InterStageQueue<Widget> Q45 = new InterStageQueue<Widget>(QMax, "Q45");

        InterStageQueue<Widget> Q56 = new InterStageQueue<Widget>(QMax, "Q56");

        InitStage S0a = new InitStage(Q01, M, N, "S0a", r, 2);

        InitStage S0b = new InitStage(Q01, M, N, "S0b", r, 2);

        MidStage S1 = new MidStage(Q01, Q12, "S1");

        MidStage S2 = new MidStage(Q12, Q23, "S2");

        MidStage S3a = new MidStage(Q23, Q34, "S3a", 2);

        MidStage S3b = new MidStage(Q23, Q34, "S3b", 2);

        MidStage S4 = new MidStage(Q34, Q45, "S4");

        MidStage S5a = new MidStage(Q45, Q56, "S5a", 2);

        MidStage S5b = new MidStage(Q45, Q56, "S5b", 2);

        FinalStage S6 = new FinalStage(Q56, "S6");

        while(currentTime <= maxTime){
            currentTime = 10000001;
        }

        System.out.println(S0a.getProcessingTime());
    }

    public String createReport(){
        String report = "";
        return report;
    }
}
