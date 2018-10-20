package com.bambura.integrationApi.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
public class User {

    private String city;
    private String country;
    private String dob;
    private String email;
    private String firstName;
    private String kycStatus;
    private String lastName;
    private String locale;
    private String mobile;
    private Gender gender;
    private String street;
    //TODO: Where should be sessionId!!!
    private String sessionId;
    private String userCat;
    @Id
    private String userId;
    private String zip;


}


