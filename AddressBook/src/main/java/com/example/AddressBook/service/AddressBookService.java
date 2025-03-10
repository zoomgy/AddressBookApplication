package com.example.AddressBook.service;

import com.example.AddressBook.dto.AddressBookDTO;
import com.example.AddressBook.model.AddressBookModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j  // Enables Lombok Logging
@Service
public class AddressBookService {

    private final List<AddressBookModel> addressList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Save a new address entry
    public AddressBookModel saveAddress(AddressBookDTO dto) {
        AddressBookModel address = new AddressBookModel();
        address.setId(idCounter.getAndIncrement()); // Assign a unique ID
        address.setName(dto.getName());
        address.setPhone(dto.getPhone());
        address.setEmail(dto.getEmail());
        addressList.add(address);

        log.info("Saved new address: ID={}, Name={}", address.getId(), address.getName());
        return address;
    }

    // Get all address entries
    public List<AddressBookModel> getAllAddresses() {
        log.info("Fetching all addresses, Total Count: {}", addressList.size());
        return addressList;
    }

    // Get address by ID
    public Optional<AddressBookModel> getAddressById(Long id) {
        log.info("Fetching address with ID: {}", id);
        return addressList.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    // Update address by ID
    public Optional<AddressBookModel> updateAddress(Long id, AddressBookDTO dto) {
        log.info("Updating address with ID: {}", id);
        return getAddressById(id).map(existingAddress -> {
            existingAddress.setName(dto.getName());
            existingAddress.setPhone(dto.getPhone());
            existingAddress.setEmail(dto.getEmail());
            log.info("Updated address: ID={}, Name={}", id, existingAddress.getName());
            return existingAddress;
        });
    }

    // Delete address by ID
    public boolean deleteAddress(Long id) {
        log.info("Deleting address with ID: {}", id);
        boolean removed = addressList.removeIf(address -> address.getId().equals(id));
        if (removed) {
            log.info("Successfully deleted address with ID: {}", id);
        } else {
            log.warn("Address with ID={} not found", id);
        }
        return removed;
    }
}
