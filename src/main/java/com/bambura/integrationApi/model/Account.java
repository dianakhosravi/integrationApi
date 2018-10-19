package com.bambura.integrationApi.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Builder
@Entity
public class Account {

    private String accountHolder;
    @Id
    private String accountId;
    private Double balance;
    private String balanceCy;
    private String provider;
    @OneToOne
    private User user;

}


