package com.bambora.integrationApi.controller;

import com.bambora.integrationApi.model.*;
import com.bambora.integrationApi.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class MerchantController {

    static Double sumOfTxAmount = 0.0;

    @Autowired
    IntegrationService integrationService;
    @Autowired
    Transaction transaction;

    @PostMapping("/verifyuser")
    public ResponseEntity checkUser(@RequestParam String userId, @RequestParam String sessionId) {

        Verify verify = integrationService.verify(sessionId, userId);

        if (verify.getSuccess() == true) {
            Account account = integrationService.getAccountByUserId(userId);
            User user = account.getUser();

            HttpHeaders headers = new HttpHeaders();
            headers.add("userId", user.getUserId());
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
            return new ResponseEntity(headers, HttpStatus.OK);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("status", "false");
        headers.add("errCode", "123");
        headers.add("errMsg", "Unknown userId");
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @PostMapping("/authorize")
    public ResponseEntity authorize(@RequestBody Authorize authorize) {
        ResponseEntity responseEntity;
        UUID uuid = UUID.randomUUID();
        Account account = integrationService.getAccountById(authorize.getAccountId());


        sumOfTxAmount += authorize.getTxAmount();

        if (account.getBalance() - sumOfTxAmount >= 0) {

            transaction = Transaction.builder()
                    .account(account)
                    .authCode(uuid.toString())
                    .merchantTxId(111111111)
                    .provider(authorize.getProvider())
                    .txAmount(authorize.getTxAmount())
                    .txAmountCy(authorize.getTxAmountCy())
                    .txId(authorize.getTxId())
                    .txTypeId(authorize.getTxTypeId())
                    .txTypeName(authorize.getTxTypeName())
                    .build();

            //integrationService.saveTransaction(transaction);

            HttpHeaders headers = new HttpHeaders();

            headers.add("userId", authorize.getUserId());
            headers.add("success", "true");
            headers.add("txId", authorize.getTxId());
            headers.add("merchantTxId", "111111111");
            headers.add("authCode", uuid.toString());

            responseEntity = new ResponseEntity(headers, HttpStatus.OK);

        } else {

            HttpHeaders headers = new HttpHeaders();
            headers.add("status", "false");
            headers.add("errCode", "10001");
            headers.add("errMsg", "Authorize failed");
            headers.add("authCode", uuid.toString());
            responseEntity = new ResponseEntity(headers, HttpStatus.OK);
        }

        return responseEntity;
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody Transfer transfer) {
        //Transaction transaction = integrationService.getTransactionById(transfer.getTxId());
        transaction.setSuccess(true);
        integrationService.saveTransaction(transaction);

        sumOfTxAmount += transaction.getTxAmount();

    }

    @PostMapping("/cancel")
    public void cancel(@RequestBody Transfer transfer) {
        Transaction transaction = integrationService.getTransactionById(transfer.getTxId());
        transaction.setErrCode(111111);
        transaction.setErrMsg("Transfer failed");
        transaction.setSuccess(false);
    }

}
