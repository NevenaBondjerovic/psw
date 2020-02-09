package com.psw.clinicalcentre.scores;

import com.psw.clinicalcentre.appointments.AppointmentWithScoreDTO;

public interface ScoreService {

    void addScore(AppointmentWithScoreDTO request);

}
