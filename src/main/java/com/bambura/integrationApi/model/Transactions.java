package com.bambura.integrationApi.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Builder
@Data
public class Transactions {

    @Id
    @GeneratedValue
    private String id;
    private User user;
    private List<Transaction> transactions;


}
