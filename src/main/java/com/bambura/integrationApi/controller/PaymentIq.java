package com.bambura.integrationApi.controller;

import com.bambura.integrationApi.Dao.AccountDao;
import com.bambura.integrationApi.Dao.UserDao;
import com.bambura.integrationApi.model.Account;
import com.bambura.integrationApi.model.User;
import com.bambura.integrationApi.model.Verify;
import com.bambura.integrationApi.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/paymentiq")
public class PaymentIq {
    @Autowired
    IntegrationService integrationService;

    @Autowired
    AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    OperatorPlatform operatorPlatform;

    @PostMapping("/verifyuser")
    public ResponseEntity verifySubmit(String sessionId, String userId) {

        Verify verify = operatorPlatform.verify(sessionId, userId);

        if (verify.getSuccess() == true) {

            User user = userDao.findById(userId).get();
            Account account = accountDao.findAll().stream()
                    .filter(c->c.getUser().getUserId().equalsIgnoreCase(user.getUserId()))
                    .findAny().get();

            HttpHeaders headers = new HttpHeaders();
            headers.add("userId",user.getUserId());
            headers.add("userCat",user.getUserCat());
            headers.add("kycStatus;",user.getKycStatus());
            headers.add("firstName",user.getFirstName());
            headers.add("lastName",user.getLastName());
            headers.add("street",user.getStreet());
            headers.add("zip",user.getZip());
            headers.add("country",user.getCountry());
            headers.add("email",user.getEmail());
            headers.add("mobile",user.getMobile());
            headers.add("dob",user.getDob());
            headers.add("balance",account.getBalance().toString());
            headers.add("balanceCy",account.getBalanceCy());
            headers.add("local",user.getLocale());
            return new ResponseEntity(headers, HttpStatus.OK);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("status","false");
        headers.add("errCode","123");
        headers.add("errMsg","Unknown userId");
        return new ResponseEntity(headers, HttpStatus.OK);
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
