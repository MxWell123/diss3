package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;
import java.util.Random;

//meta! id="105"
public class ProcesVystupZakaznikaZMinibusu extends Process {

    private Random nasada = new Random();
    private Random rnd1;

    public ProcesVystupZakaznikaZMinibusu(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
        rnd1 = new Random(nasada.nextLong());
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

	//meta! sender="AgentMinibus", id="106", type="Start"
	public void processStart(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        sprava.setCode(Mc.odosliZakaznikaDoRadu);
        sprava.setZakaznik(sprava.getMinibus().vyberZakaznikaZMinibusu());
        hold(2 + ((8 - 2) * rnd1.nextDouble()), sprava);

    }

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v ProcesVystupZakaznikaZMinibusu.");
    }

	//meta! sender="AgentMinibus", id="177", type="Notice"
	public void processKoniecVystupu(MessageForm message) {
        assistantFinished(message);
    }

	//meta! sender="AgentMinibus", id="182", type="Notice"
	public void processOdosliZakaznikaDoRadu(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        if (sprava.getMinibus().getPolohaMinibusu() == 2) { // minibus sa nachadza v Arcar
            sprava.setCode(Mc.vystupZakaznikaDoObsluhy);
            sprava.setAddressee(Id.agentSpolocnosti);
            notice(sprava);
        } else {
            sprava.setCode(Mc.vystupZakaznikaTerm3);
            sprava.setAddressee(Id.agentSpolocnosti);
            notice(sprava);
        }
        if (!sprava.getMinibus().jeMinibusPrazdny()) {
            sprava.setAddressee(this);
            sprava.setCode(Mc.start);
            notice(sprava);
        } else {
            sprava.setAddressee(this);
            sprava.setCode(Mc.koniecVystupu);
            notice(sprava);
        }
    }

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.koniecVystupu:
			processKoniecVystupu(message);
		break;

		case Mc.start:
			processStart(message);
		break;

		case Mc.odosliZakaznikaDoRadu:
			processOdosliZakaznikaDoRadu(message);
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

}