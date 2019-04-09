package libraryapp;

import book.AgeRestriction;
import book.Author;
import book.Book;
import book.BookEntryValidator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddBookDialog extends AbstractBookModificationDialog {

    /*private JTextField nameTextField;
    private JTextField authorTextField;
    private JFormattedTextField dateTextField;
    private JComboBox<AgeRestriction> ageRestrictComboBox;
    private JTextField priceTextField;
    private JCheckBox isGiftCheckBox;
*/
    public AddBookDialog(LibraryMainFrame owner, HashMap<String, Component> mainFrameSharedComponents) {
        super();
        setModal(true);
        setSize(400, 600);
        setLocation(400, 150);
        setTitle("Добавление книги");

        JButton addButton = new JButton("Добавить");
        JButton cancelButton = new JButton("Отмена");
        JLabel infoLabel = new JLabel("Введите данные о новой книге:");
        JLabel nameLabel = new JLabel("Название книги");
        JLabel authorLabel = new JLabel("Автор");
        JLabel ageRestrictLabel = new JLabel("Ограничение по возрасту");
        JLabel priceLabel = new JLabel("Цена книги");
        JLabel isGiftLabel = new JLabel("Получена в дар");

        //authorTextField = new JTextField();
        authorTextField = new JTextField();
        nameTextField = new JTextField();
        MaskFormatter dateMaskFormatter = null;
        try {
            dateMaskFormatter = new MaskFormatter("##.##.####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateMaskFormatter.setPlaceholderCharacter('_');
        JLabel dateLabel = new JLabel("Дата добавления");

        dateTextField = new JFormattedTextField(dateMaskFormatter);

        AgeRestriction[] ageRestrictOptions = {
                AgeRestriction.PLUS7,
                AgeRestriction.PLUS12,
                AgeRestriction.PLUS16,
                AgeRestriction.PLUS18
        };
        ageRestrictComboBox = new JComboBox<>(ageRestrictOptions);

        priceTextField = new JTextField();

        isGiftCheckBox = new JCheckBox();

        nameTextField.setColumns(20);

        cancelButton.addActionListener(e -> this.dispose());

        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
        // for OK and cancel buttons
        JPanel lowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainPanel.add(lowPanel, BorderLayout.SOUTH);

        // ADD Button Action
        addButton.addActionListener(e -> {

            // TODO сделать так, чтобы много авторов через запятую попадали в список
            // TODO верификация введенных данных

            // Парсинг введенных данных и создание объекта Book на добавление
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date = null;
            try {

                date = simpleDateFormat.parse(dateTextField.getText());
            } catch (ParseException e1) {
                e1.getMessage();
            }

            // в условии - валидация
            if (BookEntryValidator.checkBook(this)) {
                Book bookToAdd = new Book(nameTextField.getText(),
                        new Author[]{new Author(authorTextField.getText())},
                        date,
                        (AgeRestriction) ageRestrictComboBox.getSelectedItem(),
                        Double.parseDouble(priceTextField.getText()),
                        isGiftCheckBox.isSelected());
                // TODO еси все ок и верификация прошла норм, добавить книгу
                owner.getTableModel().addBook(bookToAdd);
                this.dispose();
            }
        });

        lowPanel.add(addButton);
        lowPanel.add(cancelButton);
        // for infolabel
        JPanel highPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(highPanel, BorderLayout.NORTH);
        highPanel.add(infoLabel);
        // for text areas
        JPanel wrapTextAreas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel textAreas = new JPanel(new GridLayout(0, 2, 10,10));
        wrapTextAreas.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        wrapTextAreas.add(textAreas);
        mainPanel.add(wrapTextAreas, BorderLayout.CENTER);

        textAreas.add(nameLabel);
        textAreas.add(nameTextField);
        textAreas.add(authorLabel);
        textAreas.add(authorTextField);
        textAreas.add(dateLabel);
        textAreas.add(dateTextField);
        textAreas.add(ageRestrictLabel);
        textAreas.add(ageRestrictComboBox);
        textAreas.add(priceLabel);
        textAreas.add(priceTextField);
        textAreas.add(isGiftLabel);
        textAreas.add(isGiftCheckBox);

        pack();
        setResizable(true);
        setVisible(true);
    }


}
