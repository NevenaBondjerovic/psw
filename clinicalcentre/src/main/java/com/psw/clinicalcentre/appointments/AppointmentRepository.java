package com.psw.clinicalcentre.appointments;

import com.psw.clinicalcentre.users.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    Set<Appointment> findAllByScheduledFor(User user);
    Optional<Appointment> findById(Integer id);

    @Query("select a from Appointment a " +
            "where a.doctor.id = :id " +
            "and a.dateOfAppointment >= curdate() ")
    Set<Appointment> findAllFutureAppointmentsForDoctor(@Param("id") Integer id);

}
