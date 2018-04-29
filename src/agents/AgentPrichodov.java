package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="59"
public class AgentPrichodov extends Agent {

    private SimQueue<Zakaznik> frontZakaznikovTerm1;
    private SimQueue<Zakaznik> frontZakaznikovTerm2;

    public AgentPrichodov(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        frontZakaznikovTerm1 = new SimQueue<>();
        frontZakaznikovTerm2 = new SimQueue<>();
        init();
    }

    public void pridajZakaznikDoRaduTerm1(Zakaznik zak) {
        frontZakaznikovTerm1.add(zak);
    }

    public Zakaznik vyberZakaznikaZRaduTerm1() {
        if (frontZakaznikovTerm1.isEmpty()) {
            return null;
        }
        return frontZakaznikovTerm1.poll();
    }

    public void pridajZakaznikDoRaduTerm2(Zakaznik zak) {
        frontZakaznikovTerm2.add(zak);
    }

    public Zakaznik vyberZakaznikaZRaduTerm2() {
        if (frontZakaznikovTerm2.isEmpty()) {
            return null;
        }
        return frontZakaznikovTerm2.poll();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerPrichodov(Id.managerPrichodov, mySim(), this);
		addOwnMessage(Mc.prichodZakaznikaTerm2);
		addOwnMessage(Mc.nastupZakaznikovTerm2);
		addOwnMessage(Mc.nastupZakaznikovTerm1);
		addOwnMessage(Mc.prichodZakaznikaTerm1);
	}
	//meta! tag="end"
}