package simulation;

import OSPABA.*;

public class MyMessage extends MessageForm {

    private double zaciatokCakania;
    private double celkoveCakanie;

    public MyMessage(Simulation sim) {
        super(sim);
        zaciatokCakania = 0.0;
        celkoveCakanie = 0.0;
    }

    public MyMessage(MyMessage original) {
        super(original);
        zaciatokCakania = ((MyMessage) original).zaciatokCakania;
        celkoveCakanie = ((MyMessage) original).celkoveCakanie;
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

    public double getZaciatokCakania() {
        return zaciatokCakania;
    }

    public void setZaciatokCakania(double zaciatokCakania) {
        this.zaciatokCakania = zaciatokCakania;
    }

    public double getCelkoveCakanie() {
        return celkoveCakanie;
    }

    public void setCelkoveCakanie(double celkoveCakanie) {
        this.celkoveCakanie = celkoveCakanie;
    }

}