package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;

//meta! id="74"
public class PlanovacPrichodZakaznikovTerm1 extends Scheduler
{
	public PlanovacPrichodZakaznikovTerm1(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentOkolia", id="140", type="Notice"
	public void processKoniec(MessageForm message)
	{
	}

	//meta! sender="AgentOkolia", id="75", type="Start"
	public void processStart(MessageForm message)
	{
	}

	//meta! sender="AgentOkolia", id="130", type="Notice"
	public void processNovyZakaznik(MessageForm message)
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
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.koniec:
			processKoniec(message);
		break;

		case Mc.start:
			processStart(message);
		break;

		case Mc.novyZakaznik:
			processNovyZakaznik(message);
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
