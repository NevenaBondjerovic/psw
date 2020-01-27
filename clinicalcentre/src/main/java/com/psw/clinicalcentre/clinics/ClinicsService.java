package com.psw.clinicalcentre.clinics;

import java.util.Set;

public interface ClinicsService {

    Set<Clinic> findAll();
    Clinic findById(Integer id);

}
