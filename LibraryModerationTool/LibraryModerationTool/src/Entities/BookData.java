package Entities;
import IO.*;
public class BookData {
    public int pages;

    public BookData(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "pages:"+IO.yellow(this.pages)+"\n";
    }
}

