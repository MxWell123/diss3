package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import instantAssistants.*;

//meta! id="2"
public class ManagerOkolia extends Manager
{
	public ManagerOkolia(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentModelu", id="4", type="Notice"
	public void processInitPrichodyZakaznikov(MessageForm message)
	{
	}

	//meta! sender="PlanovacPrichodZakaznikovOdchod", id="79", type="Finish"
	public void processFinishPlanovacPrichodZakaznikovOdchod(MessageForm message)
	{
	}

	//meta! sender="PlanovacPrichodZakaznikovTerm1", id="75", type="Finish"
	public void processFinishPlanovacPrichodZakaznikovTerm1(MessageForm message)
	{
	}

	//meta! sender="PlanovacPrichodZakaznikovTerm2", id="77", type="Finish"
	public void processFinishPlanovacPrichodZakaznikovTerm2(MessageForm message)
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
		case Mc.finish:
			switch (message.sender().id())
			{
			case Id.planovacPrichodZakaznikovOdchod:
				processFinishPlanovacPrichodZakaznikovOdchod(message);
			break;

			case Id.planovacPrichodZakaznikovTerm1:
				processFinishPlanovacPrichodZakaznikovTerm1(message);
			break;

			case Id.planovacPrichodZakaznikovTerm2:
				processFinishPlanovacPrichodZakaznikovTerm2(message);
			break;
			}
		break;

		case Mc.initPrichodyZakaznikov:
			processInitPrichodyZakaznikov(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentOkolia myAgent()
	{
		return (AgentOkolia)super.myAgent();
	}

}
