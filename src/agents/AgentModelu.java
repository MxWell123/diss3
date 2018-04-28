package agents;

import OSPABA.*;
import simulation.*;
import managers.*;
import continualAssistants.*;
import java.util.Random;

//meta! id="1"
public class AgentModelu extends Agent {


    public AgentModelu(int id, Simulation mySim, Agent parent) {
        super(id, mySim, parent);
        init();
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerModelu(Id.managerModelu, mySim(), this);
		addOwnMessage(Mc.prichodZakaznikaTerm2);
		addOwnMessage(Mc.prichodZakaznikaTerm1);
		addOwnMessage(Mc.prichodZakaznikaNaVratenieAuta);
	}
	//meta! tag="end"
}