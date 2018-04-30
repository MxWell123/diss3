package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;
import java.util.Random;

//meta! id="1"
public class AgentModelu extends Agent {

    private int pocetMinibusov;
    private int typMinibusu;
    private int pocetPracovnikov;
    private int pocetVypustenychMinbusov;

    private Minibus[] minibusy;

    public AgentModelu(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        pocetVypustenychMinbusov = 0;
        // Setup component for the next replication
    }

    public void setPocetMinibusov(int pocetMinibusov) {
        this.pocetMinibusov = pocetMinibusov;
        minibusy = new Minibus[pocetMinibusov];
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        new ManagerModelu(Id.managerModelu, mySim(), this);
        new PlanovacMinibusov(Id.planovacMinibusov, mySim(), this);
        addOwnMessage(Mc.prichodZakaznikaTerm2);
        addOwnMessage(Mc.prichodZakaznikaTerm1);
        addOwnMessage(Mc.koniec);
        addOwnMessage(Mc.prichodZakaznikaNaVratenieAuta);
        addOwnMessage(Mc.initPrichodMinibusov);
        addOwnMessage(Mc.novyMinibus);
    }
    //meta! tag="end"

    public void spustiSimulaciu(int pocetMinibusov, int typMinibusu) {
        this.pocetMinibusov = pocetMinibusov;
        this.typMinibusu = typMinibusu;
        MyMessage message = new MyMessage(mySim());
        message.setCode(Mc.initPrichodyZakaznikov);
        message.setAddressee(Id.agentOkolia);
        manager().notice(message);
    }

    public void vlozDoPolaMinibus(Minibus minibus) {
        minibusy[pocetVypustenychMinbusov] = minibus;
        pocetVypustenychMinbusov++;
    }

    public Minibus[] getMinibusy() {
        return minibusy;
    }

    public int getPocetMinibusov() {
        return pocetMinibusov;
    }

    public int getPocetPracovnikov() {
        return pocetPracovnikov;
    }

    public int getTypMinibusu() {
        return typMinibusu;
    }

}
