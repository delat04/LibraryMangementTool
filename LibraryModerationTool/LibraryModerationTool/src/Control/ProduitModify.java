package Control;

import DAO.LibraryDAO;
import Entities.Book;
import Entities.DVD;
import Entities.Entity;
import IO.IO;

public class ProduitModify {

    public static void modify() {
        IO.thinWall();
        int barcode = IO.getInt("BarCode", 0, 1000000);
        Entity E = LibraryDAO.find(barcode);
        if (E != null) {
            modify2(E);
        } else {
            IO.println("Ce produit n'existe pas.");
        }
        LibraryDAO.Update();
    }

    public static void ModNewBook(Entity E) {
        IO.wall();
        IO.println("1) nombre d'examplaire");
        IO.println("2) Title");
        IO.println("3) nombre de pages");

        int choice = IO.getInt("Choice", 1, 3);
        switch (choice) {
            case 1:
                E.setnb(IO.getInt("nombre d'examplaires", 1, 500));
                break;

            case 2:
                E.title = IO.getString("Title", 1, 50);
                break;

            case 3:
                ((Book) E).bookdata.pages = IO.getInt("nombre de pages", 1, 2000);
                break;
        }

        IO.println("[[ BOOK EDITED SUCCESSFULLY ]]");
    }


    public static void ModNewDVD(Entity E) {
        IO.wall();
        IO.println("1) nombre d'examplaire");
        IO.println("2) Title");
        IO.println("3) duré on heures");
        IO.println("4) duré on minutes");
        int choice = IO.getInt("Choice", 1, 4);
        switch (choice) {
            case 1:
                E.setnb(IO.getInt("nombre d'examplaires", 1, 500));
                break;

            case 2:
                E.title = IO.getString("Title", 1, 50);
                break;

            case 3:
                ((DVD) E).dvddata.h = IO.getInt("dure on heures", 0, 24);
                break;

            case 4:
                ((DVD) E).dvddata.m = IO.getInt("dure on minutes ", 0, 59);
                break;

        }

        IO.println("[[ DVD EDITED SUCCESSFULLY ]]");
    }

    public static void modify2(Entity E) {

        IO.println(E.type + " " + Book.class.getName());
        if (E.type.equals(Book.class.getName()))
            ModNewBook(E);
        else if (E.type.equals(DVD.class.getName()))
            ModNewDVD(E);
        else {
            IO.println("Unkown type");
        }

    }


}
