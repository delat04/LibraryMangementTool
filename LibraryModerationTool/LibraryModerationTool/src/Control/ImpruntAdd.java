package Control;

import DAO.ClientDAO;
import DAO.ImpruntDAO;
import DAO.LibraryDAO;
import Entities.Client;
import Entities.Entity;
import Entities.Imprunt;
import IO.IO;

import java.time.LocalDate;

public class ImpruntAdd {

    public static void add() {
        IO.important("Ajout de nouveau Imprunt");
        IO.thinWall();

        int imprunt_ID = IO.getInt("Imprunt Id", 0, 1000);
        Imprunt E = ImpruntDAO.findParId(imprunt_ID);
        if (E != null) {
            IO.warning("Ce Imprunt DEJA EXIS!");
        } else {
            addNewImprunt(imprunt_ID);
        }
        ImpruntDAO.Update();
    }

    public static void addNewImprunt(int imprunt_ID) {
        int cin, barcode;
        Client E;
        Entity E2;
        do {
            IO.important("Entré une Cin d'un Client existant et abonné ! ");
            cin = IO.getInt("CIN", 10000000, 99999999);
            E = ClientDAO.find(cin);
        } while (E == null || ClientRegister.late(E));

        do {
            IO.important("Entré barcode d'un produit  existant!, avec nombre d'examplaires non 0.");
            barcode = IO.getInt("barcode", 0, 99999999);
            E2 = LibraryDAO.find(barcode);
        } while (E2 == null || E2.nb == 0);


        if (E.imprunts_ID.size() >= 3 + 2 * (E.registerdata.premium ? 1 : 0)) {
            IO.warning("maximum d'imprunt pour ce Client est " + (3 + 2 * (E.registerdata.premium ? 1 : 0)) + ":");
            return;
        }
        E2.nb--;
        boolean premium = E.registerdata.premium;
        E2.imprunts_ID.add(imprunt_ID);
        E.imprunts_ID.add(imprunt_ID);
        ImpruntDAO.add(
                new Imprunt(
                        cin,
                        barcode,
                        imprunt_ID,
                        LocalDate.now(),
                        premium
                )
        );
        IO.sucess("[[ IMPRUNT ADDED SUCCESSFULLY ]]");
    }


}
