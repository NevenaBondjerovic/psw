package com.psw.clinicalcentre.appointments.requests;

import java.util.Set;

public interface AppointmentRequestsService {

    void save(Integer appointmentId, Integer userId);
    Set<AppointmentRequests> findAllUnprocessedByAdmin(Integer adminId);
    void acceptAppointmentRequest(Integer appointmentId);
    void declineAppointmentRequest(Integer appointmentId);
    void approveRequestByAdmin(Integer requestId);
    void declineRequestByAdmin(Integer requestId);

}
