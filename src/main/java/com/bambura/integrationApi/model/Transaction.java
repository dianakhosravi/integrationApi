package com.bambura.integrationApi.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class Transaction {

    private Number errCode;
    private String errMsg;
    private Double fee;
    private String feeCy;
    private String kycStatus;
    private Number merchantTxId;
    private Boolean success;
    private Double txAmount;
    private String txAmountCy;
    @Id
    private String txId;
    private String txName;
    private Double txPspAmount;
    private String txPspAmountCy;
    private String txRefId;
    private int txTypeId;

}
