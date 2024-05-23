package Control;

import DAO.ClientDAO;
import DAO.MoneyDAO;
import Entities.Client;
import Entities.ClientData;
import Entities.RegisterData;
import IO.IO;

import java.time.LocalDate;

public class ClientAdd {

    public static void add() {
        IO.important("Ajout de nouveau CLient");
        IO.thinWall();

        int cin = IO.getInt("CIN", 10000000, 99999999);
        Client E = ClientDAO.find(cin);
        if (E != null) {
            IO.warning("Ce Client DEJA EXIS!");
        } else {
            addNewClient(cin);
        }
        ClientDAO.Update();
    }

    public static void addNewClient(int cin) {
        int phoneNumber = IO.getInt("Phone number", 10000000, 99999999);
        String name = IO.getString("Name et prenom", 1, 50);
        boolean premium = IO.getBool("Premium");
        ClientDAO.add(
                new Client(
                        cin,
                        new ClientData(name, phoneNumber),
                        new RegisterData(premium, LocalDate.now())
                )
        );
        if(premium) MoneyDAO.add(10);
        else MoneyDAO.add(5);
        IO.sucess("[[ CLIENT ADDED SUCCESSFULLY ]]");
    }


}
