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

    @GetMapping("/")
    public List<AddressModel> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AddressModel> getAddressById(@PathVariable Long id) {
        Optional<AddressModel> address = addressService.getAddressById(id);
        return address.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public AddressModel createAddress(@RequestBody AddressModel address) {
        return addressService.createAddress(address);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressModel> updateAddress(@PathVariable Long id, @RequestBody AddressModel newDetails) {
        Optional<AddressModel> updatedAddress = addressService.updateAddress(id, newDetails);
        return updatedAddress.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        return addressService.deleteAddress(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
