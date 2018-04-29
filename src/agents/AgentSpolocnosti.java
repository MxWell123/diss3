package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="38"
public class AgentSpolocnosti extends Agent {

    private SimQueue<Zakaznik> frontZakaznikovNaMinibus;

    public AgentSpolocnosti(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        frontZakaznikovNaMinibus = new SimQueue<>();
        init();
    }

    public void pridajZakaznikDoRaduNaMinibus(Zakaznik zak) {
        frontZakaznikovNaMinibus.add(zak);
    }

    public Zakaznik vyberZakaznikaZRaduNaMinibus() {
        return frontZakaznikovNaMinibus.poll();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerSpolocnosti(Id.managerSpolocnosti, mySim(), this);
		addOwnMessage(Mc.vystupZakaznikaDoObsluhy);
		addOwnMessage(Mc.vystupZakaznikaTerm3);
		addOwnMessage(Mc.nastupZakaznikovZObsluhy);
		addOwnMessage(Mc.prichodZakaznikaTerm2);
		addOwnMessage(Mc.nastupZakaznikovTerm2);
		addOwnMessage(Mc.prichodZakaznikaTerm1);
		addOwnMessage(Mc.nastupZakaznikovTerm1);
		addOwnMessage(Mc.initPrichodMinibusov);
		addOwnMessage(Mc.prichodZakaznikaNaVratenieAuta);
	}
	//meta! tag="end"
}