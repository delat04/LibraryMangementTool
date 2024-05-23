package DAO;

import Entities.Client;
import IO.IO;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ClientDAO {
    static protected ArrayList<Client> clients;
    static String src;

    public static void add(Client E) {
        clients.add(E);
        Update();
    }


    public static ArrayList<Client> searchByName(String str) {
        ArrayList<Client> list = new ArrayList<Client>();
        for (Client M : clients) {
            if (M.clientdata.name.contains(str)) {
                list.add(M);
            }
        }
        return list;
    }

    public static ArrayList<Client> GetListOfPremium() {
        ArrayList<Client> list = new ArrayList<Client>();
        for (Client M : clients) {
            if (M.registerdata.premium) {
                list.add(M);
            }
        }
        return list;
    }

    public static ArrayList<Client> GetListOfNotPremium() {
        ArrayList<Client> list = new ArrayList<Client>();
        for (Client M : clients) {
            if (M.registerdata.premium == false) {
                list.add(M);
            }
        }
        return list;
    }

    public static ArrayList<Client> getAll() {
        ArrayList<Client> list = new ArrayList<Client>();
        for (Client M : clients) {
            list.add(M);
        }
        return list;
    }

    public static void remove(Client E) {
        clients.remove(E);
        Update();
    }

    public static Client find(int cin) {
        for (Client M : clients) {
            if (M.getCin() == cin) return M;
        }
        return null;
    }

    public static boolean exists(Client E) {
        return clients.contains(E);
    }

    public static void Update() {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder
                    .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                    .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                    .create();
            FileWriter writer = new FileWriter(src);
            writer.write(gson.toJson(clients));
            writer.close();
        } catch (IOException e) {
            IO.println("ERROR UPDATING!");
        }
    }


    public static void load(String newsrc) {
        src = newsrc;

        Type pType = new TypeToken<ArrayList<Client>>() {
        }.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();
        FileReader fr;
        try {
            fr = new FileReader(src);
            clients = gson.fromJson(fr, pType);
        } catch (FileNotFoundException e) {
            try {
                FileWriter writer = new FileWriter(src);
                writer.write("[]");
                writer.close();


                try {
                    fr = new FileReader(src);
                    clients = gson.fromJson(fr, pType);

                } catch (FileNotFoundException e2) {
                }
            } catch (IOException e3) {
            }

        }


    }


}


class LocalDateSerializer implements JsonSerializer<LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");

    @Override
    public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDate));
    }
}

class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString(),
                DateTimeFormatter.ofPattern("M/dd/yyyy").withLocale(Locale.ENGLISH));
    }
}



