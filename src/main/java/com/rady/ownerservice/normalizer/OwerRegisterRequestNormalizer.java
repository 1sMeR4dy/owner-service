package com.rady.ownerservice.normalizer;


import com.rady.ownerservice.dto.owner.OwnerRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwerRegisterRequestNormalizer {

    private final EmailNormalizer emailNormalizer;
    private final PhoneNormalizer phoneNormalizer;


    public OwnerRegisterRequest normalize(OwnerRegisterRequest request) {

        request.setEmail(emailNormalizer.normalize(request.getEmail()));
        request.setPhone(phoneNormalizer.normalize(request.getPhone()));

        return request;
    }


}
