package com.rady.ownerservice.mapper;


import com.rady.ownerservice.domain.entity.Owner;
import com.rady.ownerservice.dto.owner.OwnerRegisterRequest;
import com.rady.ownerservice.dto.owner.OwnerResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface OwnerMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Owner toOwnerDraft(OwnerRegisterRequest request);

     OwnerResponse toOwnerResponse(Owner owner);
}
