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
                    .user(user)
                    .build();

            accountDao.save(account);
        }
   }

