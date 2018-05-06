/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import java.util.Random;

/**
 *
 * @author davidecek
 */
public class Zakaznik {

    private double zaciatokCakania;
    private double celkoveCakanie;
    private int pocetSpolucestujucich;
    private boolean typ; //false = z term1/term2 ...... true = vracia auto
    private Random nasada;
    private Random gen;

    public Zakaznik(boolean typ) {
        this.zaciatokCakania = 0;
        this.celkoveCakanie = 0;
        this.pocetSpolucestujucich = 1 + vygenerujPocetSpolucestujucich();
        this.typ = typ;
    }

    public int getPocetSpolucestujucich() {
        return pocetSpolucestujucich;
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

    private int vygenerujPocetSpolucestujucich() {
        nasada = new Random();
        gen = new Random(nasada.nextLong());
        double cislo = gen.nextDouble();
        if (cislo < 0.6) {
            return 0;
        } else if (cislo < 0.80) {
            return 1;
        } else if (cislo < 0.95) {
            return 2;
        } else {
            return 3;
        }
    }

}
