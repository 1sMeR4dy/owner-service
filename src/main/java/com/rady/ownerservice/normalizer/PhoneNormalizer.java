package com.rady.ownerservice.normalizer;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PhoneNormalizer {


    public String normalize(String phone) {
        if(!StringUtils.hasText(phone)) {
            return null;
        }
        String trimmed = phone.trim();
        if (!trimmed.matches("\\d+")){
            throw new IllegalArgumentException("Phone number must contain only digits");
        }
        return phone.trim();
    }

}
