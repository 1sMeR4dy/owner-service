package com.rady.ownerservice.domain.entity;

import com.rady.ownerservice.domain.enums.OwnerStatus;
import io.r2dbc.spi.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name ="owners")
public class Owner {

    @Id
    private UUID id;

    private String email;

    private String phone;

    private OwnerStatus status;

    private Instant createdAt;

    private Instant updatedAt;

}
