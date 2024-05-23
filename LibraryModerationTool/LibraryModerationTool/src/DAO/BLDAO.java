package DAO;

import Entities.Client;
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

public class BLDAO {
    static ArrayList<Integer> cins;
    static String src;

    public static void add(int cin) {
        cins.add(cin);
        Update();
    }



    public static ArrayList<Integer> getAll() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Integer M : cins) {
            list.add(M);
        }
        return list;
    }

    public static void remove(Integer E) {
        cins.remove(E);
        Update();
    }

    public static Integer find(int cin) {
        for (Integer M : cins) {
            if (M == cin) return M;
        }
        return null;
    }


    public static void Update() {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(src);
            writer.write(gson.toJson(cins));
            writer.close();
        } catch (IOException e) {
            IO.println("ERROR UPDATING!");
        }
    }


    public static void load(String newsrc) {
        src = newsrc;

        Type pType = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        Gson gson = new GsonBuilder().create();
        FileReader fr;
        try {
            fr = new FileReader(src);
            cins = gson.fromJson(fr, pType);
        } catch (FileNotFoundException e) {
            try {
                FileWriter writer = new FileWriter(src);
                writer.write("[]");
                writer.close();


                try {
                    fr = new FileReader(src);
                    cins = gson.fromJson(fr, pType);

                } catch (FileNotFoundException e2) {
                }
            } catch (IOException e3) {
            }

            }
        }
}
