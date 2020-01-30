package com.psw.clinicalcentre.clinics;

import com.psw.clinicalcentre.appointments.Appointment;
import com.psw.clinicalcentre.appointments.AppointmentRepository;
import com.psw.clinicalcentre.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class ClinicsServiceImpl implements ClinicsService {

    @Autowired
    private ClinicsRepository clinicsRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Set<Clinic> findAll() {
        return clinicsRepository.findAll();
    }

    @Override
    public Clinic findById(Integer id) {
        return clinicsRepository.findById(id).orElseThrow(() -> new NotFoundException("Clinic not found."));
    }

    @Override
    public Set<Appointment> findByDateAndType(Date date, String type) {
        Set<Integer> clinicIds = new HashSet<>();
        Set<Appointment> returnValue = new HashSet<>();
        for (Appointment a : appointmentRepository.findByAppointmentDateAndType(date, type)) {
            if(!clinicIds.contains(a.getClinic().getId())){
                returnValue.add(a);
                clinicIds.add(a.getClinic().getId());
            }
        }
        return returnValue;
    }
}
