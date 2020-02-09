package com.psw.clinicalcentre.scores;

import com.psw.clinicalcentre.appointments.Appointment;
import com.psw.clinicalcentre.appointments.AppointmentRepository;
import com.psw.clinicalcentre.appointments.AppointmentWithScoreDTO;
import com.psw.clinicalcentre.clinics.Clinic;
import com.psw.clinicalcentre.clinics.ClinicsRepository;
import com.psw.clinicalcentre.exceptions.NotFoundException;
import com.psw.clinicalcentre.users.User;
import com.psw.clinicalcentre.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClinicsRepository clinicsRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    @Transactional
    public void addScore(AppointmentWithScoreDTO request) {
        User doctor = userRepository.findById(
                request.getAppointmentDTO().getDoctor().getId())
                .orElseThrow(() -> new NotFoundException("Doctor not found."));
        Clinic clinic = clinicsRepository.findById(
                request.getAppointmentDTO().getClinic().getId())
                .orElseThrow(() -> new NotFoundException("Clinic not found."));
        User user = userRepository.findById(
                request.getAppointmentDTO().getScheduledFor().getId())
                .orElseThrow(() -> new NotFoundException("User not found."));
        Appointment appointment = appointmentRepository.findById(
                request.getAppointmentDTO().getId())
                .orElseThrow(() -> new NotFoundException("Appointment not found"));

        Score score = Score.builder()
                .doctor(doctor).doctorScore(request.getScoreForDoctor())
                .clinic(clinic).clinicScore(request.getScoreForClinic())
                .user(user).appointment(appointment).build();

        scoreRepository.save(score);
    }
}
