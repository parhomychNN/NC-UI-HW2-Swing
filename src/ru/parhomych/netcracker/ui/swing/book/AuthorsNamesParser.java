package ru.parhomych.netcracker.ui.swing.book;

public class AuthorsNamesParser {

    public static Author[] parseAuthorsNames(String stringToBeParsed){
        String[] authorsStr = stringToBeParsed.split(",");
        for (int i = 0; i < authorsStr.length; i++) {
            authorsStr[i] = authorsStr[i].trim();
        }
        Author[] resultAuthorsArray = new Author[authorsStr.length];
        for (int i = 0; i < resultAuthorsArray.length; i++) {
            resultAuthorsArray[i] = new Author(authorsStr[i]);
        }
        return resultAuthorsArray;
    }

}
