package com.rady.ownerservice.factory;


import com.rady.ownerservice.domain.entity.Owner;
import com.rady.ownerservice.domain.enums.OwnerStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class OwnerFactory {


    private final Clock clock;

    public Owner newPendingOwner(Owner draft){

        Instant now = clock.instant();

        return Owner.builder()
                .id(draft.getId())
                .email(draft.getEmail())
                .phone(draft.getPhone())
                .status(OwnerStatus.PENDING)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }


}
