package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="38"
public class ManagerSpolocnosti extends Manager {

    public ManagerSpolocnosti(int id, Simulation mySim, Agent myAgent) {
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

	//meta! sender="AgentMinibus", id="101", type="Request"
	public void processNastupZakaznikovZObsluhyAgentMinibus(MessageForm message) {
        MyMessage sprava = (MyMessage) message.createCopy();
        sprava.setAddressee(Id.agentMinibus);
        sprava.setZakaznik(myAgent().vyberZakaznikaZRaduNaMinibus());
        response(sprava);
    }

	//meta! sender="AgentMinibus", id="116", type="Notice"
	public void processVystupZakaznikaTerm3(MessageForm message) {
        message.setAddressee(Id.agentOdchodu);
        notice(message);
    }

	//meta! sender="AgentModelu", id="42", type="Notice"
	public void processPrichodZakaznikaTerm2(MessageForm message) {
        message.setAddressee(Id.agentPrichodov);
        notice(message);
    }

	//meta! sender="AgentPrichodov", id="62", type="Response"
	public void processNastupZakaznikovTerm2AgentPrichodov(MessageForm message) {
        message.setAddressee(Id.agentMinibus);
        response(message);
    }

	//meta! sender="AgentMinibus", id="71", type="Request"
	public void processNastupZakaznikovTerm2AgentMinibus(MessageForm message) {
        message.setAddressee(Id.agentPrichodov);
        request(message);
    }

	//meta! sender="AgentMinibus", id="55", type="Request"
	public void processNastupZakaznikovTerm1AgentMinibus(MessageForm message) {
        message.setAddressee(Id.agentPrichodov);
        request(message);
    }

	//meta! sender="AgentPrichodov", id="61", type="Response"
	public void processNastupZakaznikovTerm1AgentPrichodov(MessageForm message) {
        message.setAddressee(Id.agentMinibus);
        response(message);
    }

	//meta! sender="AgentModelu", id="41", type="Notice"
	public void processPrichodZakaznikaTerm1(MessageForm message) {
        message.setAddressee(Id.agentPrichodov);
        notice(message);
    }

	//meta! sender="AgentMinibus", id="50", type="Notice"
	public void processVystupZakaznikaDoObsluhy(MessageForm message) {
        message.setAddressee(Id.agentObsluhy);
        notice(message);
    }

	//meta! sender="AgentModelu", id="43", type="Notice"
	public void processPrichodZakaznikaNaVratenieAuta(MessageForm message) {
        message.setAddressee(Id.agentObsluhy);
        notice(message);
    }

	//meta! sender="AgentModelu", id="44", type="Notice"
	public void processInitPrichodMinibusov(MessageForm message) {
        message.setAddressee(Id.agentMinibus);
        notice(message);
    }

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message) {
     throw new UnsupportedOperationException("Vykonal sa default v ManagerSpolocnosti.");
    }

	//meta! sender="AgentObsluhy", id="158", type="Notice"
	public void processNastupZakaznikovZObsluhyAgentObsluhy(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        myAgent().pridajZakaznikDoRaduNaMinibus(sprava.getZakaznik());
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
		case Mc.prichodZakaznikaTerm1:
			processPrichodZakaznikaTerm1(message);
		break;

		case Mc.nastupZakaznikovTerm1:
			switch (message.sender().id())
			{
			case Id.agentPrichodov:
				processNastupZakaznikovTerm1AgentPrichodov(message);
			break;

			case Id.agentMinibus:
				processNastupZakaznikovTerm1AgentMinibus(message);
			break;
			}
		break;

		case Mc.prichodZakaznikaTerm2:
			processPrichodZakaznikaTerm2(message);
		break;

		case Mc.vystupZakaznikaDoObsluhy:
			processVystupZakaznikaDoObsluhy(message);
		break;

		case Mc.nastupZakaznikovTerm2:
			switch (message.sender().id())
			{
			case Id.agentPrichodov:
				processNastupZakaznikovTerm2AgentPrichodov(message);
			break;

			case Id.agentMinibus:
				processNastupZakaznikovTerm2AgentMinibus(message);
			break;
			}
		break;

		case Mc.prichodZakaznikaNaVratenieAuta:
			processPrichodZakaznikaNaVratenieAuta(message);
		break;

		case Mc.initPrichodMinibusov:
			processInitPrichodMinibusov(message);
		break;

		case Mc.nastupZakaznikovZObsluhy:
			switch (message.sender().id())
			{
			case Id.agentObsluhy:
				processNastupZakaznikovZObsluhyAgentObsluhy(message);
			break;

			case Id.agentMinibus:
				processNastupZakaznikovZObsluhyAgentMinibus(message);
			break;
			}
		break;

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
    public AgentSpolocnosti myAgent() {
        return (AgentSpolocnosti) super.myAgent();
    }

}