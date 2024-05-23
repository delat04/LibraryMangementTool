package Control;

import DAO.ClientDAO;
import DAO.MoneyDAO;
import Entities.Client;
import IO.IO;

import java.time.LocalDate;

public class ClientRegister {

    public static void register() {
        IO.important("renouvelle l'abonnement d'un Clien!");
        IO.thinWall();
        int cin = IO.getInt("CIN", 10000000, 99999999);
        Client E = ClientDAO.find(cin);
        if (E != null) {
            abonnement(E);
        } else {
            IO.warning("Ce CIN n'est pas abonné. Abonne un nouveau client avec (Ajout dans espace Gestion!)");
        }
        ClientDAO.Update();
    }

    public static void abonnement(Client E) {

        LocalDate date_renouvelle =
                E.registerdata.lastPaid.plusDays(30);
        if (date_renouvelle.isAfter(LocalDate.now())) {
            IO.warning("treé tôt pour renouvelle.");
            IO.warning("renouvellement seulement apré la fin de abonnement courant on [" + date_renouvelle + "]");
        } else {
            E.registerdata.lastPaid = LocalDate.now();
            MoneyDAO.add(5);
            IO.sucess("[[ Abonneent renouvellé successfully... renouvellement prochaine on " + LocalDate.now().plusDays(30) + "  ]]");
        }
    }

    public static boolean late(Client E) {
        LocalDate date_renouvelle =
                E.registerdata.lastPaid.plusDays(30);
        return date_renouvelle.isBefore(LocalDate.now());

    }


}
