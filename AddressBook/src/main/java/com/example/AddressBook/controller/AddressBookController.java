package com.example.AddressBook.controller;

import com.example.AddressBook.dto.AddressBookDTO;
import com.example.AddressBook.model.AddressBookModel;
import com.example.AddressBook.service.AddressBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    @PostMapping("/add")
    public ResponseEntity<AddressBookModel> addAddress(@Valid @RequestBody AddressBookDTO dto) {
        return ResponseEntity.ok(service.saveAddress(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressBookModel>> getAllAddresses() {
        return ResponseEntity.ok(service.getAllAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookModel> getAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAddressById(id)); // If not found, exception is thrown
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressBookModel> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressBookDTO dto) {
        return ResponseEntity.ok(service.updateAddress(id, dto)); // Exception handled globally
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        service.deleteAddress(id); // If not found, exception is thrown
        return ResponseEntity.ok("Address entry deleted successfully");
    }
}
