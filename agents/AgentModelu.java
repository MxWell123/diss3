package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="1"
public class AgentModelu extends Agent
{
	public AgentModelu(int id, Simulation mySim, Agent parent)
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
		new ManagerModelu(Id.managerModelu, mySim(), this);
		new PlanovacMinibusov(Id.planovacMinibusov, mySim(), this);
		addOwnMessage(Mc.prichodZakaznikaTerm2);
		addOwnMessage(Mc.prichodZakaznikaTerm1);
		addOwnMessage(Mc.koniec);
		addOwnMessage(Mc.prichodZakaznikaNaVratenieAuta);
		addOwnMessage(Mc.initPrichodMinibusov);
		addOwnMessage(Mc.novyMinibus);
	}
	//meta! tag="end"
}
