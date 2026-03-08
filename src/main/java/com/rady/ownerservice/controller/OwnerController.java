package com.rady.ownerservice.controller;


import com.rady.ownerservice.dto.owner.OwnerRegisterRequest;
import com.rady.ownerservice.dto.owner.OwnerResponse;
import com.rady.ownerservice.service.owner.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owners")
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<OwnerResponse> createOwner(
            @RequestBody @Valid OwnerRegisterRequest ownerRegisterRequest) {
        log.info("Owner registration request information : {}", ownerRegisterRequest);
        return ownerService.register(ownerRegisterRequest);
    }
}
