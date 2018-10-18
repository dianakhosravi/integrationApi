package com.bambura.integrationApi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Verify {
    private User user;
    private Boolean success;
    private Number errCode;
    private String errMsg;

}
