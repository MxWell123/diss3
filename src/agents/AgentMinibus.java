package agents;

import OSPABA.*;
import OSPStat.Stat;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="48"
public class AgentMinibus extends Agent {

    private int counterMinibusov;
    private int typMinibusu;
    private Stat priemCasVradeTerm1;
    private Stat priemCasVradeTerm2;

    public AgentMinibus(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        priemCasVradeTerm1 = new Stat();
        priemCasVradeTerm2 = new Stat();
        // Setup component for the next replication
    }

    public int getCounterMinibusov() {
        counterMinibusov++;
        return counterMinibusov - 1;
    }

    public Stat getPriemCasVradeTerm1() {
        return priemCasVradeTerm1;
    }

    public Stat getPriemCasVradeTerm2() {
        return priemCasVradeTerm2;
    }
    

    public void pripocitajPriemCasRad1(double cas) {
        priemCasVradeTerm1.addSample(cas);
    }

    public void pripocitajPriemCasRad2(double cas) {
        priemCasVradeTerm2.addSample(cas);
    }

    public void pripocitajMinibus() {
        counterMinibusov++;
    }

    public int getTypMinibusu() {
        return typMinibusu;
    }

    public void setTypMinibusu(int typMinibusu) {
        this.typMinibusu = typMinibusu;
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerMinibus(Id.managerMinibus, mySim(), this);
        new ProcesPrechodMedziTerminalmi(Id.procesPrechodMedziTerminalmi, mySim(), this);
        new ProcesNastupZakaznikaDoMinibusu(Id.procesNastupZakaznikaDoMinibusu, mySim(), this);
        new ProcesVystupZakaznikaZMinibusu(Id.procesVystupZakaznikaZMinibusu, mySim(), this);
        addOwnMessage(Mc.koniecNastupu);
        addOwnMessage(Mc.vystupZakaznikaDoObsluhy);
        addOwnMessage(Mc.vystupZakaznikaTerm3);
        addOwnMessage(Mc.nastupZakaznikovZObsluhy);
        addOwnMessage(Mc.nastupZakaznikovTerm2);
        addOwnMessage(Mc.nastupZakaznikovTerm1);
        addOwnMessage(Mc.koniecVystupu);
        addOwnMessage(Mc.initPrichodMinibusov);
        addOwnMessage(Mc.koniecPrechodu);
    }
    //meta! tag="end"
}
