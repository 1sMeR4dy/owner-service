package com.rady.ownerservice.dto.owner;


import com.rady.ownerservice.validation.helperAnotation.AtLeastOneContact;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OwnerRegisterRequest {

    @Email(message = "Invalid email format.")
    @Size(max = 80, message = "Email must not exceed 80 characters.")
    private String email;

    @Size(max = 30, message = "Phone must not exceed 30 characters.")
    @Pattern(regexp = "^[0-9]+$", message = "Phone must contain numbers only.")
    private String phone;
}

