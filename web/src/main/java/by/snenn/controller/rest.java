package by.snenn.controller;

import by.snenn.controller.Util.Parser;
import by.snenn.controller.Util.Util;
import by.snenn.pojos.User;
import by.snenn.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("")
public class rest {

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

    @RequestMapping(value = "accounts", method = RequestMethod.GET)
    public String getAccounts(HttpSession httpSession) throws JsonProcessingException {
        User user = userService.findByLogin(Util.getPrincipal());
        httpSession.setAttribute("user", user);
        List accountList = accountService.getAccountsByUser(user.getId());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(accountList);
        return json;

    }

    @RequestMapping(value = "cards", method = RequestMethod.GET)
    public String getCards(HttpSession httpSession) throws JsonProcessingException {
        User user = userService.findByLogin(Util.getPrincipal());
        httpSession.setAttribute("user", user);
        List cardList = creditCardService.getCreditCardsLimitByUser(user.getId());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cardList);
        return json;

    }

    @RequestMapping(value = "payments", method = RequestMethod.GET)
    public String getPayments(HttpSession httpSession) throws JsonProcessingException {
        User user = userService.findByLogin(Util.getPrincipal());
        httpSession.setAttribute("user", user);
        List paymentList = paymentService.getPaymentsLimitByUser(user.getId());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(paymentList);
        return json;

    }

    @RequestMapping(value = "transfers", method = RequestMethod.GET)
    public String getTransfers(HttpSession httpSession) throws JsonProcessingException {
        User user = userService.findByLogin(Util.getPrincipal());
        httpSession.setAttribute("user", user);
        List transfersList = transferService.getTransfersLimitByUser(user.getId());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(transfersList);
        return json;

    }

    @RequestMapping(value = "createAccount", method = RequestMethod.GET)
    public String createAccount(HttpSession httpSession) throws JsonProcessingException {
        User user = userService.findByLogin(Util.getPrincipal());
        httpSession.setAttribute("user", user);
        String messages = accountService.createAccount(user);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(messages);
        return json;

    }

    @RequestMapping(value = "replenishAccount", method = RequestMethod.GET)
    public String replenishAccount(String id, String sum, HttpSession httpSession) throws JsonProcessingException {
        User user = userService.findByLogin(Util.getPrincipal());
        httpSession.setAttribute("user", user);
        String messages;
        messages = accountService.putMoney(
                Parser.parserIdFromForm(id),
                Integer.parseInt(sum),
                user.getId()
        );
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(messages);
        return json;

    }


    @RequestMapping(value = "createCard", method = RequestMethod.GET)
    public String createCard(String id,  HttpSession httpSession) throws JsonProcessingException {
        User user = userService.findByLogin(Util.getPrincipal());
        httpSession.setAttribute("user", user);
        String messages = creditCardService.createCreditCard(Parser.parserIdFromForm(id),
                user.getId());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(messages);
        return json;

    }

    @RequestMapping(value = "createPayment", method = RequestMethod.GET)
    public String createCard(String id, String sum,  HttpSession httpSession) throws JsonProcessingException {
        User user = userService.findByLogin(Util.getPrincipal());
        httpSession.setAttribute("user", user);
        String messages = paymentService.createPayment(
                Parser.parserIdFromForm(id),
                Integer.parseInt(sum),
                user.getId());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(messages);
        return json;

    }

    @RequestMapping(value = "createTransfer", method = RequestMethod.GET)
    public String createTransfer(String id, String sum, String idRecipient, HttpSession httpSession) throws JsonProcessingException {
        User user = userService.findByLogin(Util.getPrincipal());
        httpSession.setAttribute("user", user);
        String messages = transferService.createTransfer(
                Parser.parserIdFromForm(id),
                Integer.parseInt(sum),
                Integer.parseInt(idRecipient),
                user.getId());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(messages);
        return json;

    }

}
