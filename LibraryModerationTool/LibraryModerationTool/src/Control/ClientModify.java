package Control;

import DAO.ClientDAO;
import Entities.Client;
import IO.IO;

public class ClientModify {

    public static void modify() {
        IO.thinWall();
        int cin = IO.getInt("Cin", 0, 10000000);
        Client E = ClientDAO.find(cin);
        if (E != null) {
            ModClient(E);
        } else {
            IO.println("Ce client n'existe pas.");
        }
        ClientDAO.Update();
    }

    public static void ModClient(Client E) {
        IO.wall();
        IO.println("1) name");
        IO.println("2) phone Number");

        int choice = IO.getInt("Choice", 1, 2);
        switch (choice) {
            case 1:
                E.clientdata.name = IO.getString("name", 1, 500);
                break;

            case 2:
                E.clientdata.phoneNumber = IO.getInt("phone number", 10000000, 99999999);
                break;

        }

        IO.println("[[ CLIENT EDITED SUCCESSFULLY ]]");
    }


}
