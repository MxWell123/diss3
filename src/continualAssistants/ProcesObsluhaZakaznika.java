package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;
import OSPRNG.*;
import java.util.Random;

//meta! id="86"
public class ProcesObsluhaZakaznika extends Process {

    private static final int MINUTA = 60;

    TriangularRNG trianInput = new TriangularRNG(1.6 * MINUTA, 2.02 * MINUTA, 3.1 * MINUTA);
    TriangularRNG trianInput2 = new TriangularRNG(3.2 * MINUTA, 4.58 * MINUTA, 5.1 * MINUTA);
    TriangularRNG trianOutput1 = new TriangularRNG(1.0 * MINUTA, 1.25 * MINUTA, 2.1 * MINUTA);
    TriangularRNG trianOutput2 = new TriangularRNG(2.9 * MINUTA, 4.37 * MINUTA, 4.7 * MINUTA);
    Random nasada = new Random();
    Random rnd = new Random(nasada.nextLong());

    public ProcesObsluhaZakaznika(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

    //meta! sender="AgentObsluhy", id="87", type="Start"
    public void processStart(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        sprava.setCode(Mc.koniecObsluhy);
        int cisloZam = sprava.getZakaznik().getCisloZamestnanca();
        Zamestnanec zam = myAgent().getZamestnanec(cisloZam);
        double pom = 0.0;
        if (sprava.getZakaznik().isTyp() == false) {
            if (rnd.nextDouble() < 0.768028846) {
                pom = trianInput.sample();
            } else {
                pom = trianInput2.sample();
            }
            zam.setCasZaciatkuVykonu(mySim().currentTime());
            zam.setTrvanieVykonu(pom);
            hold(pom, sprava);
        } else {
            if (rnd.nextDouble() < 0.862579281) {
                pom = trianOutput1.sample();
            } else {
                pom = trianOutput2.sample();
            }
            zam.setCasZaciatkuVykonu(mySim().currentTime());
            zam.setTrvanieVykonu(pom);
            hold(pom, sprava);
        }
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v ProcesObsluhaZakaznika.");
    }

    //meta! sender="AgentObsluhy", id="169", type="Notice"
    public void processKoniecObsluhy(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        assistantFinished(sprava);
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.start:
                processStart(message);
                break;

            case Mc.koniecObsluhy:
                processKoniecObsluhy(message);
                break;

            default:
                processDefault(message);
                break;
        }
    }
    //meta! tag="end"

    @Override
    public AgentObsluhy myAgent() {
        return (AgentObsluhy) super.myAgent();
    }

}
