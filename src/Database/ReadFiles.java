package Database;

import Book.BookCSVReader;
import BorrowBook.BorrowedBookCSVReader;

public class ReadFiles {
        public ReadFiles(){
        }

        public static void ReadAllFiles(){
            readBookCSVS();
            readBorrowedBooksCSV();
        }

        private static void readBookCSVS(){
            Database.books = BookCSVReader.read(FilePaths.BOOK_PATH);
        }

        private static void readBorrowedBooksCSV(){
            Database.borrowedBooks = BorrowedBookCSVReader.readBorrowedBooks(FilePaths.BORROWED_BOOKS);
        }

}
