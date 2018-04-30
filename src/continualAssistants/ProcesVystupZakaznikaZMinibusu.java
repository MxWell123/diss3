package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;
import java.util.Random;

//meta! id="105"
public class ProcesVystupZakaznikaZMinibusu extends Process {

    private Random nasada = new Random();
    private Random rnd1;

    public ProcesVystupZakaznikaZMinibusu(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
        rnd1 = new Random(nasada.nextLong());
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

    //meta! sender="AgentMinibus", id="106", type="Start"
    public void processStart(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        sprava.setCode(Mc.koniecVystupu);
        sprava.setZakaznik(sprava.getMinibus().vyberZakaznikaZMinibusu());
        hold(2 + ((8 - 2) * rnd1.nextDouble()), sprava);
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v ProcesVystupZakaznikaZMinibusu.");
    }

    //meta! sender="AgentMinibus", id="177", type="Notice"
    public void processKoniecVystupu(MessageForm message) {
        assistantFinished(message);
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.koniecVystupu:
                processKoniecVystupu(message);
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
