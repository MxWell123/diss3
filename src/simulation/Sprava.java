/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import OSPABA.MessageForm;
import OSPABA.Simulation;

/**
 *
 * @author davidecek
 */
public class Sprava extends MessageForm {

    private double zaciatokCakania;
    private double celkoveCakanie;

    public Sprava(Simulation mySim) {
        super(mySim);
        zaciatokCakania = 0.0;
        celkoveCakanie = 0.0;

    }

    private Sprava(Sprava original) {
        super(original);
        zaciatokCakania = ((Sprava) original).zaciatokCakania;
        celkoveCakanie = ((Sprava) original).celkoveCakanie;
    }

    @Override
    public Sprava createCopy() {
        return new Sprava(this);
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
