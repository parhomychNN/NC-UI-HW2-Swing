package ru.parhomych.netcracker.ui.swing.libraryapp;

import javax.swing.*;
import java.awt.*;

public class DeleteBookDialog extends JDialog {
    public DeleteBookDialog(LibraryMainFrame owner, int currentItemRow) {
        setModal(true);
        setSize(200, 200);
        setLocation(400, 250);
        setTitle("Удаление книги");

        JButton deleteButton = new JButton("Удалить");
        JButton cancelButton = new JButton("Отмена");
        //.addActionListener(e -> owner);

        JPanel deletePanel = new JPanel(new BorderLayout());
        JLabel warning = new JLabel("Вы хотите удалить эту книгу из базы?");
        warning.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        deletePanel.add(warning, BorderLayout.NORTH);
        JPanel panelForButtons = new JPanel(new GridLayout(1,2, 10,20));
        panelForButtons.add(deleteButton);
        panelForButtons.add(cancelButton);
        JPanel panelforCentering = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelforCentering.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        panelforCentering.add(panelForButtons);
        deletePanel.add(panelforCentering, BorderLayout.SOUTH);

        cancelButton.addActionListener(e -> this.dispose());
        deleteButton.addActionListener(e -> {
            // логика для удаления книги

            owner.getTableModel().removeBook(currentItemRow);

            this.dispose();
        });

        setContentPane(deletePanel);
        pack();
        setVisible(true);
    }
}
