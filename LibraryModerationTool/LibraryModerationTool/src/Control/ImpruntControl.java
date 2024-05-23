package Control;

import DAO.ImpruntDAO;
import Entities.Imprunt;
import IO.IO;

import java.util.ArrayList;

public class ImpruntControl {

    /// MENUS =================================
    public static void impruntMenu() {
        ImpruntDAO.Update();
        IO.wall();
        IO.important("Espace Imprunt");
        IO.wall();
        IO.println("1) Gestion");
        IO.println("2) Search + List");
        IO.println("3) BACK   ");
        IO.wall();
        int choice = IO.getInt("Choice", 1, 3);
        switch (choice) {
            case 1:
                GestionMenu();
                break;

            case 2:
                SearchMenu();
                break;
            case 3:
                mainMenu.Menu();
                break;
        }
        impruntMenu();
    }

    public static void GestionMenu() {
        IO.wall();
        IO.important("Gestion Imprunt");
        IO.wall();
        IO.println("1) Ajout");
        //IO.println("2) Modifié");
        IO.println("2) Remove");
        IO.println("3) BACK");
        IO.wall();
        int choice = IO.getInt("Choice", 1, 3);
        switch (choice) {
            case 1:
                ImpruntAdd.add();
                break;
            /*case 2:
                //ImpruntModify.modify();
                break;*/
            case 2:
                remove();
                break;
            case 3:
                impruntMenu();
                break;
        }
    }


    public static void SearchMenu() {
        IO.wall();
        IO.important("Search + List");
        IO.wall();
        IO.println("1) Search par id");
        IO.println("2) Search par CIN du Client");
        IO.println("3) Search par barcode du produit ");
        IO.println("4) List tous imprunts");
        IO.println("5) BACK");
        IO.wall();
        int choice = IO.getInt("Choice", 1, 5);
        switch (choice) {
            case 1:
                SearchParId(
                        IO.getInt("imprunt ID", 0, 1000)
                );
                break;
            case 2:
                SearchParCin(
                        IO.getInt("Cin", 10000000, 99999999)
                );
                break;
            case 3:
                SearchParBarCode(IO.getInt("Barcode", 0, 1000000));
                break;
            case 4:

                IO.println(ImpruntDAO.getAll());
                break;
            case 5:
                impruntMenu();

        }
        ImpruntDAO.Update();
        impruntMenu();
    }

    //  ===================
    public static void SearchParId(int id) {

        Imprunt Es = ImpruntDAO.findParId(id);

        if (Es != null) {
            IO.println(Es);
        } else {
            IO.warning("Pas de match.");
        }
    }

    public static void SearchParCin(int cin) {
        ArrayList<Imprunt> E = ImpruntDAO.findParCin(cin);
        if (E != null) {
            IO.println(E);
        } else {
            IO.warning("Pas de match");
        }
    }

    public static void SearchParBarCode(int barcode) {
        ArrayList<Imprunt> E = ImpruntDAO.findParBarCode(barcode);
        if (E != null) {
            IO.println(E);
        } else {
            IO.warning("Pas de match");
        }
    }


    public static void remove() {
        int id = IO.getInt("id", 0, 1000);
        Imprunt E = ImpruntDAO.findParId(id);
        if (E != null) {
            ImpruntDAO.remove(E);
            IO.sucess("Removed Successfully");
        } else {
            IO.warning("imprunt avec ce ID n'existe pas");
        }
    }


}
