package com.example.AddressBook.controller;

import com.example.AddressBook.dto.AddressDTO;
import com.example.AddressBook.model.AddressModel;
import com.example.AddressBook.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addressbook")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // GET all addresses
    @GetMapping("/")
    public List<AddressModel> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // GET address by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Create Address
    @PostMapping("/create")
    public AddressModel createAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.createAddress(addressDTO);
    }

    // DELETE - Remove Address
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        return addressService.deleteAddress(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
