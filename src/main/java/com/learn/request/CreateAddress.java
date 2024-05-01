package com.learn.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAddress {
    private String userId;
    private String doorNo;
    private String streetName;
    private String landMark;
    private String district;
    private int pincode;
    private String state;
    private String country;
}
