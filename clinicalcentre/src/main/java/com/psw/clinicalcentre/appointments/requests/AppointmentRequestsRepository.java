package com.psw.clinicalcentre.appointments.requests;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AppointmentRequestsRepository extends CrudRepository<AppointmentRequests, Integer> {

    @Query("select ar from AppointmentRequests ar " +
            "where ar.admin.id = :adminId and ar.approvedByAdmin = null")
    Set<AppointmentRequests> findUnprocessedByAdmin(Integer adminId);

}
