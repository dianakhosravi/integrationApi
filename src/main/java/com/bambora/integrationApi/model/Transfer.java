package com.bambora.integrationApi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transfer {
    /*    private String userId;
        private Double txAmount;
        private String txAmountCy;
        private Double txPspAmount;
        private String txPspAmountCy;
        private String fee;
        private String feeCy;
        private String provider;*/
    private Boolean success;
    private String txId;
    /*private String txRefId;
    private Integer txTypeId;
    private String txTypeName;*/

}
