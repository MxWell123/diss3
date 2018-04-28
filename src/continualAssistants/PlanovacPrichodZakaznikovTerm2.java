package continualAssistants;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import simulation.*;
import agents.*;

//meta! id="76"
public class PlanovacPrichodZakaznikovTerm2 extends Scheduler {

    private static final double HODINA = 3600D;
    private final ExponentialRNG[] generatory = new ExponentialRNG[18];
    private static final double[] vstupy = {3 / HODINA, 6 / HODINA, 9 / HODINA, 15 / HODINA, 17 / HODINA, 19 / HODINA,
        14 / HODINA, 6 / HODINA, 3 / HODINA, 4 / HODINA, 21 / HODINA, 14 / HODINA, 19 / HODINA,
        12 / HODINA, 5 / HODINA, 2 / HODINA, 3 / HODINA, 3 / HODINA};

    public PlanovacPrichodZakaznikovTerm2(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);

        for (int i = 0; i < 18; i++) {
            generatory[i] = new ExponentialRNG(vstupy[i]);
        }
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

	//meta! sender="AgentOkolia", id="77", type="Start"
	public void processStart(MessageForm message) {
        message.setCode(Mc.novyZakaznik);
        this.hold(myAgent().dajTrvanie(generatory, vstupy), message);
    }

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v PlanovacPrichodZakaznikovTerm2.");
    }

	//meta! sender="AgentOkolia", id="135", type="Notice"
	public void processNovyZakaznik(MessageForm message) {
        if (mySim().currentTime() >= myAgent().KONIEC_PRICHODOV) {
            message.setCode(Mc.koniec);
            this.assistantFinished(message);
        } else {
            MessageForm kopia = message.createCopy();
            this.hold(myAgent().dajTrvanie(generatory, vstupy), kopia);
            this.assistantFinished(message);
        }
    }

	//meta! sender="AgentOkolia", id="142", type="Notice"
	public void processKoniec(MessageForm message) {
    }

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.start:
			processStart(message);
		break;

		case Mc.koniec:
			processKoniec(message);
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
    public AgentOkolia myAgent() {
        return (AgentOkolia) super.myAgent();
    }

}