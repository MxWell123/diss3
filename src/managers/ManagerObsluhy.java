package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="45"
public class ManagerObsluhy extends Manager {

    private MySimulation mySim;

    public ManagerObsluhy(int id, Simulation mySim, Agent myAgent) {
        super(id, mySim, myAgent);
        this.mySim = (MySimulation) mySim;
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

    //meta! sender="ProcesObsluhaZakaznika", id="87", type="Finish"
    public void processFinish(MessageForm message) {
        MyMessage nextMessage = (MyMessage) message;
        if (nextMessage.getZakaznik().isTyp()) {
            nextMessage.createCopy().setAddressee(this);
            nextMessage.setCode(Mc.nastupZakaznikovZObsluhy);
            notice(nextMessage);
        }
        myAgent().odpocitajVytazenychPrac();
        mySim.refreshGUI();
        if (0 < myAgent().velkostRadu()) {
            nextMessage.setZakaznik(myAgent().vyberZakaznikaZRadu());
            startWork(nextMessage);            
        }        

    }

    //meta! sender="AgentSpolocnosti", id="83", type="Notice"
    public void processPrichodZakaznikaNaVratenieAuta(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        if (!myAgent().jeVolnyPracovnik()) {
            myAgent().pridajZakaznikDoRadu(sprava.getZakaznik());
        } else {
            startWork(message);            
        }
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v ManagerObsluhy.");
    }

    //meta! sender="AgentSpolocnosti", id="114", type="Notice"
    public void processVystupZakaznikaDoObsluhy(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        if (!myAgent().jeVolnyPracovnik()) {
            myAgent().pridajZakaznikDoRadu(sprava.getZakaznik());
        } else {
            startWork(message);
        }

    }

    //meta! userInfo="Removed from model"
    public void processNastupZakaznikovZObsluhy(MessageForm message) {
        message.setAddressee(Id.agentSpolocnosti);
        notice(message);
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

            case Mc.vystupZakaznikaDoObsluhy:
                processVystupZakaznikaDoObsluhy(message);
                break;

            case Mc.finish:
                processFinish(message);
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

    private void startWork(MessageForm message) {
        myAgent().pripocitajVytazenychPrac();
        mySim.refreshGUI();
        message.setAddressee(myAgent().findAssistant(Id.procesObsluhaZakaznika));
        startContinualAssistant(message);
    }

}
