package com.psw.clinicalcentre.types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TypesServiceImpl implements TypesService{

    @Autowired
    private TypesRepository typesRepository;

    @Override
    public Set<String> findAllDistinctNames() {
        return typesRepository.findAllDistinctNames();
    }
}
