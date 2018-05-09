/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import OSPDataStruct.SimQueue;

/**
 *
 * @author davidecek
 */
public class Minibus {

    private int cisloMinibusu;
    private int typMinibusu;
    private int pocetMiest;
    private double cenaZaKm;
    private SimQueue<Zakaznik> zakazniciVMinibuse;
    private int polohaMinibusu; // 0-term1, 1-term2, 2-AirCarRental, 3-term3
    private String typPrace;
    private int pocetObsadenychMiest;
    private double casZaciatkuVykonu;
    private double trvanieVykonu;

    public Minibus(int cisloMinibusu, int typMinibusu) {
        this.cisloMinibusu = cisloMinibusu;
        this.typMinibusu = typMinibusu;
        this.casZaciatkuVykonu = 0.0;
        this.trvanieVykonu = 0.0;
        this.typPrace = "";
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
        zakazniciVMinibuse = new SimQueue<>();
        polohaMinibusu = 0;
        pocetObsadenychMiest = 0;
    }

    public double getCasZaciatkuVykonu() {
        return casZaciatkuVykonu;
    }

    public void setCasZaciatkuVykonu(double casZaciatkuVykonu, double trvanieVykonu) {
        this.casZaciatkuVykonu = casZaciatkuVykonu;
        this.trvanieVykonu = trvanieVykonu;
    }

    public double getTrvanieVykonu() {
        return trvanieVykonu;
    }

    public void pridajZakaznikaDoMinibusu(Zakaznik zak) {
        if (pocetObsadenychMiest + zak.getPocetSpolucestujucich() <= pocetMiest) {
            zakazniciVMinibuse.add(zak);
            this.setPocetObsadenychMiest(pocetObsadenychMiest + zak.getPocetSpolucestujucich());
        }
    }

    public String getTypPrace() {
        return typPrace;
    }

    public void setTypPrace(String typPrace) {
        this.typPrace = typPrace;
    }

    public int getPocetObsadenychMiest() {
        return pocetObsadenychMiest;
    }

    public void setPocetObsadenychMiest(int pocetObsadenychMiest) {
        this.pocetObsadenychMiest = pocetObsadenychMiest;
    }

    public Zakaznik vyberZakaznikaZMinibusu() {
        if (pocetObsadenychMiest > 0) {
            Zakaznik zak = zakazniciVMinibuse.poll();
            pocetObsadenychMiest -= zak.getPocetSpolucestujucich();
            return zak;
        }
        return null;
    }

    public int getPolohaMinibusu() {
        return polohaMinibusu;
    }

    public void setPolohaMinibusu(int polohaMinibusu) {
        this.polohaMinibusu = polohaMinibusu;
    }

    public boolean jeMinibusPrazdny() {
        if (pocetObsadenychMiest == 0) {
            return true;
        }
        return false;
    }

    public boolean jeMinibusPlny() {
        if (pocetMiest == pocetObsadenychMiest) {
            return true;
        }
        return false;
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
