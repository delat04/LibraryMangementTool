package DAO;

import Entities.Imprunt;
import IO.IO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

public class ImpruntDAO {
    static protected ArrayList<Imprunt> imprunts;
    static String src;

    public static void add(Imprunt E) {
        imprunts.add(E);
        Update();
    }

	/*
	public static ArrayList<Imprunt> GetListOfPremium(){
                ArrayList<Imprunt> list= new ArrayList<Imprunt>();
                for(Imprunt M : imprunts){
                        if(M.registerdata.premium){
                                list.add(M);
                        }
                }
                return list;
        }
public static ArrayList<Imprunt> GetListOfNotPremium(){
                ArrayList<Imprunt> list= new ArrayList<Imprunt>();
                for(Imprunt M : imprunts){
                        if(M.registerdata.premium == false){
                                list.add(M);
                        }
                }
                return list;
        }*/

    public static ArrayList<Imprunt> getAll() {
        ArrayList<Imprunt> list = new ArrayList<Imprunt>();
        for (Imprunt M : imprunts) {
            list.add(M);
        }
        return list;
    }

    public static void remove(Imprunt E) {
        imprunts.remove(E);
        Update();
    }

    public static ArrayList<Imprunt> findParCin(int cin) {
        ArrayList<Imprunt> list = new ArrayList<Imprunt>();
        for (Imprunt M : imprunts) {
            if (M.cin == cin) list.add(M);
        }
        return list;
    }

    public static ArrayList<Imprunt> findParBarCode(int barcode) {
        ArrayList<Imprunt> list = new ArrayList<Imprunt>();
        for (Imprunt M : imprunts) {
            if (M.barCode == barcode) list.add(M);
        }
        return list;
    }

    public static Imprunt findParId(int id) {
        for (Imprunt M : imprunts) {
            if (M.imprunt_ID == id) return M;
        }
        return null;
    }

    public static boolean exists(Imprunt E) {
        return imprunts.contains(E);
    }

    public static void Update() {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder
                    .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                    .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                    .create();
            FileWriter writer = new FileWriter(src);
            writer.write(gson.toJson(imprunts));
            writer.close();
        } catch (IOException e) {
            IO.println("ERROR UPDATING!");
        }
    }


    public static void load(String newsrc) {
        src = newsrc;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();
        FileReader fr;
        try {
            fr = new FileReader(src);
            Type pType = new TypeToken<ArrayList<Imprunt>>() {
            }.getType();
            imprunts = gson.fromJson(fr, pType);
        } catch (FileNotFoundException e) {
            try {
                FileWriter writer = new FileWriter(src);
                writer.write("[]");
                writer.close();


                try {
                    fr = new FileReader(src);
                    Type pType = new TypeToken<ArrayList<Imprunt>>() {
                    }.getType();
                    imprunts = gson.fromJson(fr, pType);

                } catch (FileNotFoundException e2) {
                }
            } catch (IOException e3) {
            }

        }


    }


    public static ArrayList<Imprunt> getToday() {
        ArrayList<Imprunt> list = new ArrayList<Imprunt>();
        for (Imprunt M : imprunts) {
            if(M.date.plusDays(7).isEqual( LocalDate.now())) {
                list.add(M);
            }
        }
        return list;
    }
    public static ArrayList<Imprunt> getBad() {
        ArrayList<Imprunt> list = new ArrayList<Imprunt>();
        for (Imprunt M : imprunts) {
            if(M.date.plusDays(7).isBefore( LocalDate.now())) {
                list.add(M);
            }
        }
        return list;
    }

}




