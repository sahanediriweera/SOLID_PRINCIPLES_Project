package BorrowBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Notification.MessageBox;
import Database.Database;
import UI.MainDashboard;


public class BorrowBook extends JFrame {
    private JTextField idField;
    private JTextField borrowerNameField;
    private JTextField dateBorrowedField;
    private JTextField dueDateField;
    private JButton submitButton;
    public BorrowBook(){
        setTitle("BorrowBook");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        JPanel idPanel = new JPanel();
        JLabel idLabel = new JLabel("Enter Book.Book ID");
        idField = new JTextField(20);
        idPanel.add(idLabel);
        idPanel.add(idField);

        JPanel borrowerNamePanel = new JPanel();
        JLabel borrowerNameLabel = new JLabel("Borrower Name");
        borrowerNameField = new JTextField(20);
        borrowerNamePanel.add(borrowerNameLabel);
        borrowerNamePanel.add(borrowerNameField);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(dateFormatter);

        JPanel dateBorrowedPanel = new JPanel();
        JLabel dateBorrowedLabel = new JLabel("Date Borrowed");
        dateBorrowedField = new JTextField(20);
        dateBorrowedLabel.setText(formattedDate);
        dateBorrowedPanel.add(dateBorrowedLabel);
        dateBorrowedPanel.add(dateBorrowedField);

        LocalDateTime sevenDaysLater = now.plusDays(7);
        String formattedDateSevenDaysLater = sevenDaysLater.format(dateFormatter);

        JPanel dueDatePanel = new JPanel();
        JLabel dueDateLabel = new JLabel("Enter Due Date");
        dueDateField = new JTextField(20);
        dueDateField.setText(formattedDateSevenDaysLater);
        dueDatePanel.add(dueDateLabel);
        dueDatePanel.add(dueDateField);

        JPanel submitPanel = new JPanel();
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit();
            }
        });
        submitPanel.add(submitButton);


        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(idPanel);
        panel.add(borrowerNamePanel);
        panel.add(dateBorrowedPanel);
        panel.add(dueDatePanel);
        panel.add(submitPanel);

        add(panel);

        setSize(450,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void submit(){
        BorrowedBook borrowedBook = new BorrowedBook(idField.getText(),borrowerNameField.getText(),
                dateBorrowedField.getText(),dueDateField.getText(),"0");

        Database.borrowedBooks.add(borrowedBook);
        new MessageBox("Borrowing Successful");
        new MainDashboard();

        this.dispose();
    }
}
