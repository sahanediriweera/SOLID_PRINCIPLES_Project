package BorrowBook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class BorrowedBookCSVReader {
    public static List<BorrowedBook> readBorrowedBooks(String filePath) {
        List<BorrowedBook> borrowedBooks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                BorrowedBook book = new BorrowedBook(data[0], data[1], data[2], data[3], data[4]);
                borrowedBooks.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return borrowedBooks;
    }
}
