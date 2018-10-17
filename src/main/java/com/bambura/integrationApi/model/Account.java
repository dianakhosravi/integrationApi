package com.bambura.integrationApi.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class Account {

    private String accountHolder;
    @Id
    private String accountId;
    private Double balance;
    private String provider;
    private String txAmountCy;
    private String txName;


}


