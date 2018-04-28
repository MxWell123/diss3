package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="45"
public class AgentObsluhy extends Agent
{
	public AgentObsluhy(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerObsluhy(Id.managerObsluhy, mySim(), this);
		new ProcesObsluhaZakaznika(Id.procesObsluhaZakaznika, mySim(), this);
		addOwnMessage(Mc.nastupZakaznikovZObsluhy);
		addOwnMessage(Mc.prichodZakaznikaNaVratenieAuta);
	}
	//meta! tag="end"
}