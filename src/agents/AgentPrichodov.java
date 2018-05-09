package agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="59"
public class AgentPrichodov extends Agent {

    private SimQueue<Zakaznik> frontZakaznikovTerm1;
    private SimQueue<Zakaznik> frontZakaznikovTerm2;
    private Stat priemernyRadZakaznikovTerm1;
    private Stat priemernyRadZakaznikovTerm2;

    public AgentPrichodov(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        frontZakaznikovTerm1 = new SimQueue<>();
        frontZakaznikovTerm2 = new SimQueue<>();
        priemernyRadZakaznikovTerm1 = new Stat();
        priemernyRadZakaznikovTerm2 = new Stat();
        super.prepareReplication();
        // Setup component for the next replication
    }

    public void pridajZakaznikDoRaduTerm1(Zakaznik zak) {
        frontZakaznikovTerm1.add(zak);
    }

    public Zakaznik vyberZakaznikaZRaduTerm1() {
        if (frontZakaznikovTerm1.isEmpty()) {
            return null;
        }
        return frontZakaznikovTerm1.poll();
    }

    public void pridajDoStatistikyTerm1() {
        priemernyRadZakaznikovTerm1.addSample(frontZakaznikovTerm1.size());
    }

    public void pridajDoStatistikyTerm2() {
        priemernyRadZakaznikovTerm2.addSample(frontZakaznikovTerm2.size());
    }

    public Stat getPriemernyRadZakaznikovTerm1() {
        return priemernyRadZakaznikovTerm1;
    }

    public Stat getPriemernyRadZakaznikovTerm2() {
        return priemernyRadZakaznikovTerm2;
    }

    public void pridajZakaznikDoRaduTerm2(Zakaznik zak) {
        frontZakaznikovTerm2.add(zak);
    }

    public Zakaznik vyberZakaznikaZRaduTerm2() {
        if (frontZakaznikovTerm2.isEmpty()) {
            return null;
        }
        return frontZakaznikovTerm2.poll();
    }

    public int getPocetZakRad1() {
        return frontZakaznikovTerm1.size();
    }

    public int getPocetZakRad2() {
        return frontZakaznikovTerm2.size();
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerPrichodov(Id.managerPrichodov, mySim(), this);
        addOwnMessage(Mc.nastupZakaznikovTerm2);
        addOwnMessage(Mc.prichodZakaznikaTerm2);
        addOwnMessage(Mc.nastupZakaznikovTerm1);
        addOwnMessage(Mc.prichodZakaznikaTerm1);
    }
    //meta! tag="end"
}
