package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import java.util.Random;

//meta! id="1"
public class ManagerModelu extends Manager {

    private Random rand;
    private Random nasada = new Random();

    public ManagerModelu(int id, Simulation mySim, Agent myAgent) {
        super(id, mySim, myAgent);
        rand = new Random(nasada.nextLong());
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

    //meta! sender="AgentOkolia", id="98", type="Notice"
    public void processPrichodZakaznikaTerm2(MessageForm message) {
        message.setAddressee(Id.agentSpolocnosti);
        notice(message);
    }

    //meta! sender="AgentOkolia", id="27", type="Notice"
    public void processPrichodZakaznikaTerm1(MessageForm message) {
        message.setAddressee(Id.agentSpolocnosti);
        notice(message);
    }

    //meta! sender="AgentOkolia", id="100", type="Notice"
    public void processPrichodZakaznikaNaVratenieAuta(MessageForm message) {
        message.setAddressee(Id.agentSpolocnosti);
        notice(message);
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v ManagerModelu.");
    }

    //meta! sender="AgentOkolia", id="184", type="Notice"
    public void processInitPrichodMinibusov(MessageForm message) {
        message.setAddressee(myAgent().findAssistant(Id.planovacMinibusov));
        startContinualAssistant(message);
    }

    //meta! sender="PlanovacMinibusov", id="188", type="Finish"
    public void processFinish(MessageForm message) {
        message.setAddressee(Id.agentSpolocnosti);
        message.setCode(Mc.initPrichodMinibusov);
        notice(message);
    }

    //meta! sender="AgentSpolocnosti", id="222", type="Request"
    public void processVystupZakaznikaTerm3AgentSpolocnosti(MessageForm message) {
        message.setAddressee(Id.agentOkolia);
        request(message);
    }

    //meta! sender="AgentSpolocnosti", id="225", type="Notice"
    public void processOdchodZakaznikov(MessageForm message) {
        message.setAddressee(Id.agentOkolia);
        notice(message);
    }

    //meta! sender="AgentOkolia", id="224", type="Response"
    public void processVystupZakaznikaTerm3AgentOkolia(MessageForm message) {
        message.setAddressee(Id.agentOkolia);
        response(message);
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    public void init() {
    }

    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.prichodZakaznikaNaVratenieAuta:
                processPrichodZakaznikaNaVratenieAuta(message);
                break;

            case Mc.prichodZakaznikaTerm1:
                processPrichodZakaznikaTerm1(message);
                break;

            case Mc.initPrichodMinibusov:
                processInitPrichodMinibusov(message);
                break;

            case Mc.finish:
                processFinish(message);
                break;

            case Mc.vystupZakaznikaTerm3:
                switch (message.sender().id()) {
                    case Id.agentSpolocnosti:
                        processVystupZakaznikaTerm3AgentSpolocnosti(message);
                        break;

                    case Id.agentOkolia:
                        processVystupZakaznikaTerm3AgentOkolia(message);
                        break;
                }
                break;

            case Mc.prichodZakaznikaTerm2:
                processPrichodZakaznikaTerm2(message);
                break;

            case Mc.odchodZakaznikov:
                processOdchodZakaznikov(message);
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
