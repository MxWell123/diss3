package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="48"
public class AgentMinibus extends Agent
{
	public AgentMinibus(int id, Simulation mySim, Agent parent)
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
		new ManagerMinibus(Id.managerMinibus, mySim(), this);
		new ProcesPrechodMedziTerminalmi(Id.procesPrechodMedziTerminalmi, mySim(), this);
		new ProcesVystupZakaznikaZMinibusu(Id.procesVystupZakaznikaZMinibusu, mySim(), this);
		new ProcesNastupZakaznikaDoMinibusu(Id.procesNastupZakaznikaDoMinibusu, mySim(), this);
		addOwnMessage(Mc.nastupZakaznikovZObsluhy);
		addOwnMessage(Mc.nastupZakaznikovTerm2);
		addOwnMessage(Mc.nastupZakaznikovTerm1);
		addOwnMessage(Mc.initPrichodMinibusov);
	}
	//meta! tag="end"
}