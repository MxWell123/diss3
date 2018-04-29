package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="60"
public class ManagerOdchodu extends Manager {

    public ManagerOdchodu(int id, Simulation mySim, Agent myAgent) {
        super(id, mySim, myAgent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication

        if (petriNet() != null) {
            petriNet().clear();
        }
    }

	//meta! sender="AgentSpolocnosti", id="84", type="Notice"
	public void processVystupZakaznikaTerm3(MessageForm message) {
        //statistiky
        System.out.println("sssssssssss");
    }

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message) {
         throw new UnsupportedOperationException("Vykonal sa default v ManagerOdchodu.");
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
		case Mc.vystupZakaznikaTerm3:
			processVystupZakaznikaTerm3(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

    @Override
    public AgentOdchodu myAgent() {
        return (AgentOdchodu) super.myAgent();
    }

}