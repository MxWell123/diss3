package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;

//meta! id="103"
public class ProcesPrechodMedziTerminalmi extends Process {

    private static final double PRECHOD_TERM1_TERM2 = (0.5 / 35.00) * 3600;
    private static final double PRECHOD_TERM2_ARCAR = (3.4 / 35.00) * 3600;
    private static final double PRECHOD_ARCAR_TERM3 = (2.9 / 35.00) * 3600;
    private static final double PRECHOD_TERM3_TERM1 = (0.9 / 35.00) * 3600;
    private static final double PRECHOD_ARCAR_TERM1 = (2.5 / 35.00) * 3600;

    public ProcesPrechodMedziTerminalmi(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

    //meta! sender="AgentMinibus", id="104", type="Start"
    public void processStart(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        Minibus minibus = sprava.getMinibus();
        minibus.setTypPrace("presun");
        if (minibus.getPolohaMinibusu() == 0) { // term 1
            minibus.setPolohaMinibusu(1);
            sprava.setCode(Mc.koniecPrechodu);
            minibus.setCasZaciatkuVykonu(mySim().currentTime(), PRECHOD_TERM1_TERM2);
            myAgent().pripocitajKilometre(0.5);
            hold(PRECHOD_TERM1_TERM2, message);
        } else if (minibus.getPolohaMinibusu() == 1) { // term2
            minibus.setPolohaMinibusu(2);
            sprava.setCode(Mc.koniecPrechodu);
            minibus.setCasZaciatkuVykonu(mySim().currentTime(), PRECHOD_TERM2_ARCAR);
            myAgent().pripocitajKilometre(3.4);
            hold(PRECHOD_TERM2_ARCAR, message);
        } else if (minibus.getPolohaMinibusu() == 2) { // arcar
            if (!minibus.jeMinibusPrazdny()) { // ide do term3
                minibus.setPolohaMinibusu(3);
                sprava.setCode(Mc.koniecPrechodu);
                minibus.setCasZaciatkuVykonu(mySim().currentTime(), PRECHOD_ARCAR_TERM3);
                myAgent().pripocitajKilometre(2.9);
                hold(PRECHOD_ARCAR_TERM3, message);
            } else {
                minibus.setPolohaMinibusu(0); // ide rovno term1
                sprava.setCode(Mc.koniecPrechodu);
                minibus.setCasZaciatkuVykonu(mySim().currentTime(), PRECHOD_ARCAR_TERM1);
                myAgent().pripocitajKilometre(2.5);
                hold(PRECHOD_ARCAR_TERM1, message);
            }
        } else if (minibus.getPolohaMinibusu() == 3) { // term3 
            minibus.setPolohaMinibusu(0);
            sprava.setCode(Mc.koniecPrechodu);
            minibus.setCasZaciatkuVykonu(mySim().currentTime(), PRECHOD_TERM3_TERM1);
            myAgent().pripocitajKilometre(0.9);
            hold(PRECHOD_TERM3_TERM1, message);
        }
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v ProcesPrechodMedziTerminalmi.");
    }

    //meta! sender="AgentMinibus", id="178", type="Notice"
    public void processKoniecPrechodu(MessageForm message) {
        assistantFinished(message);
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.koniecPrechodu:
                processKoniecPrechodu(message);
                break;

            case Mc.start:
                processStart(message);
                break;

            default:
                processDefault(message);
                break;
        }
    }
    //meta! tag="end"

    @Override
    public AgentMinibus myAgent() {
        return (AgentMinibus) super.myAgent();
    }

}
