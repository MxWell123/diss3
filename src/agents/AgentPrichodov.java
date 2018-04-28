package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="59"
public class AgentPrichodov extends Agent
{
	public AgentPrichodov(int id, Simulation mySim, Agent parent)
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
		new ManagerPrichodov(Id.managerPrichodov, mySim(), this);
		addOwnMessage(Mc.prichodZakaznikaTerm2);
		addOwnMessage(Mc.nastupZakaznikovTerm2);
		addOwnMessage(Mc.nastupZakaznikovTerm1);
		addOwnMessage(Mc.prichodZakaznikaTerm1);
	}
	//meta! tag="end"
}