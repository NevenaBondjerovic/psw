package com.psw.clinicalcentre.clinics;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface ClinicsRepository extends CrudRepository<Clinic, Integer> {

    Set<Clinic> findAll();
    Optional<Clinic> findById(Integer id);

}
