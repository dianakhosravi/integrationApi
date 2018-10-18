package com.bambura.integrationApi.service;

import com.bambura.integrationApi.Dao.*;
import com.bambura.integrationApi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class IntegrationService {

    @Autowired
    UserDao userDao;
    @Autowired
    AccountDao accountDao;

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
                .sex(Sex.MALE)
                .street("Exampel street 1")
                .userCat("VIP_SE")
                .userId("user_123")
                .zip("177 32")
                .build();
        userDao.save(user);

        Account account = Account.builder()
                .accountHolder("John")
                .accountId("dd14cb2d-623f-46a9-9210-beb8d1f033c9.")
                .balance(100.5)
                .balanceCy("SEK")
                .provider("Neteller")
                .build();

        accountDao.save(account);

        /*
        authorizeDao.save(Authorize.builder()
                .authCode("550e8400-e29b-41d4-a716-446655440000")
                .user(user)
                .txAmount(100.5)
                .txAmountCy("SEK")
                .build());

        Transaction transaction = Transaction.builder()
                .txAmount(100.5)
                .txAmountCy("SEK")
                .txId("12345")
                .txName("NetellerDeposit")
                .merchantTxId(111111111)
                .success(false)
                .kycStatus("Approved")
                .fee(0.50)
                .feeCy("SEK")
                .errCode(210003)
                .errMsg("")
                .txPspAmount(0.0)
                .txPspAmountCy("EUR")
                .txRefId("100019999A26720")
                .txTypeId(101)
                .build();

        transactionDao.save(transaction);

        transactionsDao.save(Transactions.builder()
                .user(user)
                .transactions(Collections.singletonList(transaction))
                .build()
        );

        transferDao.save(Transfer.builder()
                .transaction(transaction)
                .user(user)
                .account(account)
                .build()
        );
*/

    }


}
