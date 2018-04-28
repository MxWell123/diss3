package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="48"
public class ManagerMinibus extends Manager
{
	public ManagerMinibus(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentSpolocnosti", id="101", type="Response"
	public void processNastupZakaznikovZObsluhy(MessageForm message)
	{
	}

	//meta! sender="AgentSpolocnosti", id="71", type="Response"
	public void processNastupZakaznikovTerm2(MessageForm message)
	{
	}

	//meta! sender="AgentSpolocnosti", id="55", type="Response"
	public void processNastupZakaznikovTerm1(MessageForm message)
	{
	}

	//meta! sender="ProcesNastupZakaznikaDoMinibusu", id="89", type="Finish"
	public void processFinishProcesNastupZakaznikaDoMinibusu(MessageForm message)
	{
	}

	//meta! sender="ProcesPrechodMedziTerminalmi", id="104", type="Finish"
	public void processFinishProcesPrechodMedziTerminalmi(MessageForm message)
	{
	}

	//meta! sender="ProcesVystupZakaznikaZMinibusu", id="106", type="Finish"
	public void processFinishProcesVystupZakaznikaZMinibusu(MessageForm message)
	{
	}

	//meta! sender="AgentSpolocnosti", id="56", type="Notice"
	public void processInitPrichodMinibusov(MessageForm message)
	{
           
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="PlanovacMinibusov", id="151", type="Finish"
	public void processFinishPlanovacMinibusov(MessageForm message)
	{
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
		case Mc.nastupZakaznikovTerm1:
			processNastupZakaznikovTerm1(message);
		break;

		case Mc.finish:
			switch (message.sender().id())
			{
			case Id.procesPrechodMedziTerminalmi:
				processFinishProcesPrechodMedziTerminalmi(message);
			break;

			case Id.planovacMinibusov:
				processFinishPlanovacMinibusov(message);
			break;

			case Id.procesNastupZakaznikaDoMinibusu:
				processFinishProcesNastupZakaznikaDoMinibusu(message);
			break;

			case Id.procesVystupZakaznikaZMinibusu:
				processFinishProcesVystupZakaznikaZMinibusu(message);
			break;
			}
		break;

		case Mc.nastupZakaznikovTerm2:
			processNastupZakaznikovTerm2(message);
		break;

		case Mc.initPrichodMinibusov:
			processInitPrichodMinibusov(message);
		break;

		case Mc.nastupZakaznikovZObsluhy:
			processNastupZakaznikovZObsluhy(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentMinibus myAgent()
	{
		return (AgentMinibus)super.myAgent();
	}

}