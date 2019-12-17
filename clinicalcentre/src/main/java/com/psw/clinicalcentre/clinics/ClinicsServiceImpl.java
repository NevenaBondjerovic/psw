package com.psw.clinicalcentre.clinics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ClinicsServiceImpl implements ClinicsService {

    @Autowired
    private ClinicsRepository clinicsRepository;

    @Override
    public Set<Clinic> findAll() {
        return clinicsRepository.findAll();
    }
}
