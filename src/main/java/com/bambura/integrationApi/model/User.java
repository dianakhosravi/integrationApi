package com.bambura.integrationApi.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Builder
public class User {

    private String authCode;
    private Number balance;
    private String balanceCy;
    private String city;
    private String country;
    private String dob;
    private String email;
    private Number errCode;
    private String errMsg;
    private Double fee;
    private String feeCy;
    private String firstName;
    private String kycStatus;
    private String lastName;
    private String locale;
    private Number merchantTxId;
    private String mobile;
    private String provider;
    private String sessionId;
    private Sex sex;
    private String street;
    private boolean success;
    private Double txAmount;
    private String txAmountCy;
    private String txId;
    private String txName;
    private Double txPspAmount;
    private String txPspAmountCy;
    private String txRefId;
    private Integer txTypeId;
    private String userCat;
    @Id
    @GeneratedValue
    private String userId;
    private String zip;


}


