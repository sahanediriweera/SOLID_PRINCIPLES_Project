package Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class BookCSVReader {
    public static List<Book> read(String filePath) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                String title = values[1];
                String author = values[2];
                String publisher = values[3];
                String publishedDate = values[4];
                Book book = new Book(id, title, author, publisher, publishedDate);
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }
}
