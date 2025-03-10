package com.example.AddressBook.controller;

import com.example.AddressBook.dto.AddressBookDTO;
import com.example.AddressBook.model.AddressBookModel;
import com.example.AddressBook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    // Create new address entry
    @PostMapping("/add")
    public ResponseEntity<AddressBookModel> addAddress(@RequestBody AddressBookDTO dto) {
        AddressBookModel savedAddress = service.saveAddress(dto);
        return ResponseEntity.ok(savedAddress);
    }

    // Get all address entries
    @GetMapping("/all")
    public ResponseEntity<List<AddressBookDTO>> getAllAddresses() {
        return ResponseEntity.ok(service.getAllAddresses());
    }
}
