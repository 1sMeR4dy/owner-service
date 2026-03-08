package com.rady.ownerservice.validation;


import com.rady.ownerservice.dto.owner.OwnerRegisterRequest;
import com.rady.ownerservice.exception.BadRequestException;
import com.rady.ownerservice.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class OwnerRegistrationValidator {

    private final OwnerRepository ownerRepository;


    public Mono<Void>validate(OwnerRegisterRequest request){

        validateRequiredContact(request);

        return validateUniqueness(request);
    }

    private Mono<Void>validateUniqueness(OwnerRegisterRequest request){
        return checkEmailUniqueness(request.getEmail())
                .then(checkPhoneUniqueness(request.getPhone()));
    }


    private void validateRequiredContact(OwnerRegisterRequest request){

        boolean hasEmail= StringUtils.hasText(request.getEmail().trim());
        boolean hasPhone= StringUtils.hasText   (request.getPhone().trim());

        if(!hasEmail && !hasPhone){
            throw new BadRequestException("At least one contact information (email or phone) must be provided.");
        }
    }

    private Mono<Void>checkPhoneUniqueness(String phone){
        log.info("Checking phone uniqueness for ownerId: {}", phone);

        if(!StringUtils.hasText(phone)){
            return Mono.empty();
        }
        return ownerRepository.existsByPhone(phone)
                .filter(Boolean::booleanValue)
                .flatMap(exists->Mono.error(
                        new BadRequestException("Phone Number is already in use.")))
                .then();
    }

    private Mono<Void>checkEmailUniqueness(String email){
        log.info("Checking email uniqueness for ownerId: {}", email);

        if (!StringUtils.hasText(email)){
            return Mono.empty();
        }
        return ownerRepository.existsByEmail(email)
                .filter(Boolean::booleanValue)
                .flatMap(exists->Mono.error(
                        new BadRequestException("Email is already in use.")))
                .then();
    }




}
