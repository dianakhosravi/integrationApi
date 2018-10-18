package com.bambura.integrationApi.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Transfer {

    @Id
    @GeneratedValue
    private String id;
    private User user;
    private Transaction transaction;
    private Account account;

}
