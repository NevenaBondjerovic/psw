package com.psw.clinicalcentre.appointments;

import com.psw.clinicalcentre.users.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    Set<Appointment> findAllByScheduledFor(User user);
    Optional<Appointment> findById(Integer id);

}
