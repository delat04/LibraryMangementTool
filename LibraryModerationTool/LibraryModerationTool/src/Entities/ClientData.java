package Entities;

import IO.IO;

public class ClientData {
    public String name;
    public int phoneNumber;

    public ClientData(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return "name:" + IO.green(name) + "\n"
                + " phone number:" + IO.yellow(phoneNumber);
    }
}
