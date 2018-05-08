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
    private Stat casCakaniaStat;

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

    public Stat getCasCakaniaStat() {
        return casCakaniaStat;
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        pocetVytazenychPracovnikov = 0;
        frontZakaznikovPredObsluhou = new SimQueue<>();
        casCakaniaStat = new Stat();
        // Setup component for the next replication
    }

    public void pripocitajVytazenychPrac() {
        pocetVytazenychPracovnikov++;
    }

    public void odpocitajVytazenychPrac() {
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
	private void init()
	{
		new ManagerObsluhy(Id.managerObsluhy, mySim(), this);
		new ProcesObsluhaZakaznika(Id.procesObsluhaZakaznika, mySim(), this);
		addOwnMessage(Mc.vystupZakaznikaDoObsluhy);
		addOwnMessage(Mc.prichodZakaznikaNaVratenieAuta);
		addOwnMessage(Mc.koniecObsluhy);
	}
	//meta! tag="end"

}