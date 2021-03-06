package continualAssistants;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import simulation.*;
import agents.*;

//meta! id="74"
public class PlanovacPrichodZakaznikovTerm1 extends Scheduler {

    private static final double HODINA = 3600D;
    private final ExponentialRNG[] generatory = new ExponentialRNG[18];
    private static final double[] vstupy = {HODINA / 4, HODINA / 8, HODINA / 12, HODINA / 15, HODINA / 18, HODINA / 14,
        HODINA / 13, HODINA / 10, HODINA / 4, HODINA / 6, HODINA / 10, HODINA / 14, HODINA / 16,
        HODINA / 15, HODINA / 7, HODINA / 3, HODINA / 4, HODINA / 2};

    public PlanovacPrichodZakaznikovTerm1(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);

        for (int i = 0; i < 18; i++) {
            generatory[i] = new ExponentialRNG(vstupy[i]);
        }
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

    //meta! sender="AgentOkolia", id="75", type="Start"
    public void processStart(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        sprava.setCode(Mc.novyZakaznik);
        sprava.setZakaznik(new Zakaznik(false));
        this.hold(myAgent().dajTrvanie(generatory, vstupy), sprava);
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v PlanovacPrichodZakaznikovTerm1.");
    }

    //meta! sender="AgentOkolia", id="130", type="Notice"
    public void processNovyZakaznik(MessageForm message) {
        if (mySim().currentTime() >= myAgent().KONIEC_PRICHODOV) {
            message.setCode(Mc.koniec);
            this.assistantFinished(message);
        } else {
            MyMessage kopia = (MyMessage) message.createCopy();
            kopia.setZakaznik(new Zakaznik(false));
            this.hold(myAgent().dajTrvanie(generatory, vstupy), kopia);
            this.assistantFinished(message);
        }
    }

    //meta! sender="AgentOkolia", id="140", type="Notice"
    public void processKoniec(MessageForm message) {
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.novyZakaznik:
                processNovyZakaznik(message);
                break;

            case Mc.start:
                processStart(message);
                break;

            case Mc.koniec:
                processKoniec(message);
                break;

            default:
                processDefault(message);
                break;
        }
    }
    //meta! tag="end"

    @Override
    public AgentOkolia myAgent() {
        return (AgentOkolia) super.myAgent();
    }

}
