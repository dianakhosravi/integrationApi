package com.bambura.integrationApi.controller;

import com.bambura.integrationApi.Dao.UserDao;
import com.bambura.integrationApi.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class OperatorPlatform {

    @Autowired
    static UserDao userDao;

    public static Verify verify(String sessionId, String userId) {
        Optional<User> user = userDao.findAll().stream()
                .filter(c -> c.getSessionId().equalsIgnoreCase(sessionId) && c.getUserId().equalsIgnoreCase(userId))
                .findAny();

        return user.isPresent() ?
                Verify.builder()
                        .user(user.get())
                        .success(true)
                        .build()
                : Verify.builder().success(false).errCode(123).errMsg("Unknown userId").build();
    }


/*    Authorize authorize;
    Account account = accountDao.findAll().stream()
            .filter(c -> c.getAccountId().equalsIgnoreCase(accountId))
            .findAny().orElseGet(null);
    Transfer transfer = Transfer.builder().build();
    Transaction transaction = Transaction.builder().build();

        if (txAmountCy.equalsIgnoreCase("SEK")) {
        //Do a Transaction

        if (account.getBalance() + txAmount > 0) {
            //DO transfer
            transaction.setTxAmount(txAmount);
            account.setBalance(account.getBalance() + txAmount);
            transfer.setTransaction(transaction);
            transfer.s
        } else{

        }
        authorize = Authorize.builder()
                .errCode(10001)
                .errMsg("Authorization failed")
                .build();
    }
    */
}
