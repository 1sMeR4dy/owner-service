package com.rady.ownerservice.repository;

import com.rady.ownerservice.domain.entity.Owner;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface OwnerRepository extends ReactiveCrudRepository<Owner, UUID> {

    Mono<Boolean> existsByEmail(String email);
    Mono<Boolean>existsByPhone(String phone);

}
