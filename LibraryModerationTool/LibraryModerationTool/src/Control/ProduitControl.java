package Control;

import DAO.LibraryDAO;
import Entities.Book;
import Entities.DVD;
import Entities.Entity;
import IO.IO;

import java.util.ArrayList;

public class ProduitControl {

    public static void produitMenu() {
        LibraryDAO.Update();
        IO.wall();
        IO.important("Espace Produit");
        IO.wall();
        IO.println("1) Gestion");
        IO.println("2) Search + List");
        IO.println("3) BACK ");
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
        mainMenu.Menu();
    }

    public static void GestionMenu() {
        IO.wall();
        IO.important("Gestion Produits");
        IO.wall();
        IO.println("1) Ajout");
        IO.println("2) Modifié");
        IO.println("3) Remove");
        IO.println("4) BACK");
        IO.wall();
        int choice = IO.getInt("Choice", 1, 4);
        switch (choice) {
            case 1:
                ProduitAdd.add();
                break;
            case 2:
                ProduitModify.modify();
                break;
            case 3:
                remove();
                break;
            case 4:
                produitMenu();
                return;
        }
        produitMenu();
    }

    public static void SearchMenu() {
        IO.wall();
        IO.important("Search + List");
        IO.wall();
        IO.println("1) Search par mot clé (de titre!)");
        IO.println("2) Search par BarCode");
        IO.println("3) List tous produits");
        IO.println("4) List Books");
        IO.println("5) List DVDs");
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
                SearchParBarCode(
                        IO.getInt("barcode:", 0, 1000000)
                );
                break;
            case 3:
                IO.println(LibraryDAO.getAll());
                break;
            case 4:

                IO.println(LibraryDAO.GetListOfEntity(new Book()));
                break;
            case 5:
                IO.println(LibraryDAO.GetListOfEntity(new DVD()));
                break;
            case 6:
                produitMenu();

        }
        produitMenu();
    }

    //  ===================
    public static void SearchParMotCle(String str) {

        ArrayList<Entity> Es = LibraryDAO.searchByTitle(str);

        if (Es.size() != 0) {
            IO.println(Es);
        } else {
            IO.warning("Pas de match.");
        }
    }

    public static void SearchParBarCode(int barcode) {
        Entity E = LibraryDAO.find(barcode);
        if (E != null) {
            IO.println(E);
        } else {
            IO.warning("Ce BarCode n'existe pas");
        }
    }

    public static void remove() {
        int barcode = IO.getInt("BarCode", 0, 1000000);
        Entity E = LibraryDAO.find(barcode);
        if (E != null) {
            LibraryDAO.remove(E);
            IO.sucess("Removed Successfully");
        } else {
            IO.warning("Ce BarCode n'existe pas");
        }
    }


}
