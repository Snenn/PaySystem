package by.snenn.controller.Util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static int parserIdFromForm(String string) {
        String result = null;
        StringBuilder text = new StringBuilder(string);
        Pattern p = Pattern.compile("(ID )[0-9]+");
        Matcher m = p.matcher(text);
        while (m.find()) {
            result = text.substring(m.start() + 3, m.end());
        }
        int id = Integer.parseInt(result);
        return id;
    }
}
