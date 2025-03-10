package com.example.AddressBook.service;

import com.example.AddressBook.dto.AddressBookDTO;
import com.example.AddressBook.model.AddressBookModel;
import com.example.AddressBook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    // Save new contact
    public AddressBookModel saveAddress(AddressBookDTO dto) {
        AddressBookModel address = new AddressBookModel();
        address.setName(dto.getName());
        address.setPhone(dto.getPhone());
        address.setEmail(dto.getEmail());
        return repository.save(address);
    }

    // Get all contacts
    public List<AddressBookDTO> getAllAddresses() {
        List<AddressBookModel> addresses = repository.findAll();
        return addresses.stream()
                .map(address -> new AddressBookDTO(address.getName(), address.getPhone(), address.getEmail()))
                .collect(Collectors.toList());
    }
}
