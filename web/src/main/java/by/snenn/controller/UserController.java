package by.snenn.controller;

import by.snenn.controller.Util.*;
import by.snenn.pojos.User;
import by.snenn.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICreditCardService creditCardService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private ITransferService transferService;
    private Logger logger = Logger.getLogger(HomeController.class.getName());


    @RequestMapping(value = {""}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showUserPage(ModelMap model, HttpServletRequest req, HttpSession httpSession) {
        User user=userService.findByLogin(Util.getPrincipal());
        httpSession.setAttribute("user", user);

        if (req.getParameter("transfer") != null) {
            String messages = null;
            try {
                int idCardSender = Integer.parseInt(Form.getString(req.getParameter("idCardSenderTransfer"), Patterns.NUMBER));
                int idCardRecipient= Integer.parseInt(Form.getString(req.getParameter("idCardRecipientTransfer"), Patterns.NUMBER));
                int sum = Integer.parseInt(Form.getString(req.getParameter("sumTransfer"), Patterns.NUMBER));
                messages = creditCardService.transferMoneyUser(user,idCardSender,idCardRecipient,sum);
            } catch (ParseException e) {
                model.addAttribute(Messages.msgError, "Invalid input!!!");
                logger.error("Error1, Invalid input");
            }
            model.addAttribute(Messages.msgMessage, messages);
        }

        if (req.getParameter("pay") != null) {
            String messages = null;
            try {
                int idCard = Integer.parseInt(Form.getString(req.getParameter("idCardSenderPay"), Patterns.NUMBER));
                int sum = Integer.parseInt(Form.getString(req.getParameter("sumPay"), Patterns.NUMBER));
                messages = creditCardService.payOrderUser(user, idCard, sum);
            } catch (ParseException e) {
                model.addAttribute(Messages.msgError, "Invalid input!!!");
                logger.error("Error1, Invalid input");            }
            model.addAttribute(Messages.msgMessage, messages);
        }

        httpSession.setAttribute ("countAccounts", accountService.getCountByUser(user.getId()));
        httpSession.setAttribute ("countCards", creditCardService.getCountCreditCardsByUser(user.getId()));
        httpSession.setAttribute ("sumBalance", accountService.getSumAllBalanceByUser(user.getId()));
        model.addAttribute("accounts", accountService.getAccountsByUser(user.getId()) );
        model.addAttribute("cards", creditCardService.getCreditCardsLimitByUser(user.getId()) );
        model.addAttribute("creditCardStatuses", creditCardService.getAllcreditCardsStatus());



        return "user";
    }

    @RequestMapping(value = {"/cards"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showUserCardsPage(ModelMap model, HttpSession httpSession) {
        User user= (User) httpSession.getAttribute("user");
        model.addAttribute("cards", creditCardService.getCreditCardsLimitByUser(user.getId()) );
        model.addAttribute("creditCardStatuses", accountService.viewCreditCardStatusesForAccount());
        return "userCards";
    }

    @RequestMapping(value = {"/payments"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showUserPaymentsPage(ModelMap model, HttpSession httpSession) {
        User user= (User) httpSession.getAttribute("user");
        model.addAttribute("payments", paymentService.getPaymentsLimitByUser(user.getId()) );
        return "userPayments";
    }

    @RequestMapping(value = {"/transfers"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showUserTransfersPage(ModelMap model, HttpSession httpSession) {
        User user= (User) httpSession.getAttribute("user");
        model.addAttribute("transfers", transferService.getTransfersLimitByUser(user.getId()) );
        return "userTransfers";
    }

    @RequestMapping(value = {"/createAccount"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showUserСreateAccountPage(ModelMap model, HttpServletRequest req, HttpSession httpSession) {
        User user= (User) httpSession.getAttribute("user");
        if (req.getParameter("createAccount") != null) {
            String messages = accountService.createAccount(user);
            model.addAttribute(Messages.msgMessage, messages);
            httpSession.setAttribute ("countAccounts", accountService.getCountByUser(user.getId()));
        }
        httpSession.setAttribute ("countAccounts", accountService.getCountByUser(user.getId()));
        return "userCreateAccount";
    }

//    @RequestMapping(value = {"/replenishAccount"}, method = {RequestMethod.POST, RequestMethod.GET })
//    public String showUserReplenishAccountPage(ModelMap model, HttpServletRequest req, HttpSession httpSession) throws ParseException {
//        User user= (User) httpSession.getAttribute("user");
//        model.addAttribute("accounts", accountService.getAccountsByUser(0,6,user.getId()) );
//        if (req.getParameter("replenishAccount") != null) {
//            String messages = null;
//            try {
//                messages = accountService.putMoney(
//                        Parser.parserIdFromForm(req.getParameter("selectAccount")),
//                        Integer.parseInt(Form.getString(req.getParameter("sum"), Patterns.NUMBER)));
//            } catch (ParseException e) {
//                model.addAttribute(Messages.msgMessage, "Invalid input!!!");
//                logger.error("Error1, Invalid input");
//            }
//            model.addAttribute(Messages.msgMessage, messages);
//        }
//        httpSession.setAttribute ("sumBalance", accountService.getSumAllBalanceByUser(user.getId()));
//        return "userReplenishAccount";
//    }

    @RequestMapping(value = {"/deleteAccount"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showUserDeleteAccountPage(ModelMap model, HttpServletRequest req, HttpSession httpSession) {
        User user= (User) httpSession.getAttribute("user");
        model.addAttribute("accounts", accountService.getAccountsByUser(user.getId()) );
        if (req.getParameter("deleteAccount") != null) {
            String messages = accountService.resetAccountUser(Parser.parserIdFromForm(req.getParameter("selectAccount")));
            model.addAttribute(Messages.msgMessage, messages);
            httpSession.setAttribute ("countAccounts", accountService.getCountByUser(user.getId()));
        }
        return "userDeleteAccount";
    }

//    @RequestMapping(value = {"/createCard"}, method = {RequestMethod.POST, RequestMethod.GET })
//    public String showUserСreateCardPage(ModelMap model, HttpServletRequest req, HttpSession httpSession) {
//        User user= (User) httpSession.getAttribute("user");
//        model.addAttribute("accounts", accountService.getAccountsByUser(user.getId()) );
//        if (req.getParameter("createCard") != null) {
//            String messages = creditCardService.createCreditCard(Parser.parserIdFromForm(req.getParameter("selectCard")));
//            model.addAttribute(Messages.msgMessage, messages);
//            httpSession.setAttribute ("countCards", creditCardService.getCountCreditCardsByUser(user.getId()));
//        }
//        return "userСreateCard";
//    }

    @RequestMapping(value = {"/deleteCard"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showUserDeleteCardPage(ModelMap model, HttpServletRequest req, HttpSession httpSession) {
        User user= (User) httpSession.getAttribute("user");
        model.addAttribute("cards", creditCardService.getCreditCardsLimitByUser(user.getId()) );
        if (req.getParameter("deleteCard") != null) {
            String messages = creditCardService.blockCard(Parser.parserIdFromForm(req.getParameter("selectCard")));
            model.addAttribute(Messages.msgMessage, messages);
            httpSession.setAttribute ("countCards", creditCardService.getCountCreditCardsByUser(user.getId()));
        }
        return "userDeleteCard";
    }

    @RequestMapping(value = {"/createPayment"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showUserCreatePaymentPage(ModelMap model, HttpServletRequest req, HttpSession httpSession) {
        User user= (User) httpSession.getAttribute("user");
        model.addAttribute("cards", creditCardService.getCreditCardsLimitByUser(user.getId()) );
        model.addAttribute("creditCardStatuses", creditCardService.getAllcreditCardsStatus());
        if (req.getParameter("createPayment") != null) {
            String messages = null;
            try {
                messages = creditCardService.payOrderUser(user,
                        Parser.parserIdFromForm(req.getParameter("selectCard")),
                        Integer.parseInt(Form.getString(req.getParameter("sum"), Patterns.NUMBER)));
                httpSession.setAttribute ("sumBalance", accountService.getSumAllBalanceByUser(user.getId()));
            } catch (ParseException e) {
                model.addAttribute(Messages.msgMessage, "Invalid input!!!");
                logger.error("Error1, Invalid input");
            }
            model.addAttribute(Messages.msgMessage, messages);
        }
        return "userCreatePayment";
    }

    @RequestMapping(value = {"/createTransfer"}, method = {RequestMethod.POST, RequestMethod.GET })
    public String showUserCreateTransferPage(ModelMap model, HttpServletRequest req, HttpSession httpSession) {
        User user= (User) httpSession.getAttribute("user");
        model.addAttribute("cards", creditCardService.getCreditCardsLimitByUser(user.getId()) );
        model.addAttribute("creditCardStatuses", creditCardService.getAllcreditCardsStatus());
        if (req.getParameter("createTransfer") != null) {
            String messages = null;
            try {
                messages = creditCardService.transferMoneyUser(user,
                        Parser.parserIdFromForm(req.getParameter("selectCard")),
                        Integer.parseInt(Form.getString(req.getParameter("idRecipient"), Patterns.NUMBER)),
                        Integer.parseInt(Form.getString(req.getParameter("sum"), Patterns.NUMBER)));
            } catch (ParseException e) {
                model.addAttribute(Messages.msgMessage, "Invalid input!!!");
                logger.error("Error1, Invalid input");
            }
            model.addAttribute(Messages.msgMessage, messages);
        }
        httpSession.setAttribute ("sumBalance", accountService.getSumAllBalanceByUser(user.getId()));
        return "userCreateTransfer";
    }



}
