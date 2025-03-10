package com.example.AddressBook.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookDTO {

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]{2,}$", message = "Name must start with a capital letter and have at least 3 characters")
    private String name;

    private String phone;
    private String email;
}
