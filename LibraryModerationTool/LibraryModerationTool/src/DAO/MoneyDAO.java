package DAO;

import Entities.Client;
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

public class MoneyDAO {

    static Integer money;
    static String src;

    public static void add(int E) {
        money = money + E;
        Update();
    }


    public static void remove(int E) {
        money = money - E;
        Update();
    }

    public static void Update() {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(src);
            writer.write(gson.toJson(money));
            writer.close();
        } catch (IOException e) {
            IO.warning("ERROR UPDATING!");
        }
    }


    public static void load(String newsrc) {
        src = newsrc;

        //Type pType = new TypeToken<int>() {
        //}.getType();
        Gson gson = new GsonBuilder().create();
        FileReader fr;
        try {
            fr = new FileReader(src);
            money = gson.fromJson(fr, Integer.class);
        } catch (FileNotFoundException e) {
            try {
                FileWriter writer = new FileWriter(src);
                writer.write("0");
                writer.close();


                try {
                    fr = new FileReader(src);
                    money = gson.fromJson(fr, Integer.class);

                } catch (FileNotFoundException e2) {
                }
            } catch (IOException e3) {
            }

        }

    }

    public static Integer getMoney() {
        return money;
    }
}