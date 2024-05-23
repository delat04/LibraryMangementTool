package Entities;

import IO.IO;

import java.time.LocalDate;

public class Imprunt {
    public int imprunt_ID;
    public int cin;
    public int barCode;
    public LocalDate date;
    public boolean premium;

    public Imprunt(int cin, int barCode, int imprunt_ID, LocalDate date, boolean premium) {
        this.cin = cin;
        this.barCode = barCode;
        this.imprunt_ID = imprunt_ID;
        this.date = date;
        this.premium = premium;
    }

    public String toString() {
        return "imprunt id:" + IO.yellow(imprunt_ID) + "\n"
                + "cin correspondant:" + IO.red(imprunt_ID) + "\n"
                + "barCode de produit correspondant:" + IO.green(barCode) + "\n"
                + "Date d'imprunt:" + IO.yellow(date) + "\n"
                + "premium:" + premium + "\n";
    }

    public int getImprunt_ID() {
        return imprunt_ID;
    }

    public int getBarCode() {
        return barCode;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setImprunt_ID(int imprunt_ID) {
        this.imprunt_ID = imprunt_ID;
    }
}
