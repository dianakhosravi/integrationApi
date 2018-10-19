package com.bambura.integrationApi.controller;

import com.bambura.integrationApi.Dao.UserDao;
import com.bambura.integrationApi.model.User;
import com.bambura.integrationApi.model.Verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperatorPlatform {


    @Autowired
    private UserDao userDao;

    public Verify verify(String sessionId, String userId) {
        Optional<User> user =  userDao.findAll().stream()
                .filter(c -> c.getSessionId().equalsIgnoreCase(sessionId) && c.getUserId().equalsIgnoreCase(userId))
                .findAny();

        return user.isPresent() ?
                Verify.builder()
                        .success(true)
                        .build()
                :Verify.builder().success(false).errCode(123).errMsg("Unknown userId").build();
    }


}
