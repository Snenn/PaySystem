package by.snenn.controller;

import by.snenn.controller.Util.Messages;
import by.snenn.controller.Util.Patterns;
import by.snenn.controller.Util.Util;
import by.snenn.pojos.User;
import by.snenn.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private
    ICreditCardService creditCardService;
    @Autowired
    private
    IUserService userService;
    @Autowired
    private
    IAccountService accountService;
    @Autowired
    private
    IPaymentService paymentService;
    @Autowired
    private
    ITransferService transferService;

    @RequestMapping(value = {"/cr"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showCreditCards(ModelMap model, HttpServletRequest req) {
        User user=userService.findByLogin(Util.getPrincipal());
        model.addAttribute("user", user);
        int startNumber=Util.getStartIndex(req);
        if (req.getParameter("block") != null) {
            if (req.getParameter("idCard").matches(Patterns.ID_CARD)) {
                int idCard= Integer.parseInt(req.getParameter("idCard"));
                req.setAttribute(Messages.msgMessage, creditCardService.blockCard(idCard));
            } else {
                req.setAttribute(Messages.msgError, "Input Error");
            }
        }

        if (req.getParameter("unlock") != null) {
            if (req.getParameter("idCard").matches(Patterns.ID_CARD)) {
                int idCard= Integer.parseInt(req.getParameter("idCard"));
                req.setAttribute(Messages.msgMessage, creditCardService.unlockCard(idCard));
            } else {
                req.setAttribute(Messages.msgError, "Input Error");
            }
        }
        model.addAttribute("adCount", creditCardService.getCountCreditCards());
        model.addAttribute("startIndex", startNumber);
        model.addAttribute("creditCards", creditCardService.getCreditCardsLimit(startNumber, 8));
        model.addAttribute("creditCardStatuses", creditCardService.getAllcreditCardsStatus());

        return "adminCreditCards";
    }

    @RequestMapping(value = {""}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showMain(HttpSession httpSession) {
        User user=userService.findByLogin(Util.getPrincipal());
        httpSession.setAttribute("user", user);
        httpSession.setAttribute("countUsers", userService.getCountUsers());
        httpSession.setAttribute("countAccounts", accountService.getCountAccounts());
        httpSession.setAttribute("countCards",creditCardService.getCountCreditCards());
        httpSession.setAttribute("countPayments",paymentService.getCountPayments());
        httpSession.setAttribute("countTransfers",transferService.getCountTransfers());
        httpSession.setAttribute("sumBalance",accountService.getSumAllBalance());
        return "admin";
    }

    @RequestMapping(value = {"/users"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showUsers(ModelMap model, HttpServletRequest req) {
        int startNumber=Util.getStartIndex(req);
        model.addAttribute("adCount", userService.getCountUsers());
        model.addAttribute("startIndex", startNumber);
        model.addAttribute("users", userService.getUsersLimit(startNumber, 8));

        return "adminUsers";
    }

    @RequestMapping(value = {"/accounts"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showAccounts(ModelMap model, HttpServletRequest req) {
        int startNumber=Util.getStartIndex(req);
        model.addAttribute("adCount", accountService.getCountAccounts());
        model.addAttribute("startIndex", startNumber);
        model.addAttribute("accounts", accountService.getAccountsLimit(startNumber, 8));

        return "adminAccounts";
    }

    @RequestMapping(value = {"/payments"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showPayments(ModelMap model, HttpServletRequest req) {
        int startNumber=Util.getStartIndex(req);
        model.addAttribute("adCount", paymentService.getCountPayments());
        model.addAttribute("startIndex", startNumber);
        model.addAttribute("payments", paymentService.getPaymentsLimit(startNumber, 8));
        return "adminPayments";
    }

    @RequestMapping(value = {"/transfers"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showTransfers(ModelMap model, HttpServletRequest req) {
        int startNumber=Util.getStartIndex(req);
        model.addAttribute("adCount", transferService.getCountTransfers());
        model.addAttribute("startIndex", startNumber);
        model.addAttribute("transfers", transferService.getTransfersLimit(startNumber, 8));

        return "adminTransfers";
    }

}
