package ru.parhomych.netcracker.ui.swing.book;

import java.util.HashMap;

public class DateParser {
    public static HashMap<String, Integer> parseDate(String dateStr){
        HashMap<String, Integer> parsedDate = new HashMap<>();
        String[] dateStrArr = dateStr.split("[.]");
        parsedDate.put("day", Integer.valueOf(dateStrArr[0]));
        parsedDate.put("month", Integer.valueOf(dateStrArr[1]));
        parsedDate.put("year", Integer.valueOf(dateStrArr[2]));
        return parsedDate;
    }
}
