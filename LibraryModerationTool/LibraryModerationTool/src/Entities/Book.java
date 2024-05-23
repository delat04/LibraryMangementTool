package Entities;
import IO.*;
public class Book extends Entity {

    public BookData bookdata;

    public Book() {
    }

    public Book(int barCode, int nb, String title, BookData bk) {
        super(barCode, nb, title);
        this.type = Book.class.getName();
        setData(bk);
    }

    public void setData(BookData bk) {
        this.bookdata = bk;
    }

    public String toString() {
        return super.toString()
                + "barCode:" + IO.red(this.barCode)+"\n"
                + " nb:" + IO.yellow(this.nb) + "\n"
                + " title:" + IO.green(this.title) + "\n"
                + this.bookdata ;
    }

    public BookData getBookdata() {
        return bookdata;
    }

    public void setBookdata(BookData bookdata) {
        this.bookdata = bookdata;
    }
}
