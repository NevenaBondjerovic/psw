package com.psw.clinicalcentre.clinics;

import com.psw.clinicalcentre.exceptions.NotFoundException;
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

    @Override
    public Clinic findById(Integer id) {
        return clinicsRepository.findById(id).orElseThrow(() -> new NotFoundException("Clinic not found."));
    }
}
