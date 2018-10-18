package com.bambura.integrationApi.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Authorize {

    private User user;
    @Id
    private String authCode;
    private Double txAmount;
    private String txAmountCy;
    private boolean success;
    private Number errCode;
    private String errMsg;

}
