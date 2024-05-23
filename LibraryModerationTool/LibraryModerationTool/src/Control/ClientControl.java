package Control;

import DAO.ClientDAO;
import DAO.LibraryDAO;
import Entities.Client;
import IO.IO;

import java.util.ArrayList;

public class ClientControl {

    /// MENUS =================================
    public static void clientMenu() {
        IO.wall();
        IO.important("Espace Client");
        IO.wall();
        IO.println("1) Gestion");
        IO.println("2) Abonné/Renouvelle ");
        IO.println("3) Search + List");
        IO.println("4) BACK   ");
        IO.wall();
        int choice = IO.getInt("Choice", 1, 4);
        switch (choice) {
            case 1:
                GestionMenu();
                break;
            case 2:
                ClientRegister.register();
                break;

            case 3:
                SearchMenu();
                break;
            case 4:
                mainMenu.Menu();
                break;
        }
        LibraryDAO.Update();
        clientMenu();
    }

    public static void GestionMenu() {
        IO.wall();
        IO.important("Gestion Client");
        IO.wall();
        IO.println("1) Ajout (5dt / 10dt premium");
        IO.println("2) Modifié");
        IO.println("3) Remove");
        IO.println("4) BACK");
        IO.wall();
        int choice = IO.getInt("Choice", 1, 4);
        switch (choice) {
            case 1:
                ClientAdd.add();
                break;
            case 2:
                ClientModify.modify();
                break;
            case 3:
                remove();
                break;
            case 4:
                clientMenu();
                break;
        }
    }


    public static void SearchMenu() {
        IO.wall();
        IO.important("Search + List");
        IO.wall();
        IO.println("1) Search par mot clé (de nom complet!)");
        IO.println("2) Search par CIN");
        IO.println("3) List tous clients");
        IO.println("4) List Clients Premium");
        IO.println("5) List Clients non Premium");
        IO.println("6) BACK");
        IO.wall();
        int choice = IO.getInt("Choice", 1, 5);
        switch (choice) {
            case 1:
                SearchParMotCle(
                        IO.getString("mot cle", 1, 100)
                );
                break;
            case 2:
                SearchParCin(
                        IO.getInt("Cin:", 10000000, 99999999)
                );
                break;
            case 3:
                IO.println(ClientDAO.getAll());
                break;
            case 4:

                IO.println(ClientDAO.GetListOfPremium());
                break;
            case 5:
                IO.println(ClientDAO.GetListOfNotPremium());
                break;
            case 6:
                clientMenu();

        }
        clientMenu();
    }

    //  ===================
    public static void SearchParMotCle(String str) {

        ArrayList<Client> Es = ClientDAO.searchByName(str);

        if (Es.size() != 0) {
            IO.println(Es);
        } else {
            IO.println("Pas de match.");
        }
    }

    public static void SearchParCin(int cin) {
        Client E = ClientDAO.find(cin);
        if (E != null) {
            IO.println(E);
        } else {
            IO.println("Ce Cin n'existe pas");
        }
    }

    public static void remove() {
        int cin = IO.getInt("Cin", 10000000, 99999999);
        Client E = ClientDAO.find(cin);
        if (E != null) {
            ClientDAO.remove(E);
            IO.sucess("Removed Successfully");
        } else {
            IO.warning("Ce CIn n'existe pas");
        }
    }


}
