package ru.parhomych.netcracker.ui.swing.book;

import ru.parhomych.netcracker.ui.swing.libraryapp.AbstractBookModificationDialog;
import ru.parhomych.netcracker.ui.swing.libraryapp.ErrorDialog;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.HashMap;

public class BookEntryValidator {

    public BookEntryValidator() {
    }

    public static Boolean checkBook(AbstractBookModificationDialog owner) {
        String name = owner.getNameTextField().getText();
        String authors = owner.getAuthorTextField().getText();
        String date = owner.getDateTextField().getText();
        String price = owner.getPriceTextField().getText();
        Boolean isGift = owner.getIsGiftCheckBox().isSelected();

        StringBuilder errMessage = new StringBuilder();
        errMessage.append("<html>");

        Boolean currentCheckingProcess = true;

        // setup borders to default
        owner.getNameTextField().setBorder(new JTextField().getBorder());
        owner.getAuthorTextField().setBorder(new JTextField().getBorder());
        owner.getDateTextField().setBorder(new JTextField().getBorder());
        owner.getPriceTextField().setBorder(new JTextField().getBorder());

        // checking name
        // check the length of name (must be from 2 to 70)
        if (name.length() < 2 ||
                name.length() > 70) {
            owner.getNameTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
            errMessage.append("В поле 'Название книги' должно быть от 2 до 70 символов<br>");
            currentCheckingProcess = false;
        }
        // checking authors
        if (authors.length() < 2 ||
                authors.length() > 70) {
            owner.getAuthorTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
            errMessage.append("В поле 'Автор книги' должно быть от 2 до 70 символов<br>");
            currentCheckingProcess = false;
        }
        // DATE
        // checking date _
        if (date.contains("_")) {
            errMessage.append("Необходимо заполнить все _ в поле 'Дата добавления'<br>");
            owner.getDateTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
            currentCheckingProcess = false;
        } else {

            HashMap<String, Integer> enteredDateParsed = DateParser.parseDate(date);
            // checking day
            if (enteredDateParsed.get("day") > 31) {
                errMessage.append("Проверьте число в поле 'Дата добавления'<br>");
                owner.getDateTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
                currentCheckingProcess = false;
            }
            // checking month
            if (enteredDateParsed.get("month") > 12) {
                errMessage.append("Проверьте месяц в поле 'Дата добавления'<br>");
                owner.getDateTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
                currentCheckingProcess = false;
            }
            // checking year
            if (enteredDateParsed.get("year") > Calendar.getInstance().get(Calendar.YEAR)) {
                errMessage.append("Год в поле 'Дата добавления' не должен быть в будущем<br>");
                owner.getDateTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
                currentCheckingProcess = false;
            }
        }

        // checks the length of price (if not gift - should not be empty)
        if (price.length() == 0 && !isGift) {
            errMessage.append("Поле 'Цена' не должно быть пустым, если книга передана не в дар<br>");
            owner.getPriceTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
            currentCheckingProcess = false;
        } else if (price.length() == 0) {
            owner.setPriceTextField(new JTextField("0"));
        } else if (!price.matches("((-|\\+)?[0-9]+((.|,)[0-9]+)?)+")) {
            errMessage.append("В поле 'Цена' должно быть только число<br>");
            owner.getPriceTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
            currentCheckingProcess = false;
        }

        errMessage.append("</html>");

        if (currentCheckingProcess) {
            return true;
        } else {
            new ErrorDialog(owner, errMessage.toString());
            System.out.println(errMessage);
            return false;
        }


    }
}
