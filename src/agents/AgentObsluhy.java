package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="45"
public class AgentObsluhy extends Agent {

    private SimQueue<Zakaznik> frontZakaznikovPredObsluhou;
    private boolean isWorking;

    public AgentObsluhy(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);

        init();
    }

    public void pridajZakaznikDoRadu(Zakaznik zak) {
        frontZakaznikovPredObsluhou.add(zak);
    }

    public Zakaznik vyberZakaznikaZRadu() {
        return frontZakaznikovPredObsluhou.poll();
    }

    public int velkostRadu() {
        return frontZakaznikovPredObsluhou.size();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        isWorking = false;
        frontZakaznikovPredObsluhou = new SimQueue<>();
        // Setup component for the next replication
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerObsluhy(Id.managerObsluhy, mySim(), this);
		new ProcesObsluhaZakaznika(Id.procesObsluhaZakaznika, mySim(), this);
		addOwnMessage(Mc.vystupZakaznikaDoObsluhy);
		addOwnMessage(Mc.nastupZakaznikovZObsluhy);
		addOwnMessage(Mc.prichodZakaznikaNaVratenieAuta);
		addOwnMessage(Mc.koniecObsluhy);
	}
	//meta! tag="end"
}