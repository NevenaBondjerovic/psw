package com.psw.clinicalcentre.appointments;

import com.psw.clinicalcentre.users.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.Optional;
import java.util.Set;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    Set<Appointment> findAllByScheduledFor(User user);
    Optional<Appointment> findById(Integer id);

    @Query("select a from Appointment a " +
            "where a.doctor.id = :id " +
            "and a.dateOfAppointment >= curdate() ")
    Set<Appointment> findAllFutureAppointmentsForDoctor(@Param("id") Integer id);

    @Query("SELECT a \n" +
            "FROM Appointment a \n" +
            "inner join Clinic c on a.clinic.id = c.id \n" +
            "inner join Pricelist p on p.id = a.pricelist.id \n" +
            "inner join TypesOfAppointments t on t.id = a.type.id \n" +
            "where a.dateOfAppointment = :date \n" +
            "and t.name = :type and a.scheduledFor is null ")
    Set<Appointment> findByAppointmentDateAndType(@Param("date") Date date, @Param("type") String type);

    @Query("SELECT a \n" +
            "FROM Appointment a \n" +
            "inner join TypesOfAppointments t on t.id = a.type.id \n" +
            "where a.dateOfAppointment = :date \n" +
            "and a.clinic.id = :clinicId " +
            "and t.name = :type and a.scheduledFor is null ")
    Set<Appointment> findByAppointmentDateAndTypeAndClinic(@Param("date") Date date, @Param("type") String type,
                                                           @Param("clinicId") Integer clinicId);

    @Query("SELECT a \n" +
            "FROM Appointment a \n" +
            "inner join ClinicsDoctors cd on a.clinic.id = cd.clinic.id \n" +
            "where cd.clinic.id = :clinicId and a.dateOfAppointment >= curdate() and a.scheduledFor is null ")
    Set<Appointment> findByClinicId(@Param("clinicId") Integer id);


    @Query("select a from Appointment a " +
            "where a.scheduledFor.id = :id " +
            "and a.dateOfAppointment < curdate() ")
    Set<Appointment> findAllPastAppointmentsForUser(@Param("id") Integer id);
}
