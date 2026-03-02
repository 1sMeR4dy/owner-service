package com.rady.ownerservice.controller;


import com.rady.ownerservice.dto.owner.OwnerRegisterRequest;
import com.rady.ownerservice.dto.owner.OwnerResponse;
import com.rady.ownerservice.service.owner.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<OwnerResponse> createOwner(
            @RequestBody @Valid OwnerRegisterRequest ownerRegisterRequest) {
        return ownerService.register(ownerRegisterRequest);
    }
}
