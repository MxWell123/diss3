package agents;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import OSPStat.Stat;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="2"
public class AgentOkolia extends Agent {

    private static final double HODINA = 3600D;
    private static final int MINUTA = 60;
    public final double KONIEC_PRICHODOV = 5.5 * HODINA;
    public int pocetObsluzenychZakPoAuto;
    public int pocetObsluzenychZakVraciaAuto;
    private Stat casCakaniaPoAutoStat;
    private Stat casCakaniaVratAutoStat;

    public AgentOkolia(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        pocetObsluzenychZakPoAuto = 0;
        pocetObsluzenychZakVraciaAuto = 0;
        casCakaniaPoAutoStat = new Stat();
        casCakaniaVratAutoStat = new Stat();
        super.prepareReplication();
        // Setup component for the next replication
    }

    public Stat getCasCakaniaPoAutoStat() {
        return casCakaniaPoAutoStat;
    }

    public Stat getCasCakaniaVratAutoStat() {
        return casCakaniaVratAutoStat;
    }

    public int getPocetObsluzenychZakPoAuto() {
        return pocetObsluzenychZakPoAuto;
    }

    public void setPocetObsluzenychZakPoAuto(int pocetObsluzenychZakPoAuto) {
        this.pocetObsluzenychZakPoAuto = pocetObsluzenychZakPoAuto;
    }

    public int getPocetObsluzenychZakVraciaAuto() {
        return pocetObsluzenychZakVraciaAuto;
    }

    public void setPocetObsluzenychZakVraciaAuto(int pocetObsluzenychZakVraciaAuto) {
        this.pocetObsluzenychZakVraciaAuto = pocetObsluzenychZakVraciaAuto;
    }

    public void pripocitajCasCakaniaPoAuto(double cas) {
        casCakaniaPoAutoStat.addSample(cas);
    }

    public void pripocitajCasCakaniaVratAuto(double cas) {
        casCakaniaVratAutoStat.addSample(cas);
    }

    public double dajTrvanie(ExponentialRNG[] generatory, double[] vstupy) {
        final double aktualnyCas = mySim().currentTime();
        int aktualnyInterval = dajCisloIntervalu(aktualnyCas);
        double aktualneTrvanie = generatory[aktualnyInterval].sample();
        if (aktualneTrvanie > 15 * MINUTA) {
            aktualneTrvanie = 15 * MINUTA;
        }
        int nasledujuciInterval = dajCisloIntervalu(aktualnyCas + aktualneTrvanie);
        if (nasledujuciInterval > aktualnyInterval
                && vstupy[aktualnyInterval] < vstupy[nasledujuciInterval]) {

            double zaciatokNasledujucehoIntervalu = nasledujuciInterval * 15 * MINUTA + HODINA;
            double trvanieVNasledujucom = (aktualnyCas + aktualneTrvanie) % (zaciatokNasledujucehoIntervalu);
            double trvanieVAktualnom = aktualneTrvanie - trvanieVNasledujucom;
            double nasledujuceTrvanie = generatory[nasledujuciInterval].sample();
            return trvanieVAktualnom + ((trvanieVNasledujucom + nasledujuceTrvanie) / 2);
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
            int pomocna = ((aktualnaHodina - 1) * 4) + aktualnaMinuta;
            return pomocna;
        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerOkolia(Id.managerOkolia, mySim(), this);
        new PlanovacPrichodZakaznikovOdchod(Id.planovacPrichodZakaznikovOdchod, mySim(), this);
        new PlanovacPrichodZakaznikovTerm2(Id.planovacPrichodZakaznikovTerm2, mySim(), this);
        new PlanovacPrichodZakaznikovTerm1(Id.planovacPrichodZakaznikovTerm1, mySim(), this);
        addOwnMessage(Mc.vystupZakaznikaTerm3);
        addOwnMessage(Mc.odchodZakaznikov);
        addOwnMessage(Mc.koniec);
        addOwnMessage(Mc.initPrichodyZakaznikov);
        addOwnMessage(Mc.novyZakaznik);
    }
    //meta! tag="end"

    public void pripocitajZakPoAuto() {
        pocetObsluzenychZakPoAuto++;
    }

    public void pripocitajZakVratAuto() {
        pocetObsluzenychZakVraciaAuto++;
    }
}
