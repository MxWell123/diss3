package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="45"
public class AgentObsluhy extends Agent {

    private SimQueue<Zakaznik> frontZakaznikovPredObsluhou;
    private int pocetVytazenychPracovnikov;
    private int pocetPracovnikov;
    private Stat priemernyCasCakaniaVRade;
    private Zamestnanec[] zamestnanci;

    public AgentObsluhy(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    public void setPocetPracovnikov(int pocetPracovnikov) {
        this.pocetPracovnikov = pocetPracovnikov;
    }

    public void pridajZakaznikDoRadu(Zakaznik zak) {
        frontZakaznikovPredObsluhou.add(zak);
        // System.out.println("DLzka Radu =" + frontZakaznikovPredObsluhou.size());
    }

    public Zakaznik vyberZakaznikaZRadu() {
        return frontZakaznikovPredObsluhou.poll();
    }

    public int velkostRadu() {
        return frontZakaznikovPredObsluhou.size();
    }

    public Zamestnanec getZamestnanec(int cisloZamestnanca) {
        return zamestnanci[cisloZamestnanca];
    }

    public Zamestnanec[] getZamestnanci() {
        return zamestnanci;
    }

    @Override
    public void prepareReplication() {
        pocetVytazenychPracovnikov = 0;
        zamestnanci = new Zamestnanec[pocetPracovnikov];
        for (int i = 0; i < pocetPracovnikov; i++) {
            zamestnanci[i] = new Zamestnanec(i);
        }
        frontZakaznikovPredObsluhou = new SimQueue<>();
        priemernyCasCakaniaVRade = new Stat();
        super.prepareReplication();

        // Setup component for the next replication
    }

    public void pripocitajPriemernyCasVRade(double cas) {
        priemernyCasCakaniaVRade.addSample(cas);
    }

    public Stat getPriemernyCasCakaniaVRade() {
        return priemernyCasCakaniaVRade;
    }

    public int pripocitajVytazenychPrac() { // vrati cislo vytazeneho pracovnika
        Zamestnanec zam = zamestnanci[pocetVytazenychPracovnikov];
        zam.setVytazeny(true);
        zam.setVykonPrace("Obsluhuje Zakaznika");
        pocetVytazenychPracovnikov++;
        return pocetVytazenychPracovnikov - 1;
    }

    public void odpocitajVytazenychPrac() {
        Zamestnanec zam = zamestnanci[pocetVytazenychPracovnikov - 1];
        zam.setVytazeny(false);
        zam.setVykonPrace("čaká");
        pocetVytazenychPracovnikov--;
    }

    public int getPocetVytazenychPracovnikov() {
        return pocetVytazenychPracovnikov;
    }

    public boolean jeVolnyPracovnik() {
        if (pocetVytazenychPracovnikov < pocetPracovnikov) {
            return true;
        } else {
            return false;
        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerObsluhy(Id.managerObsluhy, mySim(), this);
        new ProcesObsluhaZakaznika(Id.procesObsluhaZakaznika, mySim(), this);
        addOwnMessage(Mc.vystupZakaznikaDoObsluhy);
        addOwnMessage(Mc.prichodZakaznikaNaVratenieAuta);
        addOwnMessage(Mc.koniecObsluhy);
    }
    //meta! tag="end"

}
