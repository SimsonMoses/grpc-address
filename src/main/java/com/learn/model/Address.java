package com.learn.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Document
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String addressId;
    @Column(name = "door_no")
    private String doorNo;
    @Column(name = "street_name", length = 30)
    private String streetName;
    @Column(name = "land_mark", length = 30)
    private String landMark;
    @Column(name = "district", length = 25)
    private String district;
    @Column(length = 6)
    private int pincode;
    @Column(length = 25)
    private String state;
    @Column(length = 25)
    private String country;

    @Override
    public String toString() {
        return "Address to String";
    }
}
