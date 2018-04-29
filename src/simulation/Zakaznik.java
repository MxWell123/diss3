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
    private boolean typ; //false = z term1/term2 ...... true = vracia auto

    public Zakaznik(boolean typ) {
        this.zaciatokCakania = 0;
        this.celkoveCakanie = 0;
        this.pocetSpolucestujucich = 1 + pocetSpolucestujucich;
    }

    public int getPocetSpolucestujucich() {
        return pocetSpolucestujucich;
    }

    public void setPocetSpolucestujucich(int pocetSpolucestujucich) {
        this.pocetSpolucestujucich = pocetSpolucestujucich;
    }

    public boolean isTyp() {
        return typ;
    }

    public void setTyp(boolean typ) {
        this.typ = typ;
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
