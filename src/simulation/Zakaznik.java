/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

/**
 *
 * @author davidecek
 */
public class Zakaznik {

    private double zaciatokCakania;
    private double celkoveCakanie;
    private int pocetSpolucestujucich;

    public Zakaznik(int pocetSpolucestujucich) {
        this.zaciatokCakania = 0;
        this.celkoveCakanie = 0;
        this.pocetSpolucestujucich = pocetSpolucestujucich;
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
