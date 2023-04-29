package UI;

import Book.Book;
import BorrowBook.BorrowBook;
import BorrowBook.BorrowedBook;
import Commands.AddBook;
import Database.Database;
import Notification.MessageBox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainDashboard extends JFrame {
    private JButton addBookButton;
    private JButton removeBookButton;
    private JButton borrowBookButton;
    private JButton returnBookButton;
    private JButton viewAllBooksButton;
    private JButton viewBurrowedBooksButton;
    private JButton viewOverdueBooksButton;
    private JTable table;
    private List<Book> books;
    private List<Book> borrowedBookList;
    private List<BorrowedBook> borrowedBooks;
    private DefaultTableModel model;
    private boolean bookMenu;
    private boolean borrowedBooksMenu;

    String[] columnNames = {"ID", "Title", "Author", "Publisher", "Published Date"};
    public MainDashboard(){
        bookMenu = false;
        borrowedBooksMenu = false;
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        JPanel addBookPanel = new JPanel();
        addBookButton = new JButton("Add New Book.Book");
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        addBookPanel.add(addBookButton);

        JPanel removeBookPanel = new JPanel();
        removeBookButton = new JButton("Remove Book.Book");
        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBookCheck();
            }
        });
        removeBookPanel.add(removeBookButton);

        JPanel burrowBookPanel = new JPanel();
        borrowBookButton = new JButton("Borrow Book.Book");
        borrowBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrowBook();
            }
        });
        burrowBookPanel.add(borrowBookButton);

        JPanel returnBookPanel = new JPanel();
        returnBookButton = new JButton("Return Book.Book");
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBookCheck();
            }
        });
        returnBookPanel.add(returnBookButton);

        JPanel viewAllBooksPanel = new JPanel();
        viewAllBooksButton = new JButton("View All Books");
        viewAllBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllBooks();
            }
        });
        viewAllBooksPanel.add(viewAllBooksButton);

        JPanel viewBurrowedBooksPanel = new JPanel();
        viewBurrowedBooksButton = new JButton("View Borrowed Books");
        viewBurrowedBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBorrowedBooks();
            }
        });
        viewBurrowedBooksPanel.add(viewBurrowedBooksButton);

        JPanel viewOverdueBooksPanel = new JPanel();
        viewOverdueBooksButton = new JButton("View Overdue Books");
        viewOverdueBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewOverdueBooks();
            }
        });
        viewOverdueBooksPanel.add(viewOverdueBooksButton);

        JPanel tablePanel = new JPanel();
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);


        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(addBookPanel);
        panel.add(removeBookPanel);
        panel.add(burrowBookPanel);
        panel.add(returnBookPanel);
        panel.add(viewAllBooksPanel);
        panel.add(viewBurrowedBooksPanel);
        panel.add(viewOverdueBooksPanel);
        panel.add(tablePanel);

        add(panel);

        setSize(500,550);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void getBooks() {
        books = Database.books;
    }

    private void getBorrowedBooks(){
        borrowedBooks = Database.borrowedBooks;
    }

    private void addBook(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddBook();
            }
        });
        this.dispose();
    }

    private void removeBook(){
        int rowIndex = table.getSelectedRow();
        String id = model.getValueAt(rowIndex,0).toString();
        Book bookToBeRemoved = null;
        for(Book book : Database.books){
            if(book.getId().equals(id)){
                bookToBeRemoved = book;
            }
        }

        if(bookToBeRemoved == null){
            new MessageBox("Invalid");
        }else {
         model.removeRow(rowIndex);
         Database.books.remove(bookToBeRemoved);
        }
    }

    private void borrowBook(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BorrowBook();
            }
        });
        this.dispose();
    }

    private void returnBook(){
        int rowIndex = table.getSelectedRow();
        String id = model.getValueAt(rowIndex,0).toString();
        BorrowedBook bookToBeReturned = null;

        for(BorrowedBook borrowedBook : borrowedBooks){
            if(borrowedBook.getId().equals(id) && borrowedBook.getDateReturned().equals("0")){
                bookToBeReturned = borrowedBook;
                break;
            }
        }

        Database.borrowedBooks.remove(bookToBeReturned);

        viewBorrowedBooks();

    }

    private void viewBorrowedBooks(){
        borrowedView();
        getBorrowedBooks();

        List<Book> borrowedBookList = new ArrayList<>();
        for (BorrowedBook borrowedBook : borrowedBooks){
            if(borrowedBook.getDateReturned().equals("0")){
                String id = borrowedBook.getId();
                for (Book book : Database.books){
                    if(book.getId().equals(id)){
                        borrowedBookList.add(book);
                    }
                }
            }

        }

        Set<Book> withoutDuplicates = new HashSet<>(borrowedBookList);
        borrowedBookList = new ArrayList<>(withoutDuplicates);
        this.borrowedBookList = borrowedBookList;

        DefaultTableModel newModel = new DefaultTableModel(columnNames,0);
        for (Book book : borrowedBookList) {
            Object[] rowData = {book.getId(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublishedDate()};
            newModel.addRow(rowData);
        }
        model = newModel;
        table.setModel(newModel);
        model = newModel;
    }

    private void viewOverdueBooks(){
        borrowedView();
        getBorrowedBooks();

        List<Book> borrowedBookList = new ArrayList<>();
        for (BorrowedBook borrowedBook : borrowedBooks){
            if(borrowedBook.getDateReturned().equals("0") && getOverDue(borrowedBook.getDueDate())){
                String id = borrowedBook.getId();
                for (Book book : Database.books){
                    if(book.getId().equals(id)){
                        borrowedBookList.add(book);
                    }
                }
            }

        }

        Set<Book> withoutDuplicates = new HashSet<>(borrowedBookList);
        borrowedBookList = new ArrayList<>(withoutDuplicates);
        this.borrowedBookList = borrowedBookList;

        DefaultTableModel newModel = new DefaultTableModel(columnNames,0);
        for (Book book : borrowedBookList) {
            Object[] rowData = {book.getId(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublishedDate()};
            newModel.addRow(rowData);
        }
        model = newModel;
        table.setModel(newModel);
        model = newModel;


    }

    private void viewAllBooks(){
        booksView();
        getBooks();
        model = new DefaultTableModel(columnNames,0);
        for (Book book : books) {
            Object[] rowData = {book.getId(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublishedDate()};
            model.addRow(rowData);
        }
        table.setModel(model);

    }

    private void removeBookCheck(){
        if(bookMenu  && !borrowedBooksMenu){
            removeBook();
        }else{
            new MessageBox("Invalid Selection");
        }
    }

    private void returnBookCheck(){
        if(!bookMenu && borrowedBooksMenu){
            returnBook();
        }else {
            new MessageBox("Invalid Selection");
        }
    }

    private void borrowedView(){
        bookMenu = false;
        borrowedBooksMenu =true;
    }

    private void booksView(){
        bookMenu = true;
        borrowedBooksMenu = false;
    }

    private boolean getOverDue(String inputDate){
        boolean overDue = false;

        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);

        if(date.isBefore(today) || date.isEqual(today)){
            overDue = true;
        }

        return overDue;
    }
}
