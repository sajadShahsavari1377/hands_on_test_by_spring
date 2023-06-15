package com.sajad.tddtest.tddtest.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data@AllArgsConstructor@NoArgsConstructor@SuperBuilder
public class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Version
    private Long version;
}
