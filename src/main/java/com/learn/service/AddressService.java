package com.learn.service;

import com.learn.exception.CustomException;
import com.learn.model.Address;
import com.learn.request.CreateAddress;
import com.learn.response.CommonResponse;

public interface AddressService {
    CommonResponse createAddressForUser(CreateAddress createAddress);
    CommonResponse getAddressByUserId(Long userId);
    CommonResponse updateAddress(Address address) throws CustomException;
    CommonResponse deleteAddressById(String addressId) throws CustomException;
}