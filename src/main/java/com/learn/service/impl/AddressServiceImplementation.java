package com.learn.service.impl;

import com.learn.enumeration.ResponseStatus;
import com.learn.exception.CustomException;
import com.learn.grpc.user.UserPresent;
import com.learn.grpc.user.UserRequest;
import com.learn.grpc.user.UserServiceGrpc;
import com.learn.model.Address;
import com.learn.repository.AddressRepository;
import com.learn.request.CreateAddress;
import com.learn.response.CommonResponse;
import com.learn.service.AddressService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService {

    private static final Logger log = LoggerFactory.getLogger(AddressServiceImplementation.class);

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommonResponse createAddressForUser(CreateAddress createAddress) {
        CommonResponse response = new CommonResponse();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",6002).usePlaintext().build();
        UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
        UserRequest request = UserRequest.newBuilder().setEmail(createAddress.getUserId()).build();
        log.info("UserRequest {}",request);
        UserPresent userPresent =  stub.userExist(request);
        if(userPresent.getIsUserPresent()){
            Address address = modelMapper.map(createAddress, Address.class);
            Address save = addressRepository.save(address);
            log.info("Create Address: "+address);
            response.setCode(200);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setSuccessMessage("Address has been created successfully");
            response.setData(createAddress);
        } else {
            response.setCode(404);
            response.setStatus(ResponseStatus.FAILED);
            response.setErrorMessage("User doesn't Exists!");
            response.setData(createAddress.getUserId());
        }
        return response;
    }

    @Override
    public CommonResponse getAddressByUserId(Long userId) {
        CommonResponse response = new CommonResponse();
//        User user =userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("Data not found", new User()));
//        List<Address> addressRelatedToUser = addressRepository.findByUser(user);
//        if(user!=null && !addressRelatedToUser.isEmpty()){
//            response.setCode(200);
//            response.setStatus(ResponseStatus.SUCCESS);
//            response.setSuccessMessage("Address has been feteched successfully");
//            response.setData(addressRelatedToUser);
//        }else {
//            response.setCode(404);
//            response.setStatus(ResponseStatus.FAILED);
//            response.setErrorMessage("User doesn't have the Address");
//            response.setData(userId);
//        }
        return response;
    }

    @Override
    public CommonResponse updateAddress(Address updatedAddress) throws CustomException {
        CommonResponse response = new CommonResponse();
//        log.info("updatedAddress: "+updatedAddress);
        Address address = addressRepository.findById(updatedAddress.getAddressId()).orElseThrow(() -> new CustomException("Data not found", new Address()));
        log.info("updateAddressMethod: address: "+address.getAddressId());
        log.info("Address: "+address);
        updatedAddress.setAddressId(address.getAddressId());
        if(address!=null){
            addressRepository.save(updatedAddress);
            response.setCode(200);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setSuccessMessage("Address Updated Successfully!");
            response.setData(address);
        }else {
            response.setCode(404);
            response.setStatus(ResponseStatus.FAILED);
            response.setSuccessMessage("Address doesn't exist!");
            response.setData(updatedAddress);
        }
        return response;
    }

    @Override
    public CommonResponse deleteAddressById(String addressId) throws CustomException {
        CommonResponse response = new CommonResponse();
        boolean isAddressExist = addressRepository.existsById(addressId);
        if(isAddressExist){
            Address address = addressRepository.findById(addressId).orElseThrow(()->new CustomException("Data not Found Exception",Address.class));
            addressRepository.deleteById(addressId);
            response.setCode(200);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setSuccessMessage("Address has deleted Successfully");
            response.setData(address);
        }else {
            response.setCode(404);
            response.setStatus(ResponseStatus.FAILED);
            response.setErrorMessage("Address Not Found");
            response.setData(addressId);
        }
        return response;
    }
}