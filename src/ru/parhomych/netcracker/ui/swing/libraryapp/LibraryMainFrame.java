package ru.parhomych.netcracker.ui.swing.libraryapp;

import ru.parhomych.netcracker.ui.swing.book.BookTableModel;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class LibraryMainFrame extends JFrame {

    private JButton saveAsButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton addButton;
    private JButton saveButton;
    private JMenuItem addBookMenuItem;
    private JMenuItem deleteBookMenuItem;
    private JMenuItem editBookMenuItem;
    private JLabel infoText;

    private JTable booksTable;

    private BookTableModel tableModel = new BookTableModel();
    int currentItemRow = -1;

    public BookTableModel getTableModel() {
        return tableModel;
    }

    File currentFile = null;

    public void setTableModel(BookTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public LibraryMainFrame() {
        setTitle("Система учета книг библиотеки");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocation(200, 100);


        // MENU
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        // menu FILE
        JMenu menuFile = new JMenu("Файл");
        JMenuItem openLibraryMenuItem = new JMenuItem("Открыть базу книг");
        openLibraryMenuItem.addActionListener(e -> openLibrary());
        menuFile.add(openLibraryMenuItem);

        JMenuItem saveLibraryMenuItem = new JMenuItem("Сохранить базу книг");
        saveLibraryMenuItem.addActionListener(e -> saveLibrary());
        menuFile.add(saveLibraryMenuItem);
        menuFile.addSeparator();
        JMenuItem exitMenuItem = new JMenuItem("Выйти");
        exitMenuItem.addActionListener(e -> {
            System.exit(0);
        });
        menuFile.add(exitMenuItem);
        menuBar.add(menuFile);
        // menu EDIT

        JMenu menuEdit = new JMenu("Редактировать");
        addBookMenuItem = new JMenuItem("Добавить книгу");
        addBookMenuItem.addActionListener(e -> addBook());
        menuEdit.add(addBookMenuItem);
        deleteBookMenuItem = new JMenuItem("Удалить книгу");
        deleteBookMenuItem.addActionListener(e -> deleteBook());
        menuEdit.add(deleteBookMenuItem);
        editBookMenuItem = new JMenuItem("Редактировать книгу");
        editBookMenuItem.addActionListener(e -> editBook());
        menuEdit.add(editBookMenuItem);
        menuBar.add(menuEdit);
        // menu HELP
        JMenu menuHelp = new JMenu("Справка");
        JMenuItem aboutMenuItem = new JMenuItem("О программе");
        menuHelp.add(aboutMenuItem);
        aboutMenuItem.addActionListener(e -> {
            aboutDialog();
        });
        menuBar.add(menuHelp);

        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
        JPanel highPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        highPanel.setName("high");
        mainPanel.add(highPanel, BorderLayout.NORTH);
        JPanel lowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lowPanel.setName("low");
        mainPanel.add(lowPanel, BorderLayout.SOUTH);

        infoText = new JLabel("Добавляйте книги в новую библиотеку (не забудьте её сохранить)");
        highPanel.add(infoText);

        // TABLE
        booksTable = new JTable(tableModel);
        booksTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                currentItemRow = booksTable.getSelectedRow();
                if (currentItemRow > -1) {
                    setEditElementsActive(true);
                }
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(booksTable);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        editButton = new JButton("Редактировать");
        editButton.addActionListener(e -> editBook());
        lowPanel.add(editButton);
        deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(e -> deleteBook());
        lowPanel.add(deleteButton);
        addButton = new JButton("Добавить новую");
        addButton.addActionListener(e -> addBook());
        lowPanel.add(addButton);
        saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> saveLibrary());
        lowPanel.add(saveButton);
        saveAsButton = new JButton("Сохранить как...");
        saveAsButton.addActionListener(e -> saveLibraryAs());
        lowPanel.add(saveAsButton);

        setEditElementsActive(false);
        setAddElementsActive(true);
        setVisible(true);
    }

    private void chooseFile() {
        JFileChooser fileChooserDialog = new JFileChooser();
        fileChooserDialog.setDialogTitle("Сохранить библиотеку");
        FileNameExtensionFilter jsonSaveFileFilter = new FileNameExtensionFilter("JSON Library", "json");
        fileChooserDialog.setFileFilter(jsonSaveFileFilter);
        fileChooserDialog.setCurrentDirectory(new File(
                System.getProperty("user.dir")));
        int result = fileChooserDialog.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooserDialog.getSelectedFile();

            if (FilenameUtils.getExtension(currentFile.getName()).equalsIgnoreCase("json")) {
                // filename is OK as-is
            } else {
                currentFile = new File(currentFile.toString() + ".json");  // append .json
            }
        }
    }

    private void saveLibraryAs() {
        //saveLibrary();
        chooseFile();
        if (currentFile != null) {
            saveLibrary();
        }
    }

    private void saveLibrary() {
        if (currentFile == null) {
            chooseFile();
        }
        if (currentFile != null) {
            tableModel.saveChangesToJsonFile(this, currentFile);
            infoText.setText("Ваша библиотека: " + currentFile.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Сохранен файл: " +
                    currentFile.getName());
        }

    }

    private void openLibrary() {
        JFileChooser fileChooserDialog = new JFileChooser();
        fileChooserDialog.setDialogTitle("Открыть библиотеку");
        FileNameExtensionFilter jsonOpenFileFilter = new FileNameExtensionFilter("JSON Library", "json");
        fileChooserDialog.setFileFilter(jsonOpenFileFilter);
        fileChooserDialog.setCurrentDirectory(new File(
                System.getProperty("user.dir")));
        int result = fileChooserDialog.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooserDialog.getSelectedFile();
            tableModel.openNewLibrary(currentFile);
            setEditElementsActive(true);
            infoText.setText("Ваша библиотека: " + currentFile.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Открыт файл: " +
                    currentFile.getName());
        }
    }

    private void editBook() {
        if (booksTable.getSelectedRow() > -1) {
            new EditBookDialog(this, currentItemRow);
        } else {
            setEditElementsActive(false);
        }
    }

    private void deleteBook() {
        if (booksTable.getSelectedRow() > -1) {
            new DeleteBookDialog(this, currentItemRow);
        } else {
            setEditElementsActive(false);
        }
    }

    private void addBook() {
        new AddBookDialog(this);
    }

    private void aboutDialog() {
        new AboutDialog();
    }

    private void setEditElementsActive(Boolean canBeEdited) {
        editButton.setEnabled(canBeEdited);
        deleteButton.setEnabled(canBeEdited);
        deleteBookMenuItem.setEnabled(canBeEdited);
        editBookMenuItem.setEnabled(canBeEdited);
    }

    private void setAddElementsActive(Boolean canBeAdded) {
        addButton.setEnabled(canBeAdded);
        addBookMenuItem.setEnabled(canBeAdded);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryMainFrame::new);
    }
}
