package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="2"
public class ManagerOkolia extends Manager {

    public ManagerOkolia(int id, Simulation mySim, Agent myAgent) {
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

    //meta! sender="AgentModelu", id="4", type="Notice"
    public void processInitPrichodyZakaznikov(MessageForm message) {

        MessageForm kopia = message.createCopy();
        kopia.setAddressee(Id.agentModelu);
        kopia.setCode(Mc.initPrichodMinibusov);
        notice(kopia);

        MessageForm kopia1 = message.createCopy();
        kopia1.setAddressee(myAgent().findAssistant(Id.planovacPrichodZakaznikovTerm1));
        startContinualAssistant(kopia1);

        MessageForm kopia2 = message.createCopy();
        kopia2.setAddressee(myAgent().findAssistant(Id.planovacPrichodZakaznikovTerm2));
        startContinualAssistant(kopia2);

        MessageForm kopia3 = message.createCopy();
        kopia3.setAddressee(myAgent().findAssistant(Id.planovacPrichodZakaznikovOdchod));
        startContinualAssistant(kopia3);
    }

    //meta! sender="PlanovacPrichodZakaznikovOdchod", id="79", type="Finish"
    public void processFinishPlanovacPrichodZakaznikovOdchod(MessageForm message) {
        message.setCode(Mc.prichodZakaznikaNaVratenieAuta);
        message.setAddressee(Id.agentModelu);
        notice(message);
    }

    //meta! sender="PlanovacPrichodZakaznikovTerm2", id="77", type="Finish"
    public void processFinishPlanovacPrichodZakaznikovTerm2(MessageForm message) {
        message.setCode(Mc.prichodZakaznikaTerm2);
        message.setAddressee(Id.agentModelu);
        notice(message);
    }

    //meta! sender="PlanovacPrichodZakaznikovTerm1", id="75", type="Finish"
    public void processFinishPlanovacPrichodZakaznikovTerm1(MessageForm message) {
        message.setCode(Mc.prichodZakaznikaTerm1);
        message.setAddressee(Id.agentModelu);
        notice(message);
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v ManagerOkolia.");
    }

    //meta! sender="AgentModelu", id="224", type="Request"
    public void processVystupZakaznikaTerm3(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        double pom = mySim().currentTime() - sprava.getZakaznik().getZaciatokCakania();
        sprava.getZakaznik().setCelkoveCakanie(pom);
        sprava.setAddressee(Id.agentModelu);
        response(sprava);
    }

    //meta! sender="AgentModelu", id="226", type="Notice"
    public void processOdchodZakaznikov(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        double pom = mySim().currentTime() - sprava.getZakaznik().getZaciatokCakania();
        sprava.getZakaznik().setCelkoveCakanie(pom);
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    public void init() {
    }

    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.finish:
                switch (message.sender().id()) {
                    case Id.planovacPrichodZakaznikovTerm2:
                        processFinishPlanovacPrichodZakaznikovTerm2(message);
                        break;

                    case Id.planovacPrichodZakaznikovOdchod:
                        processFinishPlanovacPrichodZakaznikovOdchod(message);
                        break;

                    case Id.planovacPrichodZakaznikovTerm1:
                        processFinishPlanovacPrichodZakaznikovTerm1(message);
                        break;
                }
                break;

            case Mc.vystupZakaznikaTerm3:
                processVystupZakaznikaTerm3(message);
                break;

            case Mc.odchodZakaznikov:
                processOdchodZakaznikov(message);
                break;

            case Mc.initPrichodyZakaznikov:
                processInitPrichodyZakaznikov(message);
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
