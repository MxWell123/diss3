package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="2"
public class AgentOkolia extends Agent
{
	public AgentOkolia(int id, Simulation mySim, Agent parent)
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
		new ManagerOkolia(Id.managerOkolia, mySim(), this);
		new PlanovacPrichodZakaznikovOdchod(Id.planovacPrichodZakaznikovOdchod, mySim(), this);
		new PlanovacPrichodZakaznikovTerm2(Id.planovacPrichodZakaznikovTerm2, mySim(), this);
		new PlanovacPrichodZakaznikovTerm1(Id.planovacPrichodZakaznikovTerm1, mySim(), this);
		addOwnMessage(Mc.koniec);
		addOwnMessage(Mc.initPrichodyZakaznikov);
		addOwnMessage(Mc.novyZakaznik);
	}
	//meta! tag="end"
}
