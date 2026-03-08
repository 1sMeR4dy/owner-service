package com.rady.ownerservice.dto.owner;


import com.rady.ownerservice.validation.helperAnotation.AtLeastOneContact;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@AtLeastOneContact
public class OwnerRegisterRequest {

    @Email(message = "Invalid email format.")
    @Size(max = 80, message = "Email not more than 80 characters.")
    private String email;

    @Size(max = 30, message = "Phone must be <= 30 characters.")
    private String phone;
}

