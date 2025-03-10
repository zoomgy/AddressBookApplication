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

    // Get All Addresses
    public List<AddressModel> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Get Address by ID
    public Optional<AddressModel> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    // Create Address
    public AddressModel createAddress(AddressDTO addressDTO) {
        AddressModel address = new AddressModel();
        address.setName(addressDTO.getName());
        address.setEmail(addressDTO.getEmail());
        address.setPhoneNumber(addressDTO.getPhoneNumber());
        address.setAddress(addressDTO.getAddress());
        return addressRepository.save(address);
    }

    // Delete Address
    public boolean deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
