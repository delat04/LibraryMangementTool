package Control;

import DAO.BLDAO;
import DAO.ClientDAO;
import DAO.MoneyDAO;
import Entities.Client;
import IO.IO;

public class BlacklistControl {

    public static void blacklistMenu() {

        IO.wall();
        IO.important("Espace BlackList");
        IO.wall();
        IO.println("1) Ajout");
        IO.println("2)  Remove ( 5 DT )");
        IO.println("3) BACK   ");
        IO.wall();
        int choice = IO.getInt("Choice", 1, 3);
        switch (choice) {
            case 1 -> add();
            case 2 -> remove();
            case 4 -> mainMenu.Menu();
        }
        BLDAO.Update();
        mainMenu.Menu();
    }


    public static void remove() {
        IO.important("remove de nouveau CLient au blacklist");
        IO.thinWall();

        int cin = IO.getInt("CIN", 10000000, 99999999);
        Integer E = BLDAO.find(cin);
        if(E==null){
            IO.warning("Ce Client n'est pas dans la blackList");
        }
        else {
            IO.sucess(" [[SUCCESS]] ");
            MoneyDAO.add(5);
            BLDAO.remove(cin);
        }
        ClientDAO.Update();
    }



    public static void add() {
        IO.important("Ajout de nouveau CLient au blacklist");
        IO.thinWall();

        int cin = IO.getInt("CIN", 10000000, 99999999);
        Client E = ClientDAO.find(cin);
        if (E == null) {
            IO.warning("Ce Client n'existe pas!");
        }
        else if(BLDAO.find(cin)!=null){
            IO.warning("Ce Client deja dans la blackList");
        }
        else {
            BLDAO.add(cin);
        }
        ClientDAO.Update();
    }

    }

