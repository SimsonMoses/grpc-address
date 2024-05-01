package com.learn.controller;

import com.learn.enumeration.ResponseStatus;
import com.learn.model.Address;
import com.learn.request.CreateAddress;
import com.learn.response.CommonResponse;
import com.learn.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("createAddressForUser")
    public CommonResponse createAddressForUser(@RequestBody CreateAddress createAddress) {
        CommonResponse response = new CommonResponse();
        try {
            response = addressService.createAddressForUser(createAddress);
        } catch (Exception ex) {
            response.setCode(500);
            response.setStatus(ResponseStatus.FAILED);
            response.setData(ex.getMessage());
            response.setErrorMessage("Something went wrong. Please try again later");
        }
        return response;
    }

    @GetMapping("getAddressByUserId")
    public CommonResponse getAddressByUserId(@RequestParam Long userId) {
        CommonResponse response = new CommonResponse();
        try {
            response = addressService.getAddressByUserId(userId);
        } catch (Exception ex) {
            response.setCode(500);
            response.setStatus(ResponseStatus.FAILED);
            response.setData(ex.getMessage());
            response.setErrorMessage("Something went wrong. Please try again later");
        }
        return response;
    }

    @PutMapping("updateAddress")
    public CommonResponse updateAddress(@RequestBody Address address) {
        CommonResponse response = new CommonResponse();
        try {
            response = addressService.updateAddress(address);
        } catch (Exception ex) {
            response.setCode(500);
            response.setStatus(ResponseStatus.FAILED);
            response.setData(ex.getMessage());
            response.setErrorMessage("Something went wrong. Please try again later");
        }
        return response;
    }

    @DeleteMapping("deleteAddressById")
    public CommonResponse deleteAddressById(@RequestParam String addressId) {
        CommonResponse response = new CommonResponse();
        try {
            response = addressService.deleteAddressById(addressId);
        } catch (Exception ex) {
            response.setCode(500);
            response.setStatus(ResponseStatus.FAILED);
            response.setData(ex.getMessage());
            response.setErrorMessage("Something went wrong. Please try again later");
        }
        return response;
    }
}
