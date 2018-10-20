package com.bambura.integrationApi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Authorize {

    private String userId;
    private Double txAmount;
    private String txAmountCy;
    private String txId;
    private Integer txTypeId;
    private String txTypeName;
    private String provider;

}
