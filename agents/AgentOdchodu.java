package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="60"
public class AgentOdchodu extends Agent
{
	public AgentOdchodu(int id, Simulation mySim, Agent parent)
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
		new ManagerOdchodu(Id.managerOdchodu, mySim(), this);
		addOwnMessage(Mc.vystupZakaznikaTerm3);
	}
	//meta! tag="end"
}
