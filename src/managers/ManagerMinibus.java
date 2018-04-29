package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="48"
public class ManagerMinibus extends Manager {

    public ManagerMinibus(int id, Simulation mySim, Agent myAgent) {
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

	//meta! sender="AgentSpolocnosti", id="101", type="Response"
	public void processNastupZakaznikovZObsluhy(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        if (sprava.getZakaznik() != null && !sprava.getMinibus().jeMinibusPlny()) {
            startNastup(message);
        } else {
            startPresun(message);
        }
    }

	//meta! sender="AgentSpolocnosti", id="71", type="Response"
	public void processNastupZakaznikovTerm2(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        if (sprava.getZakaznik() != null && !sprava.getMinibus().jeMinibusPlny()) {
            startNastup(message);
        } else {
            startPresun(message);
        }
    }

	//meta! sender="AgentSpolocnosti", id="55", type="Response"
	public void processNastupZakaznikovTerm1(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        if (sprava.getZakaznik() != null && !sprava.getMinibus().jeMinibusPlny()) {
            startNastup(message);
        } else {
            startPresun(message);
        }
    }
    //meta! sender="ProcesNastupZakaznikaDoMinibusu", id="89", type="Finish"
	//meta! sender="ProcesNastupZakaznikaDoMinibusu", id="89", type="Finish"
	public void processFinishProcesNastupZakaznikaDoMinibusu(MessageForm message) {
        message.setAddressee(Id.agentSpolocnosti);
        message.setCode(Mc.nastupZakaznikovTerm1);
        request(message);
    }

	//meta! sender="ProcesPrechodMedziTerminalmi", id="104", type="Finish"
	public void processFinishProcesPrechodMedziTerminalmi(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        int polohaMinibus = sprava.getMinibus().getPolohaMinibusu();
        if (polohaMinibus == 1) { // ak sa nachadza v terminale 2
            sprava.setAddressee(Id.agentSpolocnosti);
            sprava.setCode(Mc.nastupZakaznikovTerm2);
            request(sprava);
        } else if (polohaMinibus == 2) { // ak sa nachadza Arcar
            startVystup(sprava);
            
            sprava.setAddressee(Id.agentSpolocnosti);
            sprava.setCode(Mc.nastupZakaznikovZObsluhy);
            request(sprava);
            
        } else if (polohaMinibus == 3) { // ak sa nachadza v terminale3
            startVystup(sprava);
        } else if (polohaMinibus == 0) { // ak sa nachadza v terminale1
            sprava.setAddressee(Id.agentSpolocnosti);
            sprava.setCode(Mc.nastupZakaznikovTerm1);
            request(sprava);
        }
        sprava.setAddressee(Id.agentSpolocnosti);
        sprava.setCode(Mc.nastupZakaznikovTerm1);
        request(sprava);
    }

	//meta! sender="ProcesVystupZakaznikaZMinibusu", id="106", type="Finish"
	public void processFinishProcesVystupZakaznikaZMinibusu(MessageForm message) {

    }

	//meta! sender="AgentSpolocnosti", id="56", type="Notice"
	public void processInitPrichodMinibusov(MessageForm message) {
        message.setAddressee(Id.agentSpolocnosti);
        message.setCode(Mc.nastupZakaznikovTerm1);
        request(message);
    }

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message) {
        switch (message.code()) {
        }
    }

    //meta! userInfo="Removed from model"
    public void processFinishPlanovacMinibusov(MessageForm message) {
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

			case Id.procesNastupZakaznikaDoMinibusu:
				processFinishProcesNastupZakaznikaDoMinibusu(message);
			break;

			case Id.procesVystupZakaznikaZMinibusu:
				processFinishProcesVystupZakaznikaZMinibusu(message);
			break;
			}
		break;

		case Mc.nastupZakaznikovZObsluhy:
			processNastupZakaznikovZObsluhy(message);
		break;

		case Mc.nastupZakaznikovTerm2:
			processNastupZakaznikovTerm2(message);
		break;

		case Mc.initPrichodMinibusov:
			processInitPrichodMinibusov(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

    @Override
    public AgentMinibus myAgent() {
        return (AgentMinibus) super.myAgent();
    }

    private void startNastup(MessageForm message) {
        message.setAddressee(myAgent().findAssistant(Id.procesNastupZakaznikaDoMinibusu));
        startContinualAssistant(message);
    }

    private void startPresun(MessageForm message) {
        message.setAddressee(myAgent().findAssistant(Id.procesPrechodMedziTerminalmi));
        startContinualAssistant(message);
    }

    private void startVystup(MessageForm message) {
        message.setAddressee(myAgent().findAssistant(Id.procesVystupZakaznikaZMinibusu));
        startContinualAssistant(message);
    }
}