package Entities;
import IO.*;
public class DVD extends Entity {
    public DvdData dvddata;

    public DVD() {
    }

    public DVD(int barCode, int nb, String title, DvdData dd) {
        super(barCode, nb, title);
        this.type = DVD.class.getName();
        setData(dd);
    }

    public void setData(DvdData dd) {
        this.dvddata = dd;
    }

    public String toString() {
        return "barCode:" + IO.red(this.barCode)+"\n"
                + " nb:" + IO.green(this.nb)+"\n"
                + " DVD title:" + IO.yellow(this.title)+"\n"
                + " length: " + IO.red(this.dvddata.h + ":" + this.dvddata.m) + "\n";
    }
}
