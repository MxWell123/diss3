package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="59"
public class ManagerPrichodov extends Manager
{
	public ManagerPrichodov(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="AgentSpolocnosti", id="68", type="Notice"
	public void processPrichodZakaznikaTerm2(MessageForm message)
	{
	}

	//meta! sender="AgentSpolocnosti", id="62", type="Request"
	public void processNastupZakaznikovTerm2(MessageForm message)
	{
	}

	//meta! sender="AgentSpolocnosti", id="67", type="Notice"
	public void processPrichodZakaznikaTerm1(MessageForm message)
	{
	}

	//meta! sender="AgentSpolocnosti", id="61", type="Request"
	public void processNastupZakaznikovTerm1(MessageForm message)
	{
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.prichodZakaznikaTerm2:
			processPrichodZakaznikaTerm2(message);
		break;

		case Mc.nastupZakaznikovTerm2:
			processNastupZakaznikovTerm2(message);
		break;

		case Mc.prichodZakaznikaTerm1:
			processPrichodZakaznikaTerm1(message);
		break;

		case Mc.nastupZakaznikovTerm1:
			processNastupZakaznikovTerm1(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentPrichodov myAgent()
	{
		return (AgentPrichodov)super.myAgent();
	}

}
