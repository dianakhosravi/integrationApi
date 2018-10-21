package com.bambora.integrationApi.service;

import com.bambora.integrationApi.Dao.AccountDao;
import com.bambora.integrationApi.Dao.TransactionDao;
import com.bambora.integrationApi.Dao.UserDao;
import com.bambora.integrationApi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IntegrationService {

    @Autowired
    UserDao userDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    TransactionDao transactionDao;

    public Verify verify(String sessionId, String userId) {
        Optional<User> user = userDao.findAll().stream()
                .filter(c -> c.getSessionId().equalsIgnoreCase(sessionId) && c.getUserId().equalsIgnoreCase(userId))
                .findAny();

        return user.isPresent() ?
                Verify.builder()
                        .success(true)
                        .build()
                : Verify.builder().success(false).errCode(123).errMsg("Unknown userId").build();
    }

    public Account getAccountByUserId(String userId) {
        return (userId != null) ?
                accountDao.findAll().stream()
                        .filter(c -> c.getUser().getUserId().equalsIgnoreCase(userId))
                        .findAny().get()
                : null;
    }

    public User findUserById(String userId) {
        return (userId != null) ?
                userDao.findById(userId).get()
                : null;
    }

    public void initialRepository() {
        User user = User.builder()
                .city("Stockholm")
                .country("SWE")
                .dob("1981-01-01")
                .email("test@example.com")
                .firstName("John")
                .lastName("Jonsson")
                .locale("sv_SE")
                .mobile("+46733123123")
                .sessionId("123")
                .gender(Gender.MALE)
                .street("Exampel street 1")
                .userCat("VIP_SE")
                .userId("user_123")
                .zip("177 32")
                .build();
        userDao.save(user);


        Account account = Account.builder()
                .accountHolder("John")
                .accountId("dd14cb2d-623f-46a9-9210-beb8d1f033c9")
                .balance(100.5)
                .balanceCy("SEK")
                .provider("Neteller")
                .user(user)
                .build();
        accountDao.save(account);

    }

    public Transaction saveTransaction(Transaction transaction) {
        return (transaction != null) ?
                transactionDao.save(transaction)
                : null;
    }

    public Transaction getTransactionById(String txId) {
        return (txId != null) ?
                transactionDao.findById(txId).get()
                : null;
    }

    public Account getAccountById(String accountId) {
        return (accountId != null) ?
                accountDao.findById(accountId).get()
                : null;
    }


    public List<Transaction> getAllTransactionsByUserId(String userId) {
        return (userId != null) ?
                transactionDao.findAll().stream()
                        .filter(c -> c.getAccount().getUser().getUserId().equalsIgnoreCase(userId))
                        .collect(Collectors.toList())
                : null;
    }
}

