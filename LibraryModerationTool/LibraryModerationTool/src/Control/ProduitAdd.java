package Control;

import DAO.LibraryDAO;
import Entities.*;
import IO.IO;

public class ProduitAdd {

    public static void add() {
        IO.println("Ajout de nouveau Produit ou Examplaire");
        IO.thinWall();

        int barcode = IO.getInt("BarCode", 0, 1000000);
        Entity E = LibraryDAO.find(barcode);
        if (E != null) {
            addExiting(E);
        } else {
            addNew(barcode);
        }
        LibraryDAO.Update();
    }

    public static void addNewBook(int barcode) {
        int nb = IO.getInt("nombre d'examplaires", 1, 500);
        String title = IO.getString("Title", 1, 50);
        int pages = IO.getInt("nombre de pages", 1, 2000);

        LibraryDAO.add(new Book(barcode, nb, title, new BookData(pages)));
        IO.sucess("[[ BOOK ADDED SUCCESSFULLY ]]");
    }

    public static void addNewDVD(int barcode) {
        int nb = IO.getInt("nombre d'examplaires", 1, 500);
        String title = IO.getString("Title", 1, 50);
        int heur = IO.getInt("Longeur on heures", 0, 24);
        int minute = IO.getInt("Longeur on minutes", 0, 59);

        LibraryDAO.add(new DVD(barcode, nb, title, new DvdData(heur, minute)));
        IO.sucess("[[ DVD ADDED SUCCESSFULLY ]]");
    }

    public static void addNew(int barcode) {

        IO.wall();
        IO.println("1) Book");
        IO.println("2) DVD");
        IO.println("3) BACK");
        int choice = IO.getInt("Choice", 1, 3);
        switch (choice) {
            case 1:
                addNewBook(barcode);
                break;
            case 2:
                addNewDVD(barcode);
                break;

            case 3:
                ProduitControl.GestionMenu();
                break;

        }


    }

    public static void addExiting(Entity E) {
        IO.warning("il exist deja un produit avec ce BarCode");
        IO.thinWall();
        IO.important(E);
        IO.wall();
        IO.println("1) incrumente le nombre d'examplaires de [" + E.getBarCode() + "]");
        IO.println("2) change de barCode?");
        IO.println("3) BACK");
        int choice = IO.getInt("Choice", 1, 3);
        switch (choice) {
            case 1:
                E.incrument();
                break;
            case 2:
                add();
                break;

            case 3:
                ProduitControl.GestionMenu();
                break;

        }
    }

}
