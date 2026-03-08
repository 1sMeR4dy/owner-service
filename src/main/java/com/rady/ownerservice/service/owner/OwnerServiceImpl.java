package com.rady.ownerservice.service.owner;

import com.rady.ownerservice.domain.entity.Owner;
import com.rady.ownerservice.dto.owner.OwnerRegisterRequest;
import com.rady.ownerservice.dto.owner.OwnerResponse;
import com.rady.ownerservice.factory.OwnerFactory;
import com.rady.ownerservice.mapper.OwnerMapper;
import com.rady.ownerservice.normalizer.OwerRegisterRequestNormalizer;
import com.rady.ownerservice.repository.OwnerRepository;
import com.rady.ownerservice.validation.OwnerRegistrationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;
    private final OwnerFactory ownerFactory;
    private final OwnerRegistrationValidator registrationValidator;
    private final OwerRegisterRequestNormalizer normalizer;


    @Override
    public Mono<OwnerResponse> register(OwnerRegisterRequest request) {

        log.info(" Owner registration request.");

        OwnerRegisterRequest normalize =
                normalizer.normalize(request);

        Owner ownerDraft = ownerMapper.toOwnerDraft(normalize);
        Owner pending = ownerFactory.newPendingOwner(ownerDraft);


        return registrationValidator.validate(normalize)
                .then(ownerRepository.save(pending))
                .doOnSuccess(saved2->log.info(" Owner registration successful. ownerId: {}", saved2.getId()))
                .map(ownerMapper::toOwnerResponse);
    }
}
