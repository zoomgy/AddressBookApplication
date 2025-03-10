package com.example.AddressBook.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
