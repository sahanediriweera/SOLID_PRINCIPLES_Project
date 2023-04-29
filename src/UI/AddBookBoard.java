package UI;

import Commands.AddBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddBookBoard extends JFrame{
    private JTextField titleField;
    private JTextField authorField;
    private JTextField publisherField;
    private JTextField publishedDateField;
    private JButton submitButton;

    public AddBookBoard(){
        setTitle("Add Book.Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Enter Book.Book Title");
        titleField = new JTextField(20);
        titlePanel.add(titleLabel);
        titlePanel.add(titleField);

        JPanel authorPanel = new JPanel();
        JLabel authorLabel = new JLabel("Enter Author");
        authorField = new JTextField(20);
        authorPanel.add(authorLabel);
        authorPanel.add(authorField);

        JPanel publisherPanel = new JPanel();
        JLabel publisherLabel = new JLabel("Enter Publisher");
        publisherField = new JTextField(20);
        publisherPanel.add(publisherLabel);
        publisherPanel.add(publisherField);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(dateFormatter);

        JPanel publishedDatePanel = new JPanel();
        JLabel publishedDateLabel = new JLabel("Enter Published Date");
        publishedDateField = new JTextField(20);
        publishedDateField.setText(formattedDate);
        publishedDatePanel.add(publishedDateLabel);
        publishedDatePanel.add(publishedDateField);

        JPanel submitPanel = new JPanel();
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBook.submit();

            }
        });
        submitPanel.add(submitButton);

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(titlePanel);
        panel.add(authorPanel);
        panel.add(publisherPanel);
        panel.add(publishedDatePanel);
        panel.add(submitPanel);

        add(panel);
        setSize(450,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
