package com.rady.ownerservice.dto.owner;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rady.ownerservice.domain.enums.OwnerStatus;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;



@Data
@JsonPropertyOrder({"id", "email", "phone", "status", "createdAt", "updatedAt"})
public class OwnerResponse {

    UUID id;
    String email;
    String phone;
    OwnerStatus status;
    Instant createdAt;
    Instant updatedAt;

}
