package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="45"
public class ManagerObsluhy extends Manager
{
	public ManagerObsluhy(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentSpolocnosti", id="113", type="Notice"
	public void processNastupZakaznikovZObsluhy(MessageForm message)
	{
	}

	//meta! sender="AgentSpolocnosti", id="114", type="Notice"
	public void processVystupZakaznikaDoObsluhy(MessageForm message)
	{
	}

	//meta! sender="ProcesObsluhaZakaznika", id="87", type="Finish"
	public void processFinish(MessageForm message)
	{
	}

	//meta! sender="AgentSpolocnosti", id="83", type="Notice"
	public void processPrichodZakaznikaNaVratenieAuta(MessageForm message)
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
		case Mc.prichodZakaznikaNaVratenieAuta:
			processPrichodZakaznikaNaVratenieAuta(message);
		break;

		case Mc.nastupZakaznikovZObsluhy:
			processNastupZakaznikovZObsluhy(message);
		break;

		case Mc.vystupZakaznikaDoObsluhy:
			processVystupZakaznikaDoObsluhy(message);
		break;

		case Mc.finish:
			processFinish(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentObsluhy myAgent()
	{
		return (AgentObsluhy)super.myAgent();
	}

}
