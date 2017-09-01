package by.snenn.controller;

import by.snenn.pojos.User;
import by.snenn.pojos.UserRole;
import by.snenn.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @Autowired
    MessageSource message;
    @Autowired
    private
    IUserService userService;

    @RequestMapping(value = {"/default"}, method = RequestMethod.GET)
    public String showHomePage(ModelMap model) {
        return "default";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String showLogPage(ModelMap model) {
        return "home";
    }

    @RequestMapping(value = {"/404"}, method = RequestMethod.GET)
    public String notFound(ModelMap model) {
        return "404";
    }

    @RequestMapping(value = {"/403"}, method = RequestMethod.GET)
    public String forbidden(ModelMap model) {
        return "403";
    }

    @RequestMapping(value = {"/login", ""}, method = RequestMethod.GET)
    public void loginPage(ModelMap model) {
    }

    @RequestMapping(value = {"/errorLogin"}, method = RequestMethod.GET)
    public String errorLogin(ModelMap model) {
        return "error";
    }

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("jsp_message", "access denied");
        return "default";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = {"/reg"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showSignUpPage(ModelMap model) {
        return "reg";
    }

    @RequestMapping(value = {"reg/signUp"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String addUser(ModelMap model, @ModelAttribute("userForm") User user,
                          HttpServletRequest request) throws Exception {
        if (userService.findByLogin(user.getLogin())==null){
            user.setAccounts(null);
            UserRole userRole=userService.getRoleUser();
            user.setUserRole(userRole);
            userService.saveOrUpdate(user);
            request.setAttribute("jsp_message", "successful");
            return "login";}
        else {
            request.getSession().setAttribute("jsp_message", "Такой логин существует, выберите другой");
            return "redirect:/reg";}
    }




}
