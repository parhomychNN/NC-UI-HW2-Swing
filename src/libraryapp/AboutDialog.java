package libraryapp;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {
    public AboutDialog() {
        setModal(true);
        setSize(250, 250);
        setLocation(400, 150);
        setTitle("О программе");
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel headerLabel = new JLabel("Информация о программе: ");
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        JLabel infoLabel = new JLabel("<html>Эта программа - домашняя работа " +
                "по предмету UI в учебном центре " +
                "компании NetCracker. <br><br>" +
                "Выполнил - Пархоменко Илья</html>");
        mainPanel.add(infoLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("ОК");
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        okButton.addActionListener(e -> {
            this.dispose();
        });

        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        //pack();
        setVisible(true);
    }
}
