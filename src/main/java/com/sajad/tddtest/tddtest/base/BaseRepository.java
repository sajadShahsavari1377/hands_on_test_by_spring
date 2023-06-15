package com.sajad.tddtest.tddtest.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.yaml.snakeyaml.events.Event;

@NoRepositoryBean
public interface BaseRepository<ENTITY_TYPE> extends JpaRepository<ENTITY_TYPE, Long> {
}
