package com.rady.ownerservice.service.owner;

import com.rady.ownerservice.dto.owner.OwnerRegisterRequest;
import com.rady.ownerservice.dto.owner.OwnerResponse;
import reactor.core.publisher.Mono;

public interface OwnerService {

    Mono<OwnerResponse>register(OwnerRegisterRequest request);
}
