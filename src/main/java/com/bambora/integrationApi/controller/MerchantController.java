package com.bambora.integrationApi.controller;

import com.bambora.integrationApi.model.*;
import com.bambora.integrationApi.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
public class MerchantController {

    static Double sumOfTxAmount = 0.0;
    static Set<Transaction> txId_list = new HashSet<>();


    @Autowired
    IntegrationService integrationService;

    @RequestMapping(value = "/verifyuser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HttpHeaders> checkUser(@RequestBody UserRequest userRequest) {

        if (integrationService.verify(userRequest.getSessionId(), userRequest.getUserId())) {
            User user = integrationService.getUserById(userRequest.getUserId());
            Account account = integrationService.getAccountByUserId(userRequest.getUserId());

            HttpHeaders headers = new HttpHeaders();
            headers.add("userId", user.getUserId());
            headers.add("success", "true");
            headers.add("userCat", user.getUserCat());
            headers.add("kycStatus;", user.getKycStatus());
            headers.add("firstName", user.getFirstName());
            headers.add("lastName", user.getLastName());
            headers.add("street", user.getStreet());
            headers.add("zip", user.getZip());
            headers.add("country", user.getCountry());
            headers.add("email", user.getEmail());
            headers.add("mobile", user.getMobile());
            headers.add("dob", user.getDob());
            headers.add("balance", account.getBalance().toString());
            headers.add("balanceCy", account.getBalanceCy());
            headers.add("local", user.getLocale());
            return ResponseEntity.ok(headers);

        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("status", "false");
        headers.add("errCode", "123");
        headers.add("errMsg", "Unknown userId");
        return ResponseEntity.ok(headers);
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    @ResponseBody
    public AuthorizeResponse authorize(@RequestBody Authorize authorize) {
        AuthorizeResponse authorizeResponse;
        UUID uuid = UUID.randomUUID();
        Account account = integrationService.getAccountById(authorize.getAccountId());

        sumOfTxAmount += authorize.getTxAmount();

        if ((account.getBalance() - sumOfTxAmount >= 0) &&
                (authorize.getTxAmountCy().equalsIgnoreCase("SEK"))) {

            txId_list.add(Transaction.builder()
                    .account(account)
                    .authCode(uuid.toString())
                    .merchantTxId(111111111)
                    .provider(authorize.getProvider())
                    .txAmount(authorize.getTxAmount())
                    .txAmountCy(authorize.getTxAmountCy())
                    .txId(authorize.getTxId())
                    .txTypeId(authorize.getTxTypeId())
                    .txTypeName(authorize.getTxTypeName())
                    .build());

            authorizeResponse = AuthorizeResponse.builder()
                    .userId(authorize.getUserId())
                    .success(true)
                    .txId(authorize.getTxId())
                    .merchantTxId("111111111")
                    .authCode(uuid.toString())
                    .build();
        } else {
            authorizeResponse = AuthorizeResponse.builder()
                    .success(false)
                    .errCode(10001)
                    .errMsg("Authorize failed")
                    .build();
        }
        return authorizeResponse;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/transfer")
    @ResponseBody
    public Boolean transfer(@RequestParam String txId) {
        Transaction transaction = txId_list.stream()
                .filter(c -> c.getTxId().equalsIgnoreCase(txId))
                .findAny().get();

        transaction.setSuccess(true);
        transaction.setCurrentBalance(transaction.getAccount().getBalance());
        integrationService.saveTransaction(transaction);
        txId_list.remove(transaction);
        sumOfTxAmount += transaction.getTxAmount();

        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cancel")
    @ResponseBody
    public ResponseEntity<HttpHeaders> cancel(@RequestParam String txId) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("user", "user_123");
        headers.add("success", "false");
        headers.add("errCode", "111111");
        headers.add("errMsg", "Transfer failed");

        return ResponseEntity.ok(headers);
    }

    @GetMapping("/transactionsShow")
    public ModelAndView transactionsShow() {
        List<Transaction> transactions = integrationService.getAllTransactionsByUserId("user_123");

        ModelAndView modelAndView = new ModelAndView("transactionList");

        modelAndView.addObject("transactions", transactions);
        modelAndView.addObject("firstName", integrationService.getUserById("user_123").getFirstName());
        modelAndView.addObject("balance", integrationService.getAccountByUserId("user_123").getBalance());
        return modelAndView;
    }


}
