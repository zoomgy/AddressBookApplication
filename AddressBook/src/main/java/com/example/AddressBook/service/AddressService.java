package com.example.AddressBook.service;

import com.example.AddressBook.dto.AddressDTO;
import com.example.AddressBook.model.AddressModel;
import com.example.AddressBook.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<AddressModel> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<AddressModel> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public AddressModel createAddress(AddressModel address) {
        return addressRepository.save(address);
    }

    public Optional<AddressModel> updateAddress(Long id, AddressModel newDetails) {
        return addressRepository.findById(id).map(address -> {
            address.setName(newDetails.getName());
            address.setAddress(newDetails.getAddress());
            address.setCity(newDetails.getCity());
            address.setState(newDetails.getState());
            address.setZip(newDetails.getZip());
            address.setPhoneNumber(newDetails.getPhoneNumber());
            return addressRepository.save(address);
        });
    }

    public boolean deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
