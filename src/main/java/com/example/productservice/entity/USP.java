package com.example.productservice.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class USP {

    private Boolean isDualDriver;
    private Boolean isWired;
    private Integer driverSize;
    private Boolean hasMicrophone;
    private Boolean supportNoiseCancelling;
    private Boolean supportNoiseIsolation;
}
