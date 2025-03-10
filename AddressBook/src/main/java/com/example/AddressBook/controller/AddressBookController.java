package com.example.AddressBook.controller;

import com.example.AddressBook.dto.AddressBookDTO;
import com.example.AddressBook.model.AddressBookModel;
import com.example.AddressBook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    // Create a new address entry
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

    // Get an address entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Long id) {
        return service.getAddressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an address entry
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody AddressBookDTO dto) {
        return service.updateAddress(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an address entry
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        service.deleteAddress(id);
        return ResponseEntity.ok("Address entry deleted successfully");
    }
}
