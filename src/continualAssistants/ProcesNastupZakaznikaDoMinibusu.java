package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;
import java.util.Random;

//meta! id="88"
public class ProcesNastupZakaznikaDoMinibusu extends Process {

    private Random nasada = new Random();
    private Random rnd1;

    public ProcesNastupZakaznikaDoMinibusu(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
        rnd1 = new Random(nasada.nextLong());
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

    //meta! sender="AgentMinibus", id="89", type="Start"
    public void processStart(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        sprava.getMinibus().pridajZakaznikaDoMinibusu(sprava.getZakaznik());
        sprava.setCode(Mc.koniecNastupu);
        hold(10 + ((14 - 10) * rnd1.nextDouble()), sprava);
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v ProcesNastupuZakaznikaDoMinibusu.");
    }

    //meta! sender="AgentMinibus", id="175", type="Notice"
    public void processKoniecNastupu(MessageForm message) {
        assistantFinished(message);
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.start:
                processStart(message);
                break;

            case Mc.koniecNastupu:
                processKoniecNastupu(message);
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
