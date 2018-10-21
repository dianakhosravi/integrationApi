package com.bambora.integrationApi.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Builder
@Entity
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Transaction {

    @ManyToOne
    private Account account;
    private String authCode;
    private Number errCode;
    private String errMsg;
    private Double fee;
    private String feeCy;
    //    private String kycStatus;
    private Number merchantTxId;
    private String provider;
    private Boolean success;
    private Double txAmount;
    private String txAmountCy;
    @Id
    private String txId;
    //   private Double txPspAmount;
    //   private String txPspAmountCy;
    //   private String txRefId;

    // Id = 101 is txTypeName=creditDeposite
    private int txTypeId;
    private String txTypeName;


}
