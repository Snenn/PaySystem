package by.snenn.controller.Util;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class Util {

    public static String getPrincipal(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    public static int getStartIndex (HttpServletRequest req){
        int startNumber;
        try {startNumber =Form.getInt(req, "startIndex");}
        catch (ParseException e) {
            try {startNumber = Form.getInt(req, "startNumber");}
            catch (ParseException e1){startNumber = 0;}
        }
        return startNumber;
    }
}
