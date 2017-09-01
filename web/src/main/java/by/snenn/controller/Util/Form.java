package by.snenn.controller.Util;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class Form {
    public static String getString(String parameter,
                                   String patterns) throws ParseException {
        String value=parameter;
        if (value!=null & value.matches(patterns))
            return value;
        else
            throw new ParseException("Incorrect String: "+parameter,0);

    }


    public static int getInt(HttpServletRequest req,
                             String parameter) throws ParseException {
        String value=req.getParameter(parameter);
        if (value != null) {
            if (value.matches("[0-9]+"))
            {return (Integer.parseInt(value));}
        }
        throw new ParseException("Incorrect String: "+parameter,0);
    }
    static boolean isPost(HttpServletRequest req) {
        return (req.getMethod()!=null && req.getMethod().equalsIgnoreCase("post"));

    }
}


