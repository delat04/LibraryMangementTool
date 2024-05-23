package Entities;

import IO.IO;

import java.util.ArrayList;

public class Client {
    public int cin;
    public ClientData clientdata;
    public ArrayList<Integer> imprunts_ID;
    public RegisterData registerdata;

    public Client(int cin, ClientData bk, RegisterData registerdata) {
        this.imprunts_ID = new ArrayList<Integer>();
        this.cin = cin;
        this.clientdata = bk;
        this.registerdata = registerdata;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String toString() {
        return
                "CIN:" + IO.yellow(this.cin) + "\n"
                        + " Details:" + this.clientdata + "\n"
                        + " Abonnement:" + this.registerdata + "\n"
                        + " imprunts Ids: " + this.imprunts_ID + "\n"
                ;
    }

    public int getCin() {
        return this.cin;
    }
}
