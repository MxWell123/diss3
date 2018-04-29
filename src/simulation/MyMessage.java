package simulation;

import OSPABA.*;

public class MyMessage extends MessageForm {

    private Zakaznik zakaznik;
    private Minibus minibus;

    public MyMessage(Simulation sim) {
        super(sim);
    }

    public MyMessage(MyMessage original) {
        super(original);
    }

    public MyMessage(Zakaznik zakaznik, Simulation sim) {
        super(sim);
        this.zakaznik = zakaznik;
        this.minibus = null;
    }

    public MyMessage(Minibus minibus, Simulation sim) {
        super(sim);
        this.zakaznik = null;
        this.minibus = minibus;
    }

    @Override
    public MessageForm createCopy() {
        return new MyMessage(this);
    }

    @Override
    protected void copy(MessageForm message) {
        super.copy(message);
        MyMessage original = (MyMessage) message;
        // Copy attributes
    }

    public Zakaznik getZakaznik() {
        return zakaznik;
    }

    public void setZakaznik(Zakaznik zakaznik) {
        this.zakaznik = zakaznik;
    }

    public Minibus getMinibus() {
        return minibus;
    }

    public void setMinibus(Minibus minibus) {
        this.minibus = minibus;
    }

}
