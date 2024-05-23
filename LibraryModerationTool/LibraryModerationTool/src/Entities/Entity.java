package Entities;

import java.util.ArrayList;

public class Entity {
    public ArrayList<Integer> imprunts_ID;
    public int nb;
    public String title;
    public String type;
    protected int barCode;

    public Entity() {
    }

    public Entity(int barCode, int nb, String title) {
        this.title = title;
        this.barCode = barCode;
        this.imprunts_ID = new ArrayList<Integer>();
        this.nb = nb;
        this.type = Entity.class.getName();
    }

    public int getBarCode() {
        return this.barCode;
    }

    public void incrument() {
        this.nb++;
    }

    public void decrement() {
        this.nb--;
    }

    public void setnb(int nb) {
        this.nb = nb;
    }

    public String toString() {
        return "type:" + type + "|";
    }
}
