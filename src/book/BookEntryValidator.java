package book;

import libraryapp.AbstractBookModificationDialog;

import javax.swing.*;
import java.awt.*;

public class BookEntryValidator {

    public BookEntryValidator() {
    }

    public static Boolean checkBook(AbstractBookModificationDialog owner){
        String name = owner.getNameTextField().getText();
        String authors = owner.getAuthorTextField().getText();
        String date = owner.getDateTextField().getText();
        String price = owner.getPriceTextField().getText();

        // name
        if (name.length() < 2 ||
                name.length() > 70){
            owner.getNameTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
            return false;
        }
        if (price.length() == 0){
            return false;
        }

            return true;



    }
}
