package com.psw.clinicalcentre.appointments;

import java.sql.Date;
import java.util.Set;

public interface AppointmentService {

    Set<Appointment> findAllAvailable();
    Appointment findById(Integer id);
    Set<Appointment> findAllFutureForDoctor(Integer id);
    Set<SearchDoctorsResponse> findByClinicAndDateAndType(Integer clinicId, Date date, String type);
}
