package com.sajad.tddtest.tddtest.model.entity;

import com.sajad.tddtest.tddtest.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uq_name",columnNames = {"name"})})
@NoArgsConstructor@AllArgsConstructor
@Data@SuperBuilder
public class Coin extends BaseEntity {
    private String name;
    private String displayName;
    private String unit;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Coin coin = (Coin) o;
        return super.getId().equals(coin.getId()) && name.equals(coin.name) && displayName.equals(coin.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), name, displayName);
    }
}
