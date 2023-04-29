package Commands;

import Book.Book;
import Database.Database;
import Notification.MessageBox;
import UI.AddBookBoard;
import UI.MainDashboard;

public class AddBook{

    public AddBook(){
        new AddBookBoard();
    }

    public static void submit(String title,String author,
                              String publisher,String publishedDate){
        int id = Database.books.size() == 0 ? 0 : Integer.parseInt( Database.books.get(Database.books.size()-1).getId());
        Book newBook = new Book(Integer.toString(id+1),title,author,publisher,
                publishedDate);
        Database.books.add(newBook);
        new MessageBox("Created");
        new MainDashboard();
        //this.dispose();
    }

}
