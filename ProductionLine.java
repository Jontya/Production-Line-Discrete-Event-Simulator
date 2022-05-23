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

    private int M;
    private int N;
    private int QMax;

    // Array List to store every inter-stage storage queue
    private ArrayList<InterStageStorage<Widget>> interStageQueues;
    // Array List to store every stage
    private ArrayList<Stage> stages;

    public ProductionLine(int _M, int _N, int _QMax){
        M = _M;
        N = _N;
        QMax = _QMax;
    }

    public void beginProduction(){
        interStageQueues = new ArrayList<>();
        stages = new ArrayList<>();

        initProductionLine();
    }

    private void initProductionLine(){
        InterStageStorage<Widget> Q01 = new InterStageStorage<Widget>(QMax, "Q01");
        interStageQueues.add(Q01);

        InterStageStorage<Widget> Q12 = new InterStageStorage<Widget>(QMax, "Q12");
        interStageQueues.add(Q12);

        InterStageStorage<Widget> Q23 = new InterStageStorage<Widget>(QMax, "Q23");
        interStageQueues.add(Q23);

        InterStageStorage<Widget> Q34 = new InterStageStorage<Widget>(QMax, "Q34");
        interStageQueues.add(Q34);

        InterStageStorage<Widget> Q45 = new InterStageStorage<Widget>(QMax, "Q45");
        interStageQueues.add(Q45);

        InterStageStorage<Widget> Q56 = new InterStageStorage<Widget>(QMax, "Q56");
        interStageQueues.add(Q56);

        InitStage S0a = new InitStage();
        stages.add(S0a);

        InitStage S0b = new InitStage();
        stages.add(S0b);

        MidStage S1 = new MidStage();
        stages.add(S1);

        MidStage S2 = new MidStage();
        stages.add(S2);

        MidStage S3a = new MidStage();
        stages.add(S3a);

        MidStage S3b = new MidStage();
        stages.add(S3b);

        MidStage S4 = new MidStage();
        stages.add(S4);

        MidStage S5a = new MidStage();
        stages.add(S5a);

        MidStage S5b = new MidStage();
        stages.add(S5b);

        FinalStage S6 = new FinalStage();
        stages.add(S6);
    }

    public String createReport(){
        String report = "";
        return report;
    }
}
