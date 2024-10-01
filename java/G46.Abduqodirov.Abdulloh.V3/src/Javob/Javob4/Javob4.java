package Javob.Javob4;

import java.util.Scanner;
public class Javob4 {

    static  Section []sections=new Section[100];
    static  Book[]books=new Book[100];

    public static void main(String[] args) {
        int [] arr = new int[100];
         Library library=new Library();
        Scanner scanner=new Scanner(System.in);
        int count=0,countsection=0,countbook=0, newsection=0;
while (true) {
    menu();
    switch (scanner.nextInt()) {
        case 1:
           library.setName(getlibraryname());
            System.out.println("Qo'shildi! ");
            break;
        case 2:
            Section section=new Section();
            System.out.print("Yangi bo'lim qo'shing: ");
             newsection = scanner.nextInt();
            for (int i = 0; i < count; i++) {
                if (arr[i] != newsection) {
                    section.setNumber(newsection);
                    sections[countsection++] = section;
                    library.setSection(sections);
                }else{
                    System.out.println("Bunday raqam ishlatilgan Iltimos boshqa raqam krting !");
                }
                break;
            }

               arr[count] = newsection;
               count++;
            break;
        case 3:
            for (int i = 0; i < countsection; i++) {
                System.out.println(sections[i]);
            }
            break;
        case 4:
         choosesection(countsection,countbook);
            break;
        default:
            System.out.println("Notug'ri");
            break;
    }
}

    }

    private static void choosesection(int countsection,int countbook) {
        Scanner scanner=new Scanner(System.in);
        for (int i = 0; i < countsection; i++) {
            System.out.println(i+1+" " +sections[i]);
        }
        System.out.println("Tanlang: ");
        int selectedsection = scanner.nextInt()-1;
        if (selectedsection < countsection) {
            System.out.println("""
                        1.Kitoblar ro'yxatini ko'rish
                        2.Kitob qo'shish
                        3.Kitob nomini o'zgartirish
                        4.Kitob mavzusini o'zgartirish                                           """);
            switch (scanner.nextInt()) {
                case 1:
                    for (int i = 0; i < countbook; i++) {
                        System.out.println(i+ " "+ books[i]);
                    }
                    break;
                case 2:
                    Book book = new Book();
                    System.out.print("Kitob nomini krting: ");
                    book.setName(scanner.nextLine());
                    System.out.print("Kitobni temasini krting: ");
                    book.setTheme(scanner.nextLine());
                    System.out.print("Kitobni qancha pagesi bor: ");
                    book.setPageCount(scanner.nextInt());
                    books[countbook] = book;
                    sections[selectedsection].setBooks(books);
                    countbook++;
                    break;
                case 3:
                    for (int i = 0; i < countbook; i++) {
                        System.out.println(i + 1 + " " + books[i]);
                    }
                    System.out.print("Tanlang: ");
                    int selectedbook = scanner.nextInt() - 1;
                    if (selectedbook < countbook) {
                        System.out.println("Bunday kitob yo'q!");
                        break;
                    }
                    String bookTheme = books[selectedbook].getTheme();
                    int bookpage = books[selectedbook].getPageCount();
                    Book book1 = new Book();
                    book1.setTheme(bookTheme);
                    book1.setPageCount(bookpage);
                    System.out.print("Kitob nomini krting: ");
                    book1.setName(scanner.nextLine());
                    books[selectedbook] = book1;
                    break;
                case 4:
                    for (int i = 0; i < countbook; i++) {
                        System.out.println(i + 1 + " " + books[i]);
                    }
                    System.out.print("Tanlang: ");
                    int selectedbook1 = scanner.nextInt() - 1;
                    if (selectedbook1 < countbook) {
                        System.out.println("Bunday kitob yo'q!");
                        break;
                    }
                    String bookname1 = books[selectedbook1].getName();
                    int bookpage1 = books[selectedbook1].getPageCount();
                    Book book2 = new Book();
                    book2.setName(bookname1);
                    book2.setPageCount(bookpage1);
                    System.out.print("Kitob temasini krting: ");
                    book2.setName(scanner.nextLine());
                    books[selectedbook1] = book2;
                    break;
            }


        } else {
            System.out.println("unday section yoq");
        }
    }


    private static String getlibraryname() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Kutubxona nomini krting: ");
        String name = scanner.next();
        return name;
    }

    private static void menu() {
        System.out.println("""
            1.Kutubxona nomini krting
            2.Kutubxonaga Yangi Bo'lim qo'shish
            3.Bo'limlar ro'yxatini korish
            4.Bo'lim tanlash""");
    }

}
