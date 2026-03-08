package com.rady.ownerservice.validation.helperAnotation;

import com.rady.ownerservice.dto.owner.OwnerRegisterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtLeastOneContactValidator implements ConstraintValidator<AtLeastOneContact, OwnerRegisterRequest> {
    @Override
    public boolean isValid(OwnerRegisterRequest request,
                           ConstraintValidatorContext constraintValidatorContext) {
        boolean hasEmail = request.getEmail() != null && !request.getEmail().isBlank();
        boolean hasPhone = request.getPhone() != null && !request.getPhone().isBlank();
        return hasEmail || hasPhone;

    }
}
