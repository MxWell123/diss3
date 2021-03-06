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
            if (mySim().currentTime() > 0.5 * 60 * 60) {
                myAgent().pripocitajPriemCasRad2(mySim().currentTime() - sprava.getZakaznik().getZaciatokCakania()); // stat
            }
            startNastup(message);
        } else {
            startPresun(message);
        }
    }

    //meta! sender="AgentSpolocnosti", id="55", type="Response"
    public void processNastupZakaznikovTerm1(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        if (sprava.getZakaznik() != null && !sprava.getMinibus().jeMinibusPlny()) {
            if (mySim().currentTime() > 0.5 * 60 * 60) {
                myAgent().pripocitajPriemCasRad1(mySim().currentTime() - sprava.getZakaznik().getZaciatokCakania()); // stat
            }
            startNastup(sprava);
        } else {
            startPresun(sprava);
        }
    }

    //meta! sender="ProcesNastupZakaznikaDoMinibusu", id="89", type="Finish"
    public void processFinishProcesNastupZakaznikaDoMinibusu(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        int polohaMinibus = sprava.getMinibus().getPolohaMinibusu();
        if (polohaMinibus == 0) {
            sprava.setAddressee(Id.agentSpolocnosti);
            sprava.setCode(Mc.nastupZakaznikovTerm1);
        } else if (polohaMinibus == 1) {
            sprava.setAddressee(Id.agentSpolocnosti);
            sprava.setCode(Mc.nastupZakaznikovTerm2);
        } else if (polohaMinibus == 2) {
            sprava.setAddressee(Id.agentSpolocnosti);
            sprava.setCode(Mc.nastupZakaznikovZObsluhy);
        }
        request(sprava);
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
            if (!sprava.getMinibus().jeMinibusPrazdny()) { // ak minibus nie je prazdny
                startVystup(sprava);
            } else {
                startPresun(sprava); // ak prisiel prazdny
            }
        } else if (polohaMinibus == 3) { // ak sa nachadza v terminale3
            startVystup(sprava);
        } else if (polohaMinibus == 0) { // ak sa nachadza v terminale1
            sprava.setAddressee(Id.agentSpolocnosti);
            sprava.setCode(Mc.nastupZakaznikovTerm1);
            request(sprava);
        }

    }

    //meta! sender="ProcesVystupZakaznikaZMinibusu", id="106", type="Finish"
    public void processFinishProcesVystupZakaznikaZMinibusu(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        if (sprava.getMinibus().getPolohaMinibusu() == 2) { // ak je v arcar
            sprava.setAddressee(Id.agentSpolocnosti);
            sprava.setCode(Mc.vystupZakaznikaDoObsluhy);
            request(sprava);
        } else if (sprava.getMinibus().getPolohaMinibusu() == 3) { // ak je v term3
            sprava.setAddressee(Id.agentSpolocnosti);
            sprava.setCode(Mc.vystupZakaznikaTerm3);
            request(sprava);
        }
    }

    //meta! sender="AgentSpolocnosti", id="56", type="Notice"
    public void processInitPrichodMinibusov(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        sprava.setAddressee(Id.agentSpolocnosti);
        sprava.setCode(Mc.nastupZakaznikovTerm1);
        request(sprava);
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v ManagerMinibus.");
    }

    //meta! sender="AgentSpolocnosti", id="214", type="Response"
    public void processVystupZakaznikaDoObsluhy(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        if (!sprava.getMinibus().jeMinibusPrazdny()) {
            startVystup(sprava);
        } else {
            sprava.setAddressee(Id.agentSpolocnosti);
            sprava.setCode(Mc.nastupZakaznikovZObsluhy);
            request(sprava);
        }

    }

//meta! sender="AgentSpolocnosti", id="213", type="Response"
    public void processVystupZakaznikaTerm3(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        if (!sprava.getMinibus().jeMinibusPrazdny()) {
            startVystup(sprava);
        } else {
            startPresun(sprava);
        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    public void init() {
    }

    @Override
    public void processMessage(MessageForm message) {
        switch (message.code()) {
            case Mc.nastupZakaznikovTerm2:
                processNastupZakaznikovTerm2(message);
                break;

            case Mc.vystupZakaznikaDoObsluhy:
                processVystupZakaznikaDoObsluhy(message);
                break;

            case Mc.finish:
                switch (message.sender().id()) {
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

            case Mc.vystupZakaznikaTerm3:
                processVystupZakaznikaTerm3(message);
                break;

            case Mc.nastupZakaznikovTerm1:
                processNastupZakaznikovTerm1(message);
                break;

            case Mc.initPrichodMinibusov:
                processInitPrichodMinibusov(message);
                break;

            case Mc.nastupZakaznikovZObsluhy:
                processNastupZakaznikovZObsluhy(message);
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
