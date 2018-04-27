package simulation;

import OSPABA.*;

public class MyMessage extends MessageForm {

    private Zakaznik zakaznik;

    public MyMessage(Simulation sim) {
        super(sim);
    }

    public MyMessage(MyMessage original) {
        super(original);
    }

    public MyMessage(Zakaznik zakaznik, MyMessage original) {
        super(original);
        this.zakaznik = original.getZakaznik();
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
}
