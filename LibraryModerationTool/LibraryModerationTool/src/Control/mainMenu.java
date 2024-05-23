package Control;

import DAO.ClientDAO;
import DAO.ImpruntDAO;
import DAO.LibraryDAO;
import Entities.Client;
import Entities.Imprunt;
import IO.IO;

import java.util.ArrayList;

public class mainMenu {
    public static void Menu() {
        IO.wall();
        IO.important("Main Menu");
        IO.wall();
        IO.println("1) Espace Produits");
        IO.println("2) Espace Clients");
        IO.println("3) Espace Imprunts ");
        IO.println("4) BlackList ");
        IO.println("5) liste  ceux qui devraient revenir aujourd'hui");
        IO.println("6) Clients qui n'on pas retournue [ blacklist Si Nécessaire]");
        IO.println("7) QUIT ");
        IO.wall();
        int choice = IO.getInt("Choice", 1, 7);
        switch (choice) {
            case 1:
                ProduitControl.produitMenu();
                break;
            case 2:
                ClientControl.clientMenu();
                break;
            case 3:
                ImpruntControl.impruntMenu();
                break;
            case 4:
                BlacklistControl.blacklistMenu();
                break;
            case 5:
                listRetard();
                break;
            case 6:
                listBad();
                break;

            case 7:
                IO.exitText();
                System.exit(0);
                return;
        }
    }


    private static void listRetard() {
        ArrayList<Imprunt> E = ImpruntDAO.getToday();
        for(Imprunt im :E){
            IO.println(">> Client :\n" + ClientDAO.find( im.getCin() ) );
            IO.println(">> Produit:\n" + LibraryDAO.find( im.getBarCode()) );
            IO.thinWall();
        }
    }
    private static void listBad() {
        ArrayList<Imprunt> E = ImpruntDAO.getBad();
        for(Imprunt im :E){
            IO.println(">> Client :\n" + ClientDAO.find( im.getCin() ) );
            IO.println(">> Produit:\n" + LibraryDAO.find( im.getBarCode()) );
            IO.thinWall();
        }
    }


}
