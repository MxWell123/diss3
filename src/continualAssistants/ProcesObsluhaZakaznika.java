package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;
import OSPABA.Process;
import OSPRNG.*;
import java.util.Random;

//meta! id="86"
public class ProcesObsluhaZakaznika extends Process {

    private static final int MINUTA = 60;

    TriangularRNG trian = new TriangularRNG(1.6 * MINUTA, 2.65 * MINUTA, 5.1 * MINUTA);
    UniformContinuousRNG rovn = new UniformContinuousRNG(1.6 * MINUTA, 2.1 * MINUTA);
    ExponentialRNG exp = new ExponentialRNG(1.97 * MINUTA);
    UniformContinuousRNG rovnOut = new UniformContinuousRNG(1.3 * MINUTA, 2.5 * MINUTA);
    Random nasada = new Random();
    Random rnd = new Random(nasada.nextLong());
    private MySimulation mySim;

    public ProcesObsluhaZakaznika(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
        this.mySim = (MySimulation) mySim;
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

	//meta! sender="AgentObsluhy", id="87", type="Start"
	public void processStart(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        sprava.setCode(Mc.koniecObsluhy);
        double pom = 0.0;
        if (sprava.getZakaznik().isTyp() == false) {
            if (rnd.nextDouble() < 0.45) {
                pom = trian.sample();
            } else {
                pom = rovn.sample();
            }
            hold(pom, message);
        } else {
            if (rnd.nextDouble() < 0.33) {
                pom = exp.sample();
            } else {
                pom = rovnOut.sample();
            }
            hold(pom, message);
        }
    }

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v ProcesObsluhaZakaznika.");
    }

	//meta! sender="AgentObsluhy", id="169", type="Notice"
	public void processKoniecObsluhy(MessageForm message) {
        MyMessage kopia = (MyMessage) message;
        assistantFinished(kopia);
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

		case Mc.koniecObsluhy:
			processKoniecObsluhy(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

    @Override
    public AgentObsluhy myAgent() {
        return (AgentObsluhy) super.myAgent();
    }

}