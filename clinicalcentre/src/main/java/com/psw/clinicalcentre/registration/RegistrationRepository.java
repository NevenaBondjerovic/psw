package com.psw.clinicalcentre.registration;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface RegistrationRepository extends CrudRepository<RegistrationRequest, Integer> {

    Set<RegistrationRequest> findByProcessed(Boolean processed);

    @Modifying
    @Query("update RegistrationRequest r " +
            "set r.processed = true, r.approved = true, r.declineReason = null " +
            "where r.user = ( select id from User u where u.username = :username)")
    void acceptRegistrationRequest(@Param("username") String username);

    @Modifying
    @Query("update RegistrationRequest r " +
            "set r.processed = true, r.approved = false, r.declineReason = :reason " +
            "where r.user = ( select id from User u where u.username = :username)")
    void rejectRegistrationRequest(@Param("username") String username, @Param("reason") String declineReason);

    Optional<RegistrationRequest> findByUserUsername(String username);

}
