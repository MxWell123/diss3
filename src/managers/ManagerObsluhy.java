package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;

//meta! id="45"
public class ManagerObsluhy extends Manager {

    public ManagerObsluhy(int id, Simulation mySim, Agent myAgent) {
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

    //meta! sender="ProcesObsluhaZakaznika", id="87", type="Finish"
    public void processFinish(MessageForm message) {
        MyMessage nextMessage = (MyMessage) message;
        MyMessage kopia = (MyMessage) nextMessage.createCopy();
        if (mySim().currentTime() > 0.5 * 60 * 60) {
            myAgent().pripocitajPriemernyCasVRade(mySim().currentTime() - nextMessage.getZakaznik().getPrichodDoObsluhy());
        }
        if (nextMessage.getZakaznik().isTyp()) {
            nextMessage.setAddressee(Id.agentSpolocnosti);
            nextMessage.setCode(Mc.prichodZakaznikovNaCakanieNaMinibus);
            notice(nextMessage);
        } else {
            nextMessage.setAddressee(Id.agentSpolocnosti);
            nextMessage.setCode(Mc.odchodZakaznikov);
            notice(nextMessage);
        }

        if (0 < myAgent().velkostRadu()) {
            Zakaznik zak = myAgent().vyberZakaznikaZRadu();
            kopia.setZakaznik(zak);
            startWork(kopia);
        } else {
            myAgent().odpocitajVytazenychPrac();
            if (mySim().currentTime() > 0.5 * 60 * 60) {
                myAgent().pridajDoStatistiky();
                myAgent().pridajDoStatistikyVytazenost();
            }
        }

    }

    //meta! sender="AgentSpolocnosti", id="83", type="Notice"
    public void processPrichodZakaznikaNaVratenieAuta(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        sprava.getZakaznik().setZaciatokCakania(mySim().currentTime());
        sprava.getZakaznik().setPrichodDoObsluhy(mySim().currentTime());
        if (!myAgent().jeVolnyPracovnik()) {
            myAgent().pridajZakaznikDoRadu(sprava.getZakaznik());
            if (mySim().currentTime() > 0.5 * 60 * 60) {
                myAgent().pridajDoStatistiky();
                myAgent().pridajDoStatistikyVytazenost();
            }
        } else {
            int cisloZamestnanca = myAgent().pripocitajVytazenychPrac();
            sprava.getZakaznik().setCisloZamestnanca(cisloZamestnanca);
            startWork(sprava);
        }
    }

    //meta! userInfo="Process messages defined in code", id="0"
    public void processDefault(MessageForm message) {
        throw new UnsupportedOperationException("Vykonal sa default v ManagerObsluhy.");
    }

    //meta! sender="AgentSpolocnosti", id="114", type="Request"
    public void processVystupZakaznikaDoObsluhy(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        MyMessage sprava2 = (MyMessage) sprava.createCopy();
        sprava.getZakaznik().setPrichodDoObsluhy(mySim().currentTime());
        if (!myAgent().jeVolnyPracovnik()) {
            myAgent().pridajZakaznikDoRadu(sprava.getZakaznik());
            if (mySim().currentTime() > 0.5 * 60 * 60) {
                myAgent().pridajDoStatistiky();
                myAgent().pridajDoStatistikyVytazenost();
            }
        } else {
            int cisloZamestnanca = myAgent().pripocitajVytazenychPrac();
            sprava.getZakaznik().setCisloZamestnanca(cisloZamestnanca);
            startWork(message);
        }
        sprava2.setAddressee(Id.agentSpolocnosti);
        response(sprava2);
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    public void init() {
    }

    @Override
    public void processMessage(MessageForm message) {
        MyMessage sprava = (MyMessage) message;
        switch (sprava.code()) {
            case Mc.prichodZakaznikaNaVratenieAuta:
                processPrichodZakaznikaNaVratenieAuta(sprava);
                break;

            case Mc.finish:
                processFinish(sprava);
                break;

            case Mc.vystupZakaznikaDoObsluhy:
                processVystupZakaznikaDoObsluhy(sprava);
                break;

            default:
                processDefault(sprava);
                break;
        }
    }
    //meta! tag="end"

    @Override
    public AgentObsluhy myAgent() {
        return (AgentObsluhy) super.myAgent();
    }

    private void startWork(MessageForm message) {
        message.setAddressee(myAgent().findAssistant(Id.procesObsluhaZakaznika));
        startContinualAssistant(message);
    }

}
