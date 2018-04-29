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
public class Minibus {

    private int cisloMinibusu;
    private int typMinibusu;
    private int pocetMiest;
    private double cenaZaKm;

    public Minibus(int cisloMinibusu, int typMinibusu) {
        this.cisloMinibusu = cisloMinibusu;
        this.typMinibusu = typMinibusu;
        if (typMinibusu == 0) {
            pocetMiest = 12;
            cenaZaKm = 0.28;
        } else if (typMinibusu == 1) {
            pocetMiest = 18;
            cenaZaKm = 0.43;
        } else if (typMinibusu == 2) {
            pocetMiest = 30;
            cenaZaKm = 0.54;
        }
    }

    public int getCisloMinibusu() {
        return cisloMinibusu;
    }

    public void setCisloMinibusu(int cisloMinibusu) {
        this.cisloMinibusu = cisloMinibusu;
    }

    public int getTypMinibusu() {
        return typMinibusu;
    }

    public void setTypMinibusu(int typMinibusu) {
        this.typMinibusu = typMinibusu;
    }

    public int getPocetMiest() {
        return pocetMiest;
    }

    public void setPocetMiest(int pocetMiest) {
        this.pocetMiest = pocetMiest;
    }

    public double getCenaZaKm() {
        return cenaZaKm;
    }

    public void setCenaZaKm(double cenaZaKm) {
        this.cenaZaKm = cenaZaKm;
    }

}
