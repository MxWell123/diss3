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
public class Zamestnanec {

    private int cisloZamestnanca;
    private String vykonPrace;
    private boolean vytazeny;
    private double casZaciatkuVykonu;
    private double trvanieVykonu;

    public Zamestnanec(int cisloZamestnanca) {
        this.cisloZamestnanca = cisloZamestnanca;
        this.vykonPrace = "čaka";
        this.vytazeny = false;
        this.casZaciatkuVykonu = 0.0;
        this.trvanieVykonu = 0.0;
    }

    public double getCasZaciatkuVykonu() {
        return casZaciatkuVykonu;
    }

    public void setCasZaciatkuVykonu(double casZaciatkuVykonu) {
        this.casZaciatkuVykonu = casZaciatkuVykonu;
    }

    public double getTrvanieVykonu() {
        return trvanieVykonu;
    }

    public void setTrvanieVykonu(double trvanieVykonu) {
        this.trvanieVykonu = trvanieVykonu;
    }

    public boolean isVytazeny() {
        return vytazeny;
    }

    public void setVytazeny(boolean vytazeny) {
        this.vytazeny = vytazeny;
    }

    public int getCisloZamestnanca() {
        return cisloZamestnanca;
    }

    public void setCisloZamestnanca(int cisloZamestnanca) {
        this.cisloZamestnanca = cisloZamestnanca;
    }

    public String getVykonPrace() {
        return vykonPrace;
    }

    public void setVykonPrace(String vykonPrace) {
        this.vykonPrace = vykonPrace;
    }

}
