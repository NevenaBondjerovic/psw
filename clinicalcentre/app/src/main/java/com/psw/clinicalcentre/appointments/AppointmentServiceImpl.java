package com.psw.clinicalcentre.appointments;

import com.psw.clinicalcentre.converters.AppointmentConverter;
import com.psw.clinicalcentre.exceptions.NotFoundException;
import com.psw.clinicalcentre.scores.Score;
import com.psw.clinicalcentre.scores.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ScoreRepository scoreRepository;

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

    @Override
    public Set<AppointmentWithScoreDTO> findAllPastAppointmentsForUser(Integer id) {
        Set<Appointment> appointments = appointmentRepository.findAllPastAppointmentsForUser(id);
        Set<Score> scores = scoreRepository.findAllForUserId(id);
        Set<AppointmentWithScoreDTO> returnValue = new HashSet<>();

        appointments.forEach(appointment -> {
            AppointmentWithScoreDTO dto = new AppointmentWithScoreDTO();
            dto.setAppointmentDTO(AppointmentConverter.appointmentToAppointmentDTO(appointment));
            scores.forEach(score -> {
                if(score.getAppointment().getId().equals(appointment.getId())){
                    dto.setScoreForClinic(score.getClinicScore());
                    dto.setScoreForDoctor(score.getDoctorScore());
                }
            });
            returnValue.add(dto);
        });
        return returnValue;
    }
}
