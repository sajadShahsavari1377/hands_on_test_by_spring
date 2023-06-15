package com.sajad.tddtest.tddtest.model.entity;

import com.sajad.tddtest.tddtest.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uq_username",columnNames = "username")})
@NoArgsConstructor@AllArgsConstructor@Data@SuperBuilder
public class Member extends BaseEntity {
    private String username;
    private String password;
    private String name;
    private String family;
}
