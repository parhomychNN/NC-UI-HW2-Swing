package ru.parhomych.netcracker.ui.swing.book;


import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookTableModel extends AbstractTableModel {

    public List<Book> getBooks() {
        return books;
    }

    private List<Book> books = new ArrayList<>();

    private ObjectMapper mapper = new ObjectMapper();

    public void editBook(int bookNo, Book b){
        books.set(bookNo, b);
        fireTableDataChanged();
    }

    public void addBook(Book b){
        books.add(b);
        fireTableDataChanged();
    }

    public void removeBook(int rowOfBookToDelete){
        books.remove(rowOfBookToDelete);
        fireTableDataChanged();
    }

    public BookTableModel() {


        Book book1 = new Book("Гарри Поттер",
                new Author[]{new Author("Роулинг", "jdj@ss.ru", 'f')},
                new Date(),
                AgeRestriction.PLUS7,
                23.3,
                false);
        /*books.add(book1);
        books.add(book1);
        books.add(book1);*/
}

    public void saveChangesToJsonFile(JFrame owner, File fileForSaveLibrary){
        // JSON Test

        //ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

        try (FileOutputStream fileForJsonStorage = new FileOutputStream(fileForSaveLibrary)
        ){
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileForJsonStorage, books);
        } catch (IOException e) {
            System.out.println("Закрылся поток");
        }
    }

    public void openNewLibrary(File JSONfileToBeOpened){
        books.clear();
        List<Book> booksFromJSON;
        //ObjectMapper mapper = new ObjectMapper();
        try {
            booksFromJSON = mapper.readValue(JSONfileToBeOpened, new TypeReference<List<Book>>(){});
            books = booksFromJSON;
            fireTableDataChanged();
        } catch (IOException e) {
            System.out.println("Неверный формат файла, ошибка при развертке файла Json");
            e.printStackTrace();
        }

    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book cur=books.get(rowIndex);
        switch (columnIndex){
            case 0:
                return cur.getName();
            case 1:
                return cur.getAuthorNames();
            case 2:
                return cur.getDate();
            case 3:
                return cur.getAgeRestriction().getDescription();
            case 4:
                return cur.getPrice();
            case 5:
                return cur.getGift();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Название";
            case 1:
                return "Автор";
            case 2:
                return "Дата добавления";
            case 3:
                return "Возрастное ограничение";
            case 4:
                return "Цена";
            case 5:
                return "Получено в дар";
        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Date.class;
            case 3:
                return String.class;
            case 4:
                return Double.class;
            case 5:
                return Boolean.class;
        }
        return Object.class;
    }

}
