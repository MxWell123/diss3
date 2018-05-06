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
    public void processNastupZakaznikovZObsluhy(MessageForm message) {
        MyMessage sprava = (MyMessage) message.createCopy();
        sprava.setAddressee(Id.agentMinibus);
        sprava.setCode(Mc.nastupZakaznikovZObsluhy);
        sprava.setZakaznik(myAgent().vyberZakaznikaZRaduNaMinibus());
        response(sprava);
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

    //meta! sender="AgentMinibus", id="214", type="Request"
    public void processVystupZakaznikaDoObsluhy(MessageForm message) {
        message.setAddressee(Id.agentObsluhy);
        notice(message);
    }

    //meta! sender="AgentMinibus", id="213", type="Request"
    public void processVystupZakaznikaTerm3AgentMinibus(MessageForm message) {
        message.setAddressee(Id.agentModelu);
        request(message);
    }

    //meta! sender="AgentObsluhy", id="220", type="Notice"
    public void processPrichodZakaznikovNaCakanieNaMinibus(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        myAgent().pridajZakaznikDoRaduNaMinibus(sprava.getZakaznik());
    }

    //meta! sender="AgentObsluhy", id="230", type="Notice"
    public void processOdchodZakaznikov(MessageForm message) {
        message.setAddressee(Id.agentModelu);
        notice(message);
    }

    //meta! sender="AgentModelu", id="222", type="Response"
    public void processVystupZakaznikaTerm3AgentModelu(MessageForm message) {
        message.setAddressee(Id.agentMinibus);
        response(message);
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    public void init() {
    }

    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.nastupZakaznikovZObsluhy:
                processNastupZakaznikovZObsluhy(message);
                break;

            case Mc.vystupZakaznikaTerm3:
                switch (message.sender().id()) {
                    case Id.agentMinibus:
                        processVystupZakaznikaTerm3AgentMinibus(message);
                        break;

                    case Id.agentModelu:
                        processVystupZakaznikaTerm3AgentModelu(message);
                        break;
                }
                break;

            case Mc.odchodZakaznikov:
                processOdchodZakaznikov(message);
                break;

            case Mc.vystupZakaznikaDoObsluhy:
                processVystupZakaznikaDoObsluhy(message);
                break;

            case Mc.prichodZakaznikovNaCakanieNaMinibus:
                processPrichodZakaznikovNaCakanieNaMinibus(message);
                break;

            case Mc.prichodZakaznikaTerm2:
                processPrichodZakaznikaTerm2(message);
                break;

            case Mc.prichodZakaznikaTerm1:
                processPrichodZakaznikaTerm1(message);
                break;

            case Mc.nastupZakaznikovTerm2:
                switch (message.sender().id()) {
                    case Id.agentMinibus:
                        processNastupZakaznikovTerm2AgentMinibus(message);
                        break;

                    case Id.agentPrichodov:
                        processNastupZakaznikovTerm2AgentPrichodov(message);
                        break;
                }
                break;

            case Mc.nastupZakaznikovTerm1:
                switch (message.sender().id()) {
                    case Id.agentMinibus:
                        processNastupZakaznikovTerm1AgentMinibus(message);
                        break;

                    case Id.agentPrichodov:
                        processNastupZakaznikovTerm1AgentPrichodov(message);
                        break;
                }
                break;

            case Mc.initPrichodMinibusov:
                processInitPrichodMinibusov(message);
                break;

            case Mc.prichodZakaznikaNaVratenieAuta:
                processPrichodZakaznikaNaVratenieAuta(message);
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
