package com.bambora.integrationApi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizeResponse {

    private String userId;
    private Boolean success;
    private String txId;
    private String merchantTxId;
    private String authCode;
    private Number errCode;
    private String errMsg;


}
