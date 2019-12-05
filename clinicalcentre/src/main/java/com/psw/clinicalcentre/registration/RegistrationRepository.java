package com.psw.clinicalcentre.registration;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RegistrationRepository extends CrudRepository<RegistrationRequest, Integer> {

    Set<RegistrationRequest> findByProcessed(Boolean processed);

}
