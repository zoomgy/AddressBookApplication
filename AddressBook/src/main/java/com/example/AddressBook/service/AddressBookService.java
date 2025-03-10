package com.example.AddressBook.service;

import com.example.AddressBook.dto.AddressBookDTO;
import com.example.AddressBook.model.AddressBookModel;
import com.example.AddressBook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    // Save a new address entry
    public AddressBookModel saveAddress(AddressBookDTO dto) {
        AddressBookModel address = new AddressBookModel();
        address.setName(dto.getName());
        address.setPhone(dto.getPhone());
        address.setEmail(dto.getEmail());
        return repository.save(address);
    }

    // Get all address entries
    public List<AddressBookDTO> getAllAddresses() {
        List<AddressBookModel> addresses = repository.findAll();
        return addresses.stream()
                .map(address -> new AddressBookDTO(address.getName(), address.getPhone(), address.getEmail()))
                .collect(Collectors.toList());
    }

    // Get address by ID
    public Optional<AddressBookModel> getAddressById(Long id) {
        return repository.findById(id);
    }

    // Update address by ID
    public Optional<AddressBookModel> updateAddress(Long id, AddressBookDTO dto) {
        return repository.findById(id).map(existingAddress -> {
            existingAddress.setName(dto.getName());
            existingAddress.setPhone(dto.getPhone());
            existingAddress.setEmail(dto.getEmail());
            return repository.save(existingAddress);
        });
    }

    // Delete address by ID
    public void deleteAddress(Long id) {
        repository.deleteById(id);
    }
}
