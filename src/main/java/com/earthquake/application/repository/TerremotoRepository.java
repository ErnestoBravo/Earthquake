package com.earthquake.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.earthquake.application.entity.TerremotoEntity;

public interface TerremotoRepository extends JpaRepository<TerremotoEntity, Integer>{

}

