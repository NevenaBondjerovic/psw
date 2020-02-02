package com.psw.clinicalcentre.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);

    @Query("select u from User u " +
            "where type = :userType")
    Optional<User> findByType(@Param("userType") String userType);

    @Query("select u from User u \n" +
            "inner join ClinicsDoctors cd on cd.user.id = u.id \n" +
            "where cd.clinic.id = :clinicId and u.userType = 'CLINIC_ADMIN'")
    Optional<User> findClinicAdminByClinicId(@Param("clinicId") Integer clinicId);
}
