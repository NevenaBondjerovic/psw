package com.psw.clinicalcentre.clinics;

import com.psw.clinicalcentre.appointments.Appointment;

import java.sql.Date;
import java.util.Set;

public interface ClinicsService {

    Set<Clinic> findAll();
    Clinic findById(Integer id);
    Set<Appointment> findByDateAndType(Date date, String type);

}
