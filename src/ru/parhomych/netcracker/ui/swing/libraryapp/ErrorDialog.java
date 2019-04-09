package ru.parhomych.netcracker.ui.swing.libraryapp;

import javax.swing.*;
import java.awt.*;

public class ErrorDialog extends JDialog {

    public ErrorDialog(AbstractBookModificationDialog owner, String errorMessage) {
        super(owner);
        setSize(200,200);
        setLocation(400,200);
        setTitle("Проверьте правильность ввода");
        setModal(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        JLabel upperInfo = new JLabel("Исправьте следующие ошибки ввода:");
        mainPanel.add(upperInfo, BorderLayout.NORTH);
        JLabel errInfo = new JLabel(errorMessage);
        JPanel errPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        errPanel.add(errInfo);
        mainPanel.add(errPanel, BorderLayout.CENTER);
        JButton okButton = new JButton("Ок, понял");
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(okButton);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        okButton.addActionListener(e -> dispose());
        pack();
        setVisible(true);
    }
}
