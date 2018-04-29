package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;

//meta! id="103"
public class ProcesPrechodMedziTerminalmi extends Process
{
	public ProcesPrechodMedziTerminalmi(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentMinibus", id="104", type="Start"
	public void processStart(MessageForm message)
	{
	}

	//meta! sender="AgentMinibus", id="178", type="Notice"
	public void processKoniecPrechodu(MessageForm message)
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
		case Mc.koniecPrechodu:
			processKoniecPrechodu(message);
		break;

		case Mc.start:
			processStart(message);
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
