import Control.mainMenu;
import DAO.*;
import IO.*;

public class Main {

    public static void main(String[] str) {
        BLDAO.load("BlackList.json");
        LibraryDAO.load("lib.json");
        ClientDAO.load("Entities.json");
        ImpruntDAO.load("Imprunt.json");
        MoneyDAO.load("Money.json");

        mainMenu.Menu();

        LibraryDAO.Update();
        ClientDAO.Update();
        ImpruntDAO.Update();
        MoneyDAO.Update();
    }


}