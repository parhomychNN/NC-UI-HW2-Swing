package libraryapp;

import book.AgeRestriction;

import javax.swing.*;

public class AbstractBookModificationDialog extends JDialog{
    protected JTextField nameTextField;
    protected JTextField authorTextField;
    protected JFormattedTextField dateTextField;
    protected JComboBox<AgeRestriction> ageRestrictComboBox;
    protected JTextField priceTextField;
    protected JCheckBox isGiftCheckBox;

    AbstractBookModificationDialog(){}

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getAuthorTextField() {
        return authorTextField;
    }

    public JFormattedTextField getDateTextField() {
        return dateTextField;
    }

    public JTextField getPriceTextField() {
        return priceTextField;
    }
}
