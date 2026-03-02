package com.rady.ownerservice.dto.owner;

import com.rady.ownerservice.domain.enums.OwnerStatus;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;



@Data
public class OwnerResponse {

    UUID id;
    String email;
    String phone;
    OwnerStatus status;
    Instant createdAt;
    Instant updatedAt;
}
