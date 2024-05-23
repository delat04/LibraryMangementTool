package DAO;

import Entities.Book;
import Entities.DVD;
import Entities.Entity;
import adapter.RuntimeTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LibraryDAO {
    static protected ArrayList<Entity> medias;
    static String src;


    public static void add(Entity E) {
        medias.add(E);
        Update();
    }

    public static ArrayList<Entity> getAll() {
        ArrayList<Entity> list = new ArrayList<Entity>();
        for (Entity M : medias) {
            list.add(M);
        }
        return list;
    }


    public static ArrayList<Entity> searchByTitle(String str) {
        ArrayList<Entity> list = new ArrayList<Entity>();
        for (Entity M : medias) {
            if (M.title.contains(str)) {
                list.add(M);
            }
        }
        return list;
    }

    public static ArrayList<Entity> GetListOfEntity(Entity E) {
        ArrayList<Entity> list = new ArrayList<Entity>();
        for (Entity M : medias) {
            if (M.getClass().isInstance(E)) {
                list.add(M);
            }
        }
        return list;
    }

    public static void remove(Entity E) {
        medias.remove(E);
        Update();
    }

    public static Entity find(int barCode) {
        for (Entity M : medias) {
            if (M.getBarCode() == barCode) return M;
        }
        return null;
    }

    public static boolean exists(Entity E) {
        return medias.contains(E);
    }

    public static void Update() {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(src);
            writer.write(gson.toJson(medias));
            writer.close();
        } catch (IOException ignored) {
        }
    }


    public static void load(String newsrc) {
        src = newsrc;

        RuntimeTypeAdapterFactory<Entity> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Entity.class, "type", true)
                .registerSubtype(Book.class, Book.class.getName())
                .registerSubtype(DVD.class, DVD.class.getName());

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
        FileReader fr;
        try {
            fr = new FileReader(src);
            Type pType = new TypeToken<ArrayList<Entity>>() {
            }.getType();
            medias = gson.fromJson(fr, pType);
			/*System.out.println("===============================");
			      File myObj = new File(src);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
			System.out.println("===============================");		*/
        } catch (FileNotFoundException e) {
            try {
                FileWriter writer = new FileWriter(src);
                writer.write("[]");
                writer.close();


                try {
                    fr = new FileReader(src);
                    Type pType = new TypeToken<ArrayList<Entity>>() {
                    }.getType();
                    medias = gson.fromJson(fr, pType);

                } catch (FileNotFoundException ignored) {
                }
            } catch (IOException ignored) {
            }

        }


    }
} 

