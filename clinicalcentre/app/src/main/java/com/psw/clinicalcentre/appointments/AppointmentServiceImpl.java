package com.psw.clinicalcentre.appointments;

import com.psw.clinicalcentre.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Set<Appointment> findAllAvailable() {
        return appointmentRepository.findAllByScheduledFor(null);
    }

    @Override
    public Appointment findById(Integer id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Appointment not found."));
    }

    @Override
    public Set<Appointment> findAllFutureForDoctor(Integer id) {
        return appointmentRepository.findAllFutureAppointmentsForDoctor(id);
    }

    @Override
    public Set<SearchDoctorsResponse> findByClinicAndDateAndType(Integer clinicId, Date date, String type) {
        Set<SearchDoctorsResponse> response = new HashSet<>();
        Set<Integer> doctorsId = new HashSet<>();

        Set<Appointment> appointments = appointmentRepository.findByAppointmentDateAndTypeAndClinic(date, type, clinicId);
        appointments.forEach(appointment -> {
            if(!doctorsId.contains(appointment.getDoctor().getId())){
                Set<AppointmentDataDTO> appointmentData = new HashSet<>();
                appointmentData.add(AppointmentDataDTO.builder().id(appointment.getId()).date(date).time(appointment.getTimeOfAppointment())
                        .type(type).build());
                response.add(
                        SearchDoctorsResponse.builder().doctorId(appointment.getDoctor().getId())
                                .doctorName(appointment.getDoctor().getName())
                                .doctorSurname(appointment.getDoctor().getSurname())
                                .score(appointment.getDoctor().getScore())
                                .appointmentData(appointmentData).build());
                doctorsId.add(appointment.getDoctor().getId());
            } else {
                response.forEach(r -> {
                    if(r.getDoctorId().equals(appointment.getDoctor().getId())){
                        r.getAppointmentData().add(AppointmentDataDTO.builder().id(appointment.getId()).date(date)
                                .time(appointment.getTimeOfAppointment()).type(type).build());
                    }
                });
            }
        });
        return response;
    }

    @Override
    public Set<SearchDoctorsResponse> findByClinicId(Integer clinicId) {
        Set<SearchDoctorsResponse> response = new HashSet<>();
        Set<Integer> doctorsId = new HashSet<>();

        Set<Appointment> appointments = appointmentRepository.findByClinicId(clinicId);
        appointments.forEach(appointment -> {
            if(!doctorsId.contains(appointment.getDoctor().getId())){
                Set<AppointmentDataDTO> appointmentData = new HashSet<>();
                appointmentData.add(AppointmentDataDTO.builder().id(appointment.getId()).date(appointment.getDateOfAppointment())
                        .time(appointment.getTimeOfAppointment()).type(appointment.getType().getName()).build());
                response.add(
                        SearchDoctorsResponse.builder().doctorId(appointment.getDoctor().getId())
                                .doctorName(appointment.getDoctor().getName())
                                .doctorSurname(appointment.getDoctor().getSurname())
                                .score(appointment.getDoctor().getScore())
                                .appointmentData(appointmentData).build());
                doctorsId.add(appointment.getDoctor().getId());
            } else {
                response.forEach(r -> {
                    if(r.getDoctorId().equals(appointment.getDoctor().getId())){
                        r.getAppointmentData().add(AppointmentDataDTO.builder().id(appointment.getId()).date(appointment.getDateOfAppointment())
                                .time(appointment.getTimeOfAppointment()).type(appointment.getType().getName()).build());
                    }
                });
            }
        });
        return response;
    }
}
