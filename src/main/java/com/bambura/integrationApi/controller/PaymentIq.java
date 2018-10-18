package com.bambura.integrationApi.controller;

import com.bambura.integrationApi.Dao.AccountDao;
import com.bambura.integrationApi.Dao.UserDao;
import com.bambura.integrationApi.exception.UserNotFoundException;
import com.bambura.integrationApi.model.*;
import com.bambura.integrationApi.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(value = "/paymentiq")
public class PaymentIq {
    @Autowired
    IntegrationService integrationService;

    @Autowired
    AccountDao accountDao;


    @GetMapping("/verifyuser")
    public String verifyForm(Model model) {
        model.addAttribute("user", User.builder().build());
        return "user";
    }

    @PostMapping("/verifyuser")
    public String verifySubmit(Model model, @RequestParam String sessionId, @RequestParam String userId) throws UserNotFoundException {

        model.addAttribute("verify", OperatorPlatform.verify(sessionId,userId));
        return "verification";
    }

    @GetMapping("/authorizationShow")
    public String authorizationShow(Model model) {
        model.addAttribute("user", User.builder().build());
        return "authorizationShow";
    }

    @PostMapping("/authorize")
    public String authorize(Model model, @RequestParam Double txAmount, @RequestParam String txAmountCy, @RequestParam String accountId) {


       // model.addAttribute("user", user);
        return "authorization";
    }

    @GetMapping("/transferShow")
    public String transferShow(Model model) {
        model.addAttribute("user", User.builder().build());
        return "transferShow";
    }

/*

    @PostMapping("/transfer")
    public String transfer(Model model, @RequestParam Double fee,
                           @RequestParam String txPspAmountCy) {
        if (user.getBalance().doubleValue() > 0) {
            //TODO: success values?
            user.setSuccess(true);
            user.setErrCode(22222);
            user.setErrMsg("Transfer succeeded");
        } else {
            user.setErrCode(111111);
            user.setErrMsg("Transfer failed");
        }
        model.addAttribute("user", user);
        return "transfer";
    }

    @GetMapping("/cancelPage")
    public String cancelPage(Model model) {
        model.addAttribute("user", user);
        return "cancelPage";
    }

    @PostMapping("/cancel")
    public String transfer(Model model) {
        user.setBalance(user.getBalance().doubleValue() - user.getTxAmount());
        user.setTxAmount(0.0);
        user.setErrCode(111111);
        user.setErrMsg("Transfer failed");
        model.addAttribute("user", user);
        return "cancel";
    }

*/

}
