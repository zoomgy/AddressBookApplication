package com.example.AddressBook.service;

import com.example.AddressBook.dto.AddressBookDTO;
import com.example.AddressBook.exception.AddressBookException;
import com.example.AddressBook.model.AddressBookModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class AddressBookService {

    private final List<AddressBookModel> addressList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public AddressBookModel saveAddress(AddressBookDTO dto) {
        AddressBookModel address = new AddressBookModel();
        address.setId(idCounter.getAndIncrement());
        address.setName(dto.getName());
        address.setPhone(dto.getPhone());
        address.setEmail(dto.getEmail());
        addressList.add(address);

        log.info("Saved new address: ID={}, Name={}", address.getId(), address.getName());
        return address;
    }

    public List<AddressBookModel> getAllAddresses() {
        log.info("Fetching all addresses, Total Count: {}", addressList.size());
        return addressList;
    }

    public AddressBookModel getAddressById(Long id) {
        return addressList.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new AddressBookException("Address with ID " + id + " not found"));
    }

    public AddressBookModel updateAddress(Long id, AddressBookDTO dto) {
        AddressBookModel existingAddress = getAddressById(id); // This will throw exception if ID not found
        existingAddress.setName(dto.getName());
        existingAddress.setPhone(dto.getPhone());
        existingAddress.setEmail(dto.getEmail());

        log.info("Updated address: ID={}, Name={}", id, existingAddress.getName());
        return existingAddress;
    }

    public void deleteAddress(Long id) {
        AddressBookModel existingAddress = getAddressById(id); // This will throw exception if ID not found
        addressList.remove(existingAddress);
        log.info("Deleted address with ID: {}", id);
    }
}
