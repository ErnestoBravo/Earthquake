package com.earthquake.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earthquake.application.entity.TerremotoEntity;
import com.earthquake.application.repository.TerremotoRepository;

@Service
public class TerremotoService {

	@Autowired
	private TerremotoRepository terremotoRepo;
	
    public List<TerremotoEntity> guardarTerremoto(List<TerremotoEntity> entities) {
        return terremotoRepo.saveAll(entities);
    }
}
