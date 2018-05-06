package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;

//meta! id="48"
public class AgentMinibus extends Agent {

    private int counterMinibusov;
    private int typMinibusu;

    public AgentMinibus(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

    public int getCounterMinibusov() {
        counterMinibusov++;
        return counterMinibusov - 1;
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
	private void init()
	{
		new ManagerMinibus(Id.managerMinibus, mySim(), this);
		new ProcesNastupZakaznikaDoMinibusu(Id.procesNastupZakaznikaDoMinibusu, mySim(), this);
		new ProcesVystupZakaznikaZMinibusu(Id.procesVystupZakaznikaZMinibusu, mySim(), this);
		new ProcesPrechodMedziTerminalmi(Id.procesPrechodMedziTerminalmi, mySim(), this);
		addOwnMessage(Mc.koniecNastupu);
		addOwnMessage(Mc.nastupZakaznikovZObsluhy);
		addOwnMessage(Mc.vystupZakaznikaTerm3);
		addOwnMessage(Mc.vystupZakaznikaDoObsluhy);
		addOwnMessage(Mc.nastupZakaznikovTerm2);
		addOwnMessage(Mc.nastupZakaznikovTerm1);
		addOwnMessage(Mc.koniecVystupu);
		addOwnMessage(Mc.initPrichodMinibusov);
		addOwnMessage(Mc.koniecPrechodu);
	}
	//meta! tag="end"
}