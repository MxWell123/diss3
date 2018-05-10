package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="38"
public class AgentSpolocnosti extends Agent {

    private SimQueue<Zakaznik> frontZakaznikovNaMinibus;
    private Stat priemernyRadNaMinibus;
    private Stat primernyCasVRadeNaMinibus;

    public AgentSpolocnosti(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    public void pridajZakaznikDoRaduNaMinibus(Zakaznik zak) {
        frontZakaznikovNaMinibus.add(zak);
    }

    public Zakaznik vyberZakaznikaZRaduNaMinibus() {
        if (frontZakaznikovNaMinibus.isEmpty()) {
            return null;
        }
        return frontZakaznikovNaMinibus.poll();
    }

    @Override
    public void prepareReplication() {
        frontZakaznikovNaMinibus = new SimQueue<>();
        priemernyRadNaMinibus = new Stat();
        primernyCasVRadeNaMinibus = new Stat();
        super.prepareReplication();
        // Setup component for the next replication
    }

    public void pridajDoStatistikyCasRadMinibus(double cas) {
        primernyCasVRadeNaMinibus.addSample(mySim().currentTime() - cas);
    }

    public Stat getPrimernyCasVRadeNaMinibus() {
        return primernyCasVRadeNaMinibus;
    }

    public void pridajDoStatistikyRadMinibus() {
        priemernyRadNaMinibus.addSample(frontZakaznikovNaMinibus.size());
    }

    public Stat getPriemernyRadNaMinibus() {
        return priemernyRadNaMinibus;
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerSpolocnosti(Id.managerSpolocnosti, mySim(), this);
        addOwnMessage(Mc.vystupZakaznikaDoObsluhy);
        addOwnMessage(Mc.vystupZakaznikaTerm3);
        addOwnMessage(Mc.nastupZakaznikovZObsluhy);
        addOwnMessage(Mc.nastupZakaznikovTerm2);
        addOwnMessage(Mc.prichodZakaznikaTerm2);
        addOwnMessage(Mc.nastupZakaznikovTerm1);
        addOwnMessage(Mc.prichodZakaznikaTerm1);
        addOwnMessage(Mc.odchodZakaznikov);
        addOwnMessage(Mc.prichodZakaznikovNaCakanieNaMinibus);
        addOwnMessage(Mc.initPrichodMinibusov);
        addOwnMessage(Mc.prichodZakaznikaNaVratenieAuta);
    }
    //meta! tag="end"
}
