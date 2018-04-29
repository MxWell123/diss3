package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="38"
public class AgentSpolocnosti extends Agent
{
	public AgentSpolocnosti(int id, Simulation mySim, Agent parent)
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
		new ManagerSpolocnosti(Id.managerSpolocnosti, mySim(), this);
		addOwnMessage(Mc.nastupZakaznikovZObsluhy);
		addOwnMessage(Mc.vystupZakaznikaDoObsluhy);
		addOwnMessage(Mc.vystupZakaznikaTerm3);
		addOwnMessage(Mc.prichodZakaznikaTerm2);
		addOwnMessage(Mc.nastupZakaznikovTerm2);
		addOwnMessage(Mc.nastupZakaznikovTerm1);
		addOwnMessage(Mc.prichodZakaznikaTerm1);
		addOwnMessage(Mc.prichodZakaznikaNaVratenieAuta);
		addOwnMessage(Mc.initPrichodMinibusov);
	}
	//meta! tag="end"
}
