package com.bambora.integrationApi.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Builder
public class UserRequest {

    private String userId;
    private String sessionId;

}
