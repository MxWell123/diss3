package continualAssistants;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import simulation.*;
import agents.*;

//meta! id="78"
public class PlanovacPrichodZakaznikovOdchod extends Scheduler {

    private static final double HODINA = 3600D;
    private final ExponentialRNG[] generatory = new ExponentialRNG[18];
    private static final double[] vstupy = {HODINA / 12, HODINA / 9, HODINA / 18, HODINA / 28, HODINA / 23, HODINA / 21,
        HODINA / 16, HODINA / 11, HODINA / 17, HODINA / 22, HODINA / 36, HODINA / 24, HODINA / 32,
        HODINA / 16, HODINA / 13, HODINA / 13, HODINA / 5, HODINA / 4};

    public PlanovacPrichodZakaznikovOdchod(int id, Simulation mySim, CommonAgent myAgent) {
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

    //meta! sender="AgentOkolia", id="79", type="Start"
    public void processStart(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        sprava.setCode(Mc.novyZakaznik);
        sprava.setZakaznik(new Zakaznik(true));
        this.hold(myAgent().dajTrvanie(generatory, vstupy), sprava);
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v PlanovacPrichodZakaznikovOdchod.");
    }

    //meta! sender="AgentOkolia", id="134", type="Notice"
    public void processNovyZakaznik(MessageForm message) {
        if (mySim().currentTime() >= myAgent().KONIEC_PRICHODOV) {
            message.setCode(Mc.koniec);
            this.assistantFinished(message);
        } else {
            MyMessage kopia = (MyMessage) message.createCopy();
            kopia.setZakaznik(new Zakaznik(true));
            this.hold(myAgent().dajTrvanie(generatory, vstupy), kopia);
            this.assistantFinished(message);
        }
    }

    //meta! sender="AgentOkolia", id="141", type="Notice"
    public void processKoniec(MessageForm message) {
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.start:
                processStart(message);
                break;

            case Mc.koniec:
                processKoniec(message);
                break;

            case Mc.novyZakaznik:
                processNovyZakaznik(message);
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
