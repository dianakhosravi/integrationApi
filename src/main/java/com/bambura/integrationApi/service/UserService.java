package com.bambura.integrationApi.service;

import com.bambura.integrationApi.exception.UserNotFoundException;
import com.bambura.integrationApi.model.Sex;
import com.bambura.integrationApi.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(User.builder()
                .authCode("550e8400-e29b-41d4-a716-446655440000")
                .balance(100.50)
                .balanceCy("SEK")
                .city("Stockholm")
                .country("SWE")
                .dob("1981-01-01")
                .email("test@example.com")
                .errCode(210003)
                .errMsg("")
                .fee(0.50)
                .feeCy("SEK")
                .firstName("John")
                .kycStatus("Approved")
                .lastName("Jonsson")
                .locale("sv_SE")
                .merchantTxId(111111111)
                .mobile("+46733123123")
                .provider("Neteller")
                .sessionId("123")
                .sex(Sex.MALE)
                .street("Exampel street 1")
                .success(false)
                .txAmount(0.0)
                .txAmountCy("SEK")
                .txId("12345")
                .txName("NetellerDeposit")
                .txPspAmount(0.0)
                .txPspAmountCy("EUR")
                .txRefId("100019999A26720")
                .txTypeId(101)
                .userCat("VIP_SE")
                .userId("user_123")
                .zip("177 32")
                .build()
        );

    }


    public User verifyUser(String sessionId, String userId) throws UserNotFoundException {
        for (User user : users) {
            if (user.getSessionId().equalsIgnoreCase(sessionId) & user.getUserId().equalsIgnoreCase(userId)) {
                return user;
            } else {
                throw new UserNotFoundException();
            }
        }
        return null;
    }


}
