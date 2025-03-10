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

    @PostMapping("/add")
    public ResponseEntity<AddressBookModel> addAddress(@RequestBody AddressBookDTO dto) {
        return ResponseEntity.ok(service.saveAddress(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressBookModel>> getAllAddresses() {
        return ResponseEntity.ok(service.getAllAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Long id) {
        return service.getAddressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody AddressBookDTO dto) {
        return service.updateAddress(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        boolean deleted = service.deleteAddress(id);
        if (deleted) {
            return ResponseEntity.ok("Address entry deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
