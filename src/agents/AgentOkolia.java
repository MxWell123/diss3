package agents;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="2"
public class AgentOkolia extends Agent {

    private static final double HODINA = 3600D;
    private static final int MINUTA = 60;
    public final double KONIEC_PRICHODOV = 5.5 * HODINA;

    public AgentOkolia(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

    public double dajTrvanie(ExponentialRNG[] generatory, double[] vstupy) {
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

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerOkolia(Id.managerOkolia, mySim(), this);
        new PlanovacPrichodZakaznikovTerm2(Id.planovacPrichodZakaznikovTerm2, mySim(), this);
        new PlanovacPrichodZakaznikovOdchod(Id.planovacPrichodZakaznikovOdchod, mySim(), this);
        new PlanovacPrichodZakaznikovTerm1(Id.planovacPrichodZakaznikovTerm1, mySim(), this);
        addOwnMessage(Mc.koniec);
        addOwnMessage(Mc.initPrichodyZakaznikov);
        addOwnMessage(Mc.novyZakaznik);
    }
    //meta! tag="end"
}
