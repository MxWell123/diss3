package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;

//meta! id="187"
public class PlanovacMinibusov extends Scheduler {

    private int counter;
    private static final double CAS_MEDZERY = 30 * 60D;

    public PlanovacMinibusov(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
        counter = 0;
    }

    //meta! sender="AgentModelu", id="189", type="Notice"
    public void processKoniec(MessageForm message) {
    }

    //meta! sender="AgentModelu", id="188", type="Start"
    public void processStart(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        sprava.setCode(Mc.novyMinibus);
        sprava.setMinibus(new Minibus(counter, myAgent().getTypMinibusu()));
        counter++;
        this.hold(CAS_MEDZERY, sprava);
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v PlanovacMinibusov.");
    }

    //meta! sender="AgentModelu", id="195", type="Notice"
    public void processNovyMinibus(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        if (counter <= myAgent().getPocetMinibusov()) {
            if (mySim().currentTime() >= counter * CAS_MEDZERY) {
                sprava.setMinibus(new Minibus(counter, myAgent().getTypMinibusu()));
                counter++;
                this.hold(CAS_MEDZERY, sprava);
            }
        } else {
            message.setCode(Mc.koniec);
            this.assistantFinished(message);
        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.koniec:
                processKoniec(message);
                break;

            case Mc.start:
                processStart(message);
                break;

            case Mc.novyMinibus:
                processNovyMinibus(message);
                break;

            default:
                processDefault(message);
                break;
        }
    }
    //meta! tag="end"

    @Override
    public AgentModelu myAgent() {
        return (AgentModelu) super.myAgent();
    }

}
