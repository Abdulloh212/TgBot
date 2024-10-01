package Javob.Javob4;

public class Section {
    private  int number ;
    private Book[]books;

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Section --" +
                " number =" + number ;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }
}
