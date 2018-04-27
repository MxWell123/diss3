package continualAssistants;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import simulation.*;
import agents.*;

//meta! id="74"
public class PlanovacPrichodZakaznikovTerm1 extends Scheduler {

    private static final double HODINA = 3600D;
    private static final int MINUTA = 60;
    private ExponentialRNG[] generatory = new ExponentialRNG[18];
    private static final double[] vstupy = {4 / HODINA, 8 / HODINA, 12 / HODINA, 15 / HODINA, 18 / HODINA, 14 / HODINA,
        13 / HODINA, 10 / HODINA, 4 / HODINA, 6 / HODINA, 10 / HODINA, 14 / HODINA, 16 / HODINA,
        15 / HODINA, 7 / HODINA, 3 / HODINA, 4 / HODINA, 2 / HODINA};

    public PlanovacPrichodZakaznikovTerm1(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

    //meta! sender="AgentOkolia", id="75", type="Start"
    public void processStart(MessageForm message) {
        message.setCode(Mc.novyZakaznik);
        this.hold(dajTrvanie(), message);
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
            MessageForm kopia = message.createCopy();
            this.hold(dajTrvanie(), kopia);
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
            case Mc.koniec:
                processKoniec(message);
                break;

            case Mc.start:
                processStart(message);
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

    private double dajTrvanie() {
        final double aktualnyCas = mySim().currentTime();
        int aktualnyInterval = dajCisloIntervalu(aktualnyCas);
        double aktualneTrvanie = generatory[aktualnyInterval].sample();
        int nasledujuciInterval = dajCisloIntervalu(aktualnyCas + aktualneTrvanie);
        if (nasledujuciInterval > aktualnyInterval
                && vstupy[aktualnyInterval] < vstupy[nasledujuciInterval]) {

            double zaciatokNasledujucehoIntervalu = nasledujuciInterval * 15 * MINUTA + HODINA;
            double trvanieVNasledujucom = (aktualnyCas + aktualneTrvanie) % (zaciatokNasledujucehoIntervalu);
            double tvanieVAktualnom = aktualneTrvanie - trvanieVNasledujucom;
            double nasledujuceTrvanie = generatory[nasledujuciInterval].sample();
            return tvanieVAktualnom + ((trvanieVNasledujucom + nasledujuceTrvanie) / 2);
        } else {
            return aktualneTrvanie;
        }
    }

    private int dajCisloIntervalu(double aktualnyCas) {
        int aktualnaHodina = (int) (aktualnyCas / HODINA);
        if (aktualnaHodina < 1) {
            return 0;
        } else {
            double zvysokSekund = aktualnyCas % HODINA;
            int aktualnaMinuta = (int) (zvysokSekund / (15 * MINUTA));
            return ((aktualnaHodina - 1) * 4) + aktualnaMinuta;
        }
    }

}
