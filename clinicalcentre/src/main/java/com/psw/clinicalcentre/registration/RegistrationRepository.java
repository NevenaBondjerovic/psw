package com.psw.clinicalcentre.registration;

import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<RegistrationRequest, Integer> {
}
