package simulation;

import OSPABA.*;
import OSPStat.Stat;
import agents.*;
import diss3.Obs;
import java.util.ArrayList;
import java.util.List;

public class MySimulation extends Simulation {

    private int pocetMinibusov;
    private int pocetPracovnikov;
    private int typMinibusu;
    private Stat casCakaniaPoAutoStat;
    private Stat casCakaniaVratAutoStat;
    private Stat priemernyRadTerm1;
    private Stat priemernyRadTerm2;
    private Stat priemernyRadPredObsluhou;
    private Stat priemernyCasVRadeTerm1;
    private Stat priemernyCasVRadeTerm2;
    private Stat priemernyCasVRadePredObsluhou;
    private Stat priemernaVytazenostPracovnikov;
    private Stat priemernyPocetLudiNaMinibus;
    private Stat priemernyCasCakaniaNaMinibus;
    private Stat priemernyPocetPrejdenychKilometrov;
    private Stat priemernaCena;
    private Minibus minibus;

    public MySimulation() {
        init();
    }

    @Override
    public void prepareSimulation() {
        super.prepareSimulation();
        casCakaniaPoAutoStat = new Stat();
        casCakaniaVratAutoStat = new Stat();
        priemernyRadTerm1 = new Stat();
        priemernyRadTerm2 = new Stat();
        priemernyRadPredObsluhou = new Stat();
        priemernyCasVRadeTerm1 = new Stat();
        priemernyCasVRadeTerm2 = new Stat();
        priemernyCasVRadePredObsluhou = new Stat();
        priemernaVytazenostPracovnikov = new Stat();
        priemernyPocetLudiNaMinibus = new Stat();
        priemernyCasCakaniaNaMinibus = new Stat();
        priemernyPocetPrejdenychKilometrov = new Stat();
        priemernaCena = new Stat();
        minibus = new Minibus(0, agentMinibus().getTypMinibusu());
        // Create global statistcis
    }

    @Override
    public void prepareReplication() {
        agentObsluhy().setPocetPracovnikov(pocetPracovnikov);
        agentMinibus().setTypMinibusu(typMinibusu);
        agentModelu().setPocetMinibusov(pocetMinibusov);
        super.prepareReplication();
        agentModelu().spustiSimulaciu(pocetMinibusov, typMinibusu);

        // Reset entities, queues, local statistics, etc...
    }

    public Stat getPriemernyPocetLudiNaMinibus() {
        return priemernyPocetLudiNaMinibus;
    }

    public int getTypMinibusu() {
        return typMinibusu;
    }

    public Stat getPriemernyCasCakaniaNaMinibus() {
        return priemernyCasCakaniaNaMinibus;
    }

    public Stat getPriemernyRadTerm1() {
        return priemernyRadTerm1;
    }

    public Stat getPriemernyRadPredObsluhou() {
        return priemernyRadPredObsluhou;
    }

    public Stat getPriemernyRadTerm2() {
        return priemernyRadTerm2;
    }

    public Zamestnanec[] getZamestnanci() {
        return agentObsluhy().getZamestnanci();
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
        if (this.currentTime() > 0.5 * 60 * 60) {
            casCakaniaPoAutoStat.addSample(agentOkolia().getCasCakaniaPoAutoStat().mean());
            casCakaniaVratAutoStat.addSample(agentOkolia().getCasCakaniaVratAutoStat().mean());
            priemernyRadTerm1.addSample(agentPrichodov().getPriemernyRadZakaznikovTerm1().mean());
            priemernyRadTerm2.addSample(agentPrichodov().getPriemernyRadZakaznikovTerm2().mean());
            priemernyRadPredObsluhou.addSample(agentObsluhy().getPriemernaVelkostRadu().mean());
            priemernyCasVRadeTerm1.addSample(agentMinibus().getPriemCasVradeTerm1().mean());
            priemernyCasVRadeTerm2.addSample(agentMinibus().getPriemCasVradeTerm2().mean());
            priemernyCasVRadePredObsluhou.addSample(agentObsluhy().getPriemernyCasCakaniaVRade().mean());
            priemernaVytazenostPracovnikov.addSample(agentObsluhy().getPriemernyPocetVytazenychPracovnikov().mean());
            priemernyPocetLudiNaMinibus.addSample(agentSpolocnosti().getPriemernyRadNaMinibus().mean());
            priemernyCasCakaniaNaMinibus.addSample(agentSpolocnosti().getPrimernyCasVRadeNaMinibus().mean());
            priemernyPocetPrejdenychKilometrov.addSample(agentMinibus().getPocetKilometrov());
            priemernaCena.addSample(agentMinibus().getPocetKilometrov() * minibus.getCenaZaKm());
        }
    }

    public Stat getPriemernyPocetPrejdenychKilometrov() {
        return priemernyPocetPrejdenychKilometrov;
    }

    public Stat getPriemernaCena() {
        return priemernaCena;
    }

    public int getPocetPracovnikov() {
        return pocetPracovnikov;
    }

    public Stat getPriemernaVytazenostPracovnikov() {
        return priemernaVytazenostPracovnikov;
    }

    public Stat getPriemernyCasVRadeTerm1() {
        return priemernyCasVRadeTerm1;
    }

    public Stat getPriemernyCasVRadeTerm2() {
        return priemernyCasVRadeTerm2;
    }

    public Stat getPriemernyCasVRadePredObsluhou() {
        return priemernyCasVRadePredObsluhou;
    }

    public Stat getCasCakaniaPoAutoStat() {
        return casCakaniaPoAutoStat;
    }

    public Stat getCasCakaniaVratAutoStat() {
        return casCakaniaVratAutoStat;
    }

    @Override
    public void simulationFinished() {
        // Dysplay simulation results
        super.simulationFinished();
        // System.out.println("Waiting time mean:  " + (casCakaniaPoAutoStat.mean() / 60));
        // System.out.println("Waiting time mean:  " + (casCakaniaVratAutoStat.mean() / 60));
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private void init() {
        setAgentModelu(new AgentModelu(Id.agentModelu, this, null));
        setAgentOkolia(new AgentOkolia(Id.agentOkolia, this, agentModelu()));
        setAgentSpolocnosti(new AgentSpolocnosti(Id.agentSpolocnosti, this, agentModelu()));
        setAgentObsluhy(new AgentObsluhy(Id.agentObsluhy, this, agentSpolocnosti()));
        setAgentMinibus(new AgentMinibus(Id.agentMinibus, this, agentSpolocnosti()));
        setAgentPrichodov(new AgentPrichodov(Id.agentPrichodov, this, agentSpolocnosti()));
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
    //meta! tag="end"

    public Minibus[] getMinibusy() {
        return agentModelu().getMinibusy();
    }

}
