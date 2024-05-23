package Entities;

import IO.IO;

import java.time.LocalDate;

public class RegisterData {
    public boolean premium;
    public LocalDate lastPaid;

    public RegisterData(boolean premium, LocalDate lastPaid) {
        this.premium = premium;
        this.lastPaid = lastPaid;
    }

    public String toString() {
        return "premium:" + IO.red(premium) + "\n"
                + " temp de dernier payement:" + IO.yellow(lastPaid);
    }
}

