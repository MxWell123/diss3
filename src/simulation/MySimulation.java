package simulation;

import OSPABA.*;
import agents.*;
import diss3.Obs;
import java.util.ArrayList;
import java.util.List;

public class MySimulation extends Simulation {

    private int pocetMinibusov;
    private int pocetPracovnikov;
    private int typMinibusu;

    public MySimulation() {
        init();
    }

    @Override
    public void prepareSimulation() {
        super.prepareSimulation();

        // Create global statistcis
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        agentObsluhy().setPocetPracovnikov(pocetPracovnikov);
        agentMinibus().setTypMinibusu(typMinibusu);
        agentModelu().spustiSimulaciu(pocetMinibusov, typMinibusu);

        // Reset entities, queues, local statistics, etc...
    }

    public void setPocetMinibusov(int pocetMinibusov) {
        this.pocetMinibusov = pocetMinibusov;
    }

    public void setPocetPracovnikov(int pocetPracovnikov) {
        this.pocetPracovnikov = pocetPracovnikov;
    }

    public void setTypMinibusu(int typMinibusu) {
        this.typMinibusu = typMinibusu;
    }

    @Override
    public void replicationFinished() {
        // Collect local statistics into global, update UI, etc...
        super.replicationFinished();
    }

    @Override
    public void simulationFinished() {
        // Dysplay simulation results
        super.simulationFinished();
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        setAgentModelu(new AgentModelu(Id.agentModelu, this, null));
        setAgentOkolia(new AgentOkolia(Id.agentOkolia, this, agentModelu()));
        setAgentSpolocnosti(new AgentSpolocnosti(Id.agentSpolocnosti, this, agentModelu()));
        setAgentObsluhy(new AgentObsluhy(Id.agentObsluhy, this, agentSpolocnosti()));
        setAgentMinibus(new AgentMinibus(Id.agentMinibus, this, agentSpolocnosti()));
        setAgentPrichodov(new AgentPrichodov(Id.agentPrichodov, this, agentSpolocnosti()));
        setAgentOdchodu(new AgentOdchodu(Id.agentOdchodu, this, agentSpolocnosti()));
    }

    private AgentModelu _agentModelu;

    public AgentModelu agentModelu() {
        return _agentModelu;
    }

    public void setAgentModelu(AgentModelu agentModelu) {
        _agentModelu = agentModelu;
    }

    private AgentOkolia _agentOkolia;

    public AgentOkolia agentOkolia() {
        return _agentOkolia;
    }

    public void setAgentOkolia(AgentOkolia agentOkolia) {
        _agentOkolia = agentOkolia;
    }

    private AgentSpolocnosti _agentSpolocnosti;

    public AgentSpolocnosti agentSpolocnosti() {
        return _agentSpolocnosti;
    }

    public void setAgentSpolocnosti(AgentSpolocnosti agentSpolocnosti) {
        _agentSpolocnosti = agentSpolocnosti;
    }

    private AgentObsluhy _agentObsluhy;

    public AgentObsluhy agentObsluhy() {
        return _agentObsluhy;
    }

    public void setAgentObsluhy(AgentObsluhy agentObsluhy) {
        _agentObsluhy = agentObsluhy;
    }

    private AgentMinibus _agentMinibus;

    public AgentMinibus agentMinibus() {
        return _agentMinibus;
    }

    public void setAgentMinibus(AgentMinibus agentMinibus) {
        _agentMinibus = agentMinibus;
    }

    private AgentPrichodov _agentPrichodov;

    public AgentPrichodov agentPrichodov() {
        return _agentPrichodov;
    }

    public void setAgentPrichodov(AgentPrichodov agentPrichodov) {
        _agentPrichodov = agentPrichodov;
    }

    private AgentOdchodu _agentOdchodu;

    public AgentOdchodu agentOdchodu() {
        return _agentOdchodu;
    }

    public void setAgentOdchodu(AgentOdchodu agentOdchodu) {
        _agentOdchodu = agentOdchodu;
    }
    //meta! tag="end"

    public Minibus[] getMinibusy() {
        return agentModelu().getMinibusy();
    }
}
